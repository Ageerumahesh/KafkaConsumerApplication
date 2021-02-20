package com.kafka.consumer.contoller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kafka.consumer.pojo.Employee;


@RestController
public class KafkaConsumerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private Gson gson;

	@KafkaListener(topics = { "test" })
    public void getTopics(@RequestBody String emp) {
        System.out.println("Kafka event consumed is: " + emp);
        Employee model = gson.fromJson(emp, Employee.class);
        System.out.println("Model converted value: " + model.toString());
    }
	
	// We use ConcurrentKafkaListenerContainerFactory to create containers for methods annotated with @KafkaListener
}
