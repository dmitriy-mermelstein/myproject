package com.f5.exersice.kafka.data;

import java.util.ArrayList;

public class PublishMessage {
	private String publisher;
	private String time;
	private ArrayList<Double> readings = new ArrayList<Double>();
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ArrayList<Double> getReadings() {
		return readings;
	}
	public void setReadings(ArrayList<Double> readings) {
		this.readings = readings;
	}
	
	@Override
	public String toString() {
		return "PublishMessage [publisher=" + publisher + ", time=" + time
				+ ", readings=" + readings + "]";
	}
}
