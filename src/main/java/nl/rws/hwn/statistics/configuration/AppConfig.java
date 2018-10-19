package nl.rws.hwn.statistics.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({ "nl.rws.hwn.statistics.configuration"})
@PropertySources({@PropertySource("classpath:hwn-kafka-consumer.properties"), @PropertySource("classpath:hwn-kafka-consumer.override.properties")})
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
