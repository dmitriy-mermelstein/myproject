package com.f5.exersice.consumer;

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
public class PublisherConsumer {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublisherConsumer.class);
	
	@Autowired
	Receiver receiver;

	@Autowired
	@Qualifier(value="PublisherHandler")
	ReceiverHandler publisherHandler;

	@Value("${kafka.topics}")
    private String kafkaTopic;
	
	@PostConstruct
	private void init() {
		receiver.setHandler(kafkaTopic, publisherHandler);
		LOGGER.info("publisherHandler is set");
	}
}
