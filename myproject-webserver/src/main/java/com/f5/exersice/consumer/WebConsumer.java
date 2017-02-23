package com.f5.exersice.consumer;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.f5.exersice.kafka.consumer.Receiver;
import com.f5.exersice.kafka.consumer.ReceiverHandler;

@Component
public class WebConsumer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebConsumer.class);
	
	@Autowired
	Receiver receiver;

	@Autowired
	@Qualifier(value="WebConsumerHandler")
	ReceiverHandler webConsumerHandler;

	@Value("${kafka.topics}")
    private String kafkaTopic;
	
	@PostConstruct
	private void init() {
		receiver.setHandler(kafkaTopic, webConsumerHandler);
		LOGGER.info("webConsumerHandler is set");
	}
}
