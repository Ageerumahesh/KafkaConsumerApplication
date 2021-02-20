package com.kafka.consumer.cnfgrtn;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.google.gson.Gson;
import com.kafka.consumer.constants.AppConstants;

@Configuration
@EnableKafka
public class KafkaConsumerCnfgrtn {

	@Bean
	public ConsumerFactory<String, String> consumerFatory() {
		System.out.println("KafkaProducerCnfgrtn.consumerFactory()");
		Map<String, Object> configProps = new HashMap<String, Object>();

		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//deserlizer class to be used for the key
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//deserlizer class to be used for the value 
		//here we are using string deserializer class for both
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroupId");
		configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new StringDeserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFatory());
		concurrentKafkaListenerContainerFactory.setMissingTopicsFatal(false);
		return concurrentKafkaListenerContainerFactory;
	}

	@Bean
	public Gson gsonJsonConverter() {
		return new Gson();
	}

}
