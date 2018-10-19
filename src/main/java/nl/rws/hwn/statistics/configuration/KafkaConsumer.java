package nl.rws.hwn.statistics.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rws.hwn.publicatie.domain.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private Consumer<byte[], byte[]> consumer;
    private List<String> topics;
    private InfoRequest infoRequest;
    private ObjectMapper objectMapper = new ObjectMapper();
    private SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SSS");


    public KafkaConsumer(InfoRequest infoRequest, KafkaConsumerConfig kafkaConsumerConfig) {
        this.infoRequest = infoRequest;
        this.topics = getTopics(infoRequest);
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerConfig.getKafkaBootstrapServers());
        String uuid = UUID.randomUUID().toString();
        props.put(ConsumerConfig.GROUP_ID_CONFIG,   infoRequest.getConsumer() + "_" + uuid);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, infoRequest.getConsumer() + "_" + uuid);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConsumerConfig.getSessionTimeoutConfig());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConsumerConfig.getHeartbeatIntervalConfig());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaConsumerConfig.getMaxPollIntervalConfig());
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,  kafkaConsumerConfig.getRequestTimeoutConfig());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerConfig.getMaxPollRecordsConfig());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, kafkaConsumerConfig.getMaxPartFetchBytes());
        this.consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
    }

    private List<String> getTopics(InfoRequest infoRequest) {
        List<String> topics = new ArrayList<>();
        switch (infoRequest.getServiceType()) {
            case variable_message_sign:
                topics.add(TopicNames.VariableMessageSign.getTopicName());
                break;
            case speed_and_flow:
                topics.add(TopicNames.SpeedAndFlow.getTopicName());
                break;
            case vehicle_detection:
                topics.add(TopicNames.VehicleDetection.getTopicName());
                break;
        }
        return topics;
    }

    private Object toObj(byte[] stream) {
        Object obj = null;
        if (stream != null) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(stream);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                obj =  ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        return obj;
    }

    /**
     * timeWindowRun reads the topics in the specified time window
     */
    public void timeWindowRun() throws IOException {
        try {
            Date endTime = subscribeTopics();

            // Move the pointer as close to the eventdate as possible:
            if (infoRequest.getStart() != null) {
                long start = System.currentTimeMillis();
                TopicPartition topicPartition = new TopicPartition(this.topics.get(0), 0);
                Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
                timestampsToSearch.put(topicPartition, infoRequest.getStart().getTime());
                Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = this.consumer.offsetsForTimes(timestampsToSearch);
                for (Map.Entry<TopicPartition, OffsetAndTimestamp> b : topicPartitionOffsetAndTimestampMap.entrySet()) {
                    this.consumer.poll(100); // this is a dummy-poll, needed else the seek() won't work
                    if (b.getValue() !=null) {
                        log.info("InfoRequest.getstart() {} found in topic {}", infoRequest.getStart(),  b.getKey().topic()+"-"+b.getKey().partition() );
                        log.info("Now seeking to offset {}",b.getValue().offset() );
                        this.consumer.seek(topicPartition, b.getValue().offset());
                    } else {
                        log.info("InfoRequest.getstart() {} not found in topic {}", infoRequest.getStart(), b.getKey().topic()+"-"+b.getKey().partition());
                        log.info("Now seeking to end");
                        this.consumer.seekToEnd(Collections.singletonList(topicPartition));
                    }
                }
                log.info("Time to seek: {} ms", System.currentTimeMillis()-start);
            }

            // NOTE: Checks on validity of infoReqStart and infoReqEnd already performed by RequestValidator!

            // Start the poll:
            log.info("Poll started" );
            while (true) {
                /*
                    The parameter passed to poll controls the maximum amount of time that the consumer will block while it awaits records at the current position.
                    The consumer returns immediately as soon as any records are available, but it will wait for the full timeout specified before returning if nothing is available:
                * */
                ConsumerRecords<byte[], byte[]> recordsFetched = this.consumer.poll(1000);
                log.debug("Received records: {}", recordsFetched.count());

                // quit if (current time > endTime of window) and all records before the endTime have been read
                boolean quit = (new Date().compareTo(endTime) > 0);
                for (ConsumerRecord<byte[], byte[]> record : recordsFetched) {
                    boolean quit1 = processTimeWindowRecord(record);
                    quit = quit && quit1 ;
                }
                // Check endtime::
                if (quit) {
                    this.shutdown();
                }

            }
        } catch (WakeupException e) {
            // ignore for shutdown
            log.info("Shut-down request received for timeWindowRun");
        } catch (JAXBException | XMLStreamException e) {
            log.error(e.getMessage());
        } finally {
            log.info("Shutting down timeWindowRun...");
            consumer.close();
        }
    }


    /**
     * latestRun reads the topics to get the latest value of each publication object
     * @return list of latest events
     */
    public Collection<Event> latestRun() {
        HashMap<PublicationObjectEventType, Event> eventMap = new HashMap<>();
        try {
            subscribeTopics();
            log.info("Poll started");
            while (true) {
                /*
                    The parameter passed to poll controls the maximum amount of time that the consumer will block while it awaits records at the current position.
                    The consumer returns immediately as soon as any records are available, but it will wait for the full timeout specified before returning if nothing is available:
                * */
                ConsumerRecords<byte[], byte[]> recordsFetched = this.consumer.poll(1000);
                log.debug("Received records: {}", recordsFetched.count());
                boolean quit = true;
                for (ConsumerRecord<byte[], byte[]> record : recordsFetched) {
                    processLatestRecord(eventMap, record);
                    quit = false;
                }
                if (quit) {
                    this.shutdown();
                }
            }
        } catch (WakeupException e) {
            // ignore for shutdown
            log.info("Shut-down request received for latest run");
        } finally {
            log.info("Shutting down latest run...");
            consumer.close();
        }
        List<Event> list = new ArrayList<>(eventMap.values());
        Collections.sort(list, (e1, e2) -> {return e1.getEvent().compareTo(e2.getEvent());});

        return list;
    }

    /**
     * subscribe to topics
     * @return date
     */
    private Date subscribeTopics() {
        Date endTime = (infoRequest.getEnd() != null ? infoRequest.getEnd() : new Date());
        // Subscribe to topics:
        this.consumer.subscribe(topics);
        // Show the subscribed topics in the log:
        log.info("Subscribed to topics:" );
        this.consumer.subscription().forEach(topic -> log.info("\t\t"+ topic));
        return endTime;
    }

    /**
     * process a record from Kafka topic for timeWindowRun
     * @param record the record
     * @throws XMLStreamException stream exception
     * @throws JAXBException jaxb exception
     * @return true if this record is beyond the end time of the time window, because of the chronological order all records which fall in the window have been processed then.
     */
    private boolean processTimeWindowRecord(ConsumerRecord<byte[], byte[]> record) throws XMLStreamException, JAXBException, IOException {
        boolean quit = false;
        byte[] valStream = record.value();

        Date eventDate =  new Date(record.timestamp());
        if (!(eventDate != null && this.infoRequest != null &&
                ( (this.infoRequest.getStart()!=null && eventDate.before(this.infoRequest.getStart())) ||  (this.infoRequest.getEnd()!=null && eventDate.after(this.infoRequest.getEnd()))  ))) {
            Object key = toObj(record.key());
            if (key != null) {
                if (validMessage(this.infoRequest, key, eventDate)) {
                    Object val = toObj(valStream);
                    if (val != null && val instanceof Event) {
                        Event event = (Event) val;
                        String keyStr = this.objectMapper.writeValueAsString(event);
                        String eventStr = this.objectMapper.writeValueAsString(event);
                        System.out.println(sdtf.format(eventDate) + " |  " + keyStr + " |  " + eventStr);
                    }
                } else {
                    log.debug("not valid tm");
                }
            }
        }
        if (eventDate != null && eventDate.after(this.infoRequest.getEnd())) {
            // stop processing because timeWindow elapsed
            quit = true;
        }
        return quit;
    }

    /**
     * process record from Kafka topic for latestRun
     * @param eventMap the eventMap
     * @param record the record
     */
    private void processLatestRecord(HashMap<PublicationObjectEventType, Event> eventMap, ConsumerRecord<byte[], byte[]> record) {
        Object key = toObj(record.key());
        if (key != null) {
            log.debug("Key: {}", key.getClass().getName());
            /*
                ConsumerRecords are in chronological order, so the last value of a key is the latest
             */
            byte[] valStream = record.value();
            Date eventDate = new Date(record.timestamp());

            if (validMessage(this.infoRequest, key, eventDate)) {
                Object val = toObj(valStream);
                if (val != null && val instanceof Event) {
                    PublicationObjectEventType publicationObjectEventType = (PublicationObjectEventType) key;
                    eventMap.put(publicationObjectEventType, (Event) val);
                }
            }
        } else {
            log.debug("Key and value null");
        }
    }

    private boolean validMessage(InfoRequest infoRequest, Object origKey, Date eventDate) {
        Date infoReqStart  = infoRequest.getStart();
        Date infoRequestEnd   = infoRequest.getEnd();
        PublicationObject key;
        String eventClass;

        // Event should have occurred not before requestStartTime:
        if (infoReqStart!=null ) {
            if (eventDate.before(infoReqStart)) {
                log.debug("not valid: " + infoReqStart.toString() + " before "+ eventDate.toString());
                return false;
            }
        }

        // Event should have occurred not later than requestEndTime:
        if (infoRequestEnd!=null) {
            if (eventDate.after(infoRequestEnd)) {
                log.debug("not valid: event after");
                return false;
            }
        }

        if (origKey instanceof  PublicationObjectEventType) {
            key = ((PublicationObjectEventType) origKey).getPublicationObject();
            eventClass = ((PublicationObjectEventType) origKey).getEventClass();
        } else {
            log.debug("not valid: not PublicationObjectEventType");
            return false;
        }

        if (infoRequest instanceof InfoRequestRoadSection) {
            InfoRequestRoadSection ir = (InfoRequestRoadSection) infoRequest;
            if (key instanceof VariableMessageSign || key instanceof Detector) {
                if (
                    key.getLaneLocation().getRoadLetter().equalsIgnoreCase(ir.getRoadLetter()) &&
                    key.getLaneLocation().getRoadNumber() == ir.getRoadNumber() &&
                    key.getLaneLocation().getCarriageway().equalsIgnoreCase(ir.getCarriageway()) &&
                    (key.getLaneLocation().getKm().compareTo(ir.getStartKm()) >= 0) &&
                    (key.getLaneLocation().getKm().compareTo(ir.getEndKm()) <=0)
                    ) {
                    return true;
                }
            }
        } else if (infoRequest instanceof InfoRequestWholeRoad) {
            InfoRequestWholeRoad ir = (InfoRequestWholeRoad) infoRequest;
            if (key instanceof VariableMessageSign || key instanceof Detector) {
                if (
                        key.getLaneLocation().getRoadLetter().equalsIgnoreCase(ir.getRoadLetter()) &&
                                key.getLaneLocation().getRoadNumber() == ir.getRoadNumber()
                        ) {
                    return true;
                }
            }
        } else if (infoRequest instanceof InfoRequestHWN) {
            if (key instanceof VariableMessageSign) {
                if (
                        eventClass.equals("nl.rws.hwn.publicatie.domain.LaneLocationEvent") ||
                                eventClass.equals("nl.rws.hwn.publicatie.domain.Display") ||
                                        eventClass.equals("nl.rws.hwn.publicatie.domain.DiscontinuedEvent")
                    ) {
                    return true;
                }
            }
            if (key instanceof Detector) {
                if (
                        eventClass.equals("nl.rws.hwn.publicatie.domain.LaneLocationEvent") ||
                                eventClass.equals("nl.rws.hwn.publicatie.domain.AverageSpeedEvent") ||
                                        eventClass.equals("nl.rws.hwn.publicatie.domain.FlowEvent") ||
                                                eventClass.equals("nl.rws.hwn.publicatie.domain.DiscontinuedEvent")
                    ) {
                    return true;
                }
                if (
                        eventClass.equals("nl.rws.hwn.publicatie.domain.LaneLocationEvent") ||
                                eventClass.equals("nl.rws.hwn.publicatie.domain.VehicleDetectionEvent") ||
                                        eventClass.equals("nl.rws.hwn.publicatie.domain.DiscontinuedEvent")
                    ) {
                    return true;
                }
            }
        } else {
            throw new RuntimeException("Unknown InfoRequest subtype");
        }
        log.debug("not valid: " + key.getClass().getName());
        return false;
    }

    public void shutdown() {
        consumer.wakeup();
    }
}