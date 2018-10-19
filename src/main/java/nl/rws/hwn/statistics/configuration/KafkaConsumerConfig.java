package nl.rws.hwn.statistics.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = {"nl.rws.hwn.statistics.configuration"} )
@PropertySources({@PropertySource("classpath:hwn-kafka-consumer.properties"), @PropertySource("classpath:hwn-kafka-consumer.override.properties")})
public class KafkaConsumerConfig implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Value("${kafka.consumer_config.session_timeout_ms_config:10000}")
    private Integer sessionTimeoutConfig;

    @Value("${kafka.consumer_config.heartbeat_interval_ms_config:3000}")
    private Integer heartbeatIntervalConfig;

    @Value("${kafka.consumer_config.max_poll_interval_ms_config:300000}")
    private Integer maxPollIntervalConfig;

    @Value("${kafka.consumer_config.request_timeout_ms_config:305000}")
    private Integer requestTimeoutConfig;

    @Value("${kafka.consumer_config.max_poll_records_config:500}")
    private Integer maxPollRecordsConfig;

    @Value("${kafka.consumer_config.max.partition.fetch.bytes:25000000}")
    private Integer maxPartFetchBytes;

    @Value("${kafka.bootstrap-servers:localhost:9001}")
    private String kafkaBootstrapServers;

    @Value("${kafka.topic:SpeedAndFlow}")
    private String kafkaTopic;



    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Integer getSessionTimeoutConfig() {
        return sessionTimeoutConfig;
    }

    public Integer getHeartbeatIntervalConfig() {
        return heartbeatIntervalConfig;
    }

    public Integer getMaxPollIntervalConfig() {
        return maxPollIntervalConfig;
    }

    public Integer getRequestTimeoutConfig() {
        return requestTimeoutConfig;
    }

    public Integer getMaxPollRecordsConfig() {
        return maxPollRecordsConfig;
    }

    public Integer getMaxPartFetchBytes() {
        return maxPartFetchBytes;
    }

    public String getKafkaBootstrapServers() {
        return kafkaBootstrapServers;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    };

    public void afterPropertiesSet() {
        log.info("KafkaConsumerConfig about to be loaded...");
        log.info("getSessionTimeoutConfig:" + getSessionTimeoutConfig());
        log.info("getHeartbeatIntervalConfig" + getHeartbeatIntervalConfig());
        log.info("getMaxPollIntervalConfig"  + getMaxPollIntervalConfig());
        log.info("getRequestTimeoutConfig" + getRequestTimeoutConfig());
        log.info("getMaxPollRecordsConfig" + getMaxPollRecordsConfig());
        log.info("getMaxPartFetchBytes" + getMaxPartFetchBytes());
        log.info("getKafkaBootstrapServers" + getKafkaBootstrapServers());
        log.info("getKafkaTopic" + getKafkaTopic());
    }


}
