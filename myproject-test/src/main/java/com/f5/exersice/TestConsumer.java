package com.f5.exersice;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.f5.exersice.kafka.consumer.Receiver;
import com.f5.exersice.kafka.consumer.ReceiverHandler;

@Configuration
@Component
public class TestConsumer {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestConsumer.class);
	
	@Autowired
	Receiver receiver;

	@Autowired
	@Qualifier(value="TestHandler")
	ReceiverHandler testHandler;

	@Value("${kafka.topics}")
    private String kafkaTopic;
	
	@PostConstruct
	private void init() {
		receiver.setHandler(kafkaTopic, testHandler);
		LOGGER.info("testHandler is set");
	}
}
