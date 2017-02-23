package com.f5.exersice.consumer;

import java.io.IOException;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.f5.exersice.kafka.consumer.ReceiverHandler;
import com.f5.exersice.kafka.data.DataParser;
import com.f5.exersice.kafka.data.PublishMessage;
import com.f5.exersice.kafka.producer.Sender;
import com.f5.exersice.model.Median;
import com.f5.exersice.model.MedianRepository;
import com.fasterxml.jackson.core.JsonParseException;

@Component("PublisherHandler")
public class PublisherHandler implements ReceiverHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublisherHandler.class);

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
			// parse JSON message
			PublishMessage publish = dataParser.parseJsonMessage(message);
			LOGGER.info("parsed publish: '{}'", publish);

			// calculate median
			Double median = calculateMedian(publish);
			
			repository.save(new Median(publish.getPublisher(), publish
					.getTime(), median.toString()));
			
			sender.sendMessage("f5update.test", "{\"publisher\": \"" + publish.getPublisher() + 
					"\", \"readings\":" + publish.getReadings() + "}");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private Double calculateMedian(PublishMessage publishMessage) {
		int length = publishMessage.getReadings().size();
		if (publishMessage.getReadings() == null || length == 0) {
			return null;
		}

		publishMessage.getReadings().sort(new Comparator<Double>() {
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}
		});

		if (length % 2 != 0) {
			return publishMessage.getReadings().get(length / 2);
		} else {
			Double first = publishMessage.getReadings().get(length / 2 - 1);
			Double second = publishMessage.getReadings().get(
					(length / 2));
			Double result = (first + second) / 2;
			return result;
		}
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}
