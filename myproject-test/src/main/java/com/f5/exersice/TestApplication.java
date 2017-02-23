package com.f5.exersice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.f5.exersice.kafka.producer.Sender;
import com.f5.exersice.model.MedianRepository;

@SpringBootApplication
public class TestApplication implements CommandLineRunner  {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestApplication.class);

	@Autowired
	private MedianRepository repository;

	@Autowired
	private Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//repository.deleteAll();
		
		String publishMessage = "{\"publisher\": \"1\", \"time\": \"1234567\",\"readings\": [1, 8, 99,1014,4, 23, 214, 211]}";
		sender.sendMessage("f5sensor.data", publishMessage);
		
		LOGGER.info("===========================> sent message='{}'",
				publishMessage);
	}
}
