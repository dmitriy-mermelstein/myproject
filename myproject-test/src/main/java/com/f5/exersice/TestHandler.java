package com.f5.exersice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.f5.exersice.kafka.consumer.ReceiverHandler;
import com.f5.exersice.kafka.data.DataParser;
import com.f5.exersice.kafka.producer.Sender;
import com.f5.exersice.model.MedianRepository;

@Component("TestHandler")
public class TestHandler implements ReceiverHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestHandler.class);

	@Autowired
	private MedianRepository repository;

	@Autowired
	private Sender sender;

	@Autowired
	DataParser dataParser;
	
	private String message;
	
	@Override
	public void run() {

		LOGGER.info("<===========================received message='{}'",
				message);
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}
