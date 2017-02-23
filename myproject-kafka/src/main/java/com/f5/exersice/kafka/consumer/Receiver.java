package com.f5.exersice.kafka.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
public class Receiver extends AsyncConfigurerSupport {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Receiver.class);

	private Executor threadExecutor = null;
	private Collection<ReceiverHandler> receiverHandlers;

	@Value("${kafka.topics}")
    private String kafkaTopic;

	private Map<String, ReceiverHandler> receiverHandlersMap;

	@PostConstruct
	private void init() {
		threadExecutor = getAsyncExecutor();
		receiverHandlers = new ArrayList<ReceiverHandler>();

		receiverHandlersMap = new HashMap<String, ReceiverHandler>();
	}

	private CountDownLatch latch = new CountDownLatch(1);

	// @KafkaListener(topics = {"f5sensor.data", "f5sensor.test"})
	@KafkaListener(topics = "${kafka.topics}")
	public void receiveMessage(String message) {
		LOGGER.info("received message='{}'", message);
		latch.countDown();

		if (receiverHandlersMap != null && !receiverHandlersMap.isEmpty()) {
			// launch thread from thread pool and print thread number.

			// for(ReceiverHandler receiverListener : receiverHandlers) {
			//
			// //receiverHandlersMap.get("${kafka.topics}");
			// receiverListener.setMessage(message);
			// threadExecutor.execute(receiverListener);
			// }

			ReceiverHandler receiverListener = receiverHandlersMap.get(kafkaTopic);
			if(receiverListener != null) {
				receiverListener.setMessage(message);
				threadExecutor.execute(receiverListener);
			}

			// threadExecutor.execute(new Runnable() {
			//
			// @Override
			// public void run() {
			// LOGGER.info("received message='{}'", message);
			//
			// }
			//
			// });

		}
	}

	public void setHandler(ReceiverHandler receiverHandler) {
		receiverHandlers.add(receiverHandler);
	}

	public void setHandler(String topic, ReceiverHandler receiverHandler) {
		receiverHandlersMap.put(topic, receiverHandler);
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("F5Thread-");
		executor.initialize();
		return executor;
	}
}
