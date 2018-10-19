package nl.rws.hwn.statistics.configuration;

import nl.rws.hwn.publicatie.domain.InfoRequest;
import nl.rws.hwn.publicatie.domain.InfoRequestHWN;
import nl.rws.hwn.publicatie.domain.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@ComponentScan(basePackages = {"nl.rws.hwn.statistics.configuration"} )
public class ProducerApplication {

	static final Logger LOG = LoggerFactory.getLogger(ProducerApplication.class);

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		KafkaConsumerConfig kafkaConsumerConfig = applicationContext.getBean(KafkaConsumerConfig.class);

		Date now = new Date(118, 8, 21, 15, 44, 00);
		Date startTime = now;
		Date endTime = Date.from(now.toInstant().plusSeconds(60));
		OutputStream os = System.out;

//        InfoRequest infoRequest1 = new InfoRequestHWN(ServiceType.variable_message_sign, false, startTime, endTime, "TEST ");
//        KafkaConsumer kafkaConsumer1 = new KafkaConsumer (infoRequest1, kafkaBootstrapServers, writer1, kafkaConsumerConfig, null);
//        try {
//            System.out.println("\n*************************** VariableMessageSign ************************************\n");
//            kafkaConsumer1.timeWindowRun();
//        } finally {}

        InfoRequest infoRequest2 = new InfoRequestHWN(ServiceType.speed_and_flow, false,  Date.from(new Date().toInstant().plusSeconds(60)), "TEST ");
//		InfoRequest infoRequest2 = new InfoRequestRoadSection(ServiceType.speed_and_flow, false, startTime, endTime, "A", 0, "L", new BigDecimal(0), new BigDecimal(50), "TEST");
		KafkaConsumer kafkaConsumer2 = new KafkaConsumer (infoRequest2, kafkaConsumerConfig);
		try {
			System.out.println("\n*************************** SpeedAndFlow ************************************\n");
			kafkaConsumer2.timeWindowRun();
		} finally {}
//
//        InfoRequest infoRequest3 = new InfoRequestHWN(ServiceType.speed_and_flow, false, startTime, endTime, "TEST ");
//        KafkaConsumer kafkaConsumer3 = new KafkaConsumer (infoRequest3, kafkaBootstrapServers, writer3, kafkaConsumerConfig, null);
//        try {
//            System.out.println("\n*************************** VehicleDetection ************************************\n");
//            kafkaConsumer3.timeWindowRun();
//        } finally {}


		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        ((AbstractApplicationContext) applicationContext).close();
	}
}
