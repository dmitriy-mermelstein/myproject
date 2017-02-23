package com.f5.exersice.kafka.data;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataParser {

	private ObjectMapper mapper;

	@PostConstruct
	private void init() {
		JsonFactory factory = new JsonFactory();
		mapper = new ObjectMapper(factory);
	}
	
	public PublishMessage parseJsonMessage(String message)
			throws JsonProcessingException, IOException {

		PublishMessage publishMessage = new PublishMessage();
		// JsonFactory factory = new JsonFactory();

		// ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(message);

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode
				.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();

			switch (field.getKey()) {
			case "publisher":
				publishMessage.setPublisher(field.getValue().asText());
				System.out.println("Key: " + field.getKey() + "\tValue:"
						+ field.getValue());
				break;
			case "time":
				publishMessage.setTime(field.getValue().asText());
				System.out.println("Key: " + field.getKey() + "\tValue:"
						+ field.getValue());
				break;
			case "readings":
				Iterator<JsonNode> fieldIterator = field.getValue().elements();
				while (fieldIterator.hasNext()) {
					double value = fieldIterator.next().asInt();
					System.out.println("\tValue:" + value);
					publishMessage.getReadings().add(value);
				}
				break;
			}
		}

		return publishMessage;
	}
}
