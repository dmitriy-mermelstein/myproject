package com.f5.exersice.kafka.consumer;

public interface ReceiverHandler extends Runnable {
	public void setMessage(String message);
}
