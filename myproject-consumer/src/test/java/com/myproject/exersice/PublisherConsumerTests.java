package com.myproject.exersice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.exersice.kafka.data.PublishMessage;
import com.myproject.exersice.kafka.producer.Sender;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherConsumerTests {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublisherConsumerTests.class);

	@Autowired
	private Sender sender;

	@Test
	public void testReceiver() throws Exception {
		System.out
				.println("myprojectsensor.data: =======================>Hello, it's sensor data!");

		//sender.sendMessage("myprojectsensor.data", "{\"publisher\": \"1\", \"time\": \"1234567\",\"readings\": [1, 13, 192, 7, 8, 99,1014,4]}");
		
		sender.sendMessage("myprojectsensor.data", "{\"publisher\": \"1\", \"time\": \"1234567\",\"readings\": [1, 8, 99,1014,4, 23, 214, 211]}");

	}
}
