package com.myproject.exersice.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myproject.exersice.kafka.consumer.ReceiverHandler;
import com.myproject.exersice.kafka.data.DataParser;
import com.myproject.exersice.kafka.data.PublishMessage;
import com.myproject.exersice.kafka.producer.Sender;
import com.myproject.exersice.model.Median;
import com.myproject.exersice.model.MedianRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component("WebConsumerHandler")
public class WebConsumerHandler implements ReceiverHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebConsumerHandler.class);

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

		try {
			// parse the message received from Publisher Consumer
			PublishMessage publish = dataParser.parseJsonMessage(message);
			LOGGER.info("parsed publish: '{}'", publish);

			// fetch all medians related to the publisher id
			List<Median> medians = repository.findByPublisherId(publish.getPublisher());
			
			List<String> publisherMedians = new ArrayList<String>();
			for(Median median : medians) {
				publisherMedians.add(median.median);
			}
			
			String result = "pid" + publish.getPublisher() + ":" + publisherMedians.toString() + 
					"," + publish.getReadings().toString();
			LOGGER.info(result);
			
			// print and send the result string to broker
			sender.sendMessage("myprojectresult.test", result);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setMessage(String message) {
		this.message = message;

	}

}
