package com.f5.exersice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.f5.exersice.kafka.consumer.ReceiverHandler;

public class ReceiverHandlerTest implements ReceiverHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReceiverHandlerTest.class);

	private String message = null;
	
	@Override
	public void run() {
		LOGGER.info("!!!!!!!!!!!!!!!!!!!! ReceiverListenerTest: message[" + message + "]");
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}