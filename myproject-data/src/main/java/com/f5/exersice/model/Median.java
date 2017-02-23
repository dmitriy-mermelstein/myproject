/**
 * 
 */
package com.f5.exersice.model;

import org.springframework.data.annotation.Id;

/**
 * @author Dmitriy
 *
 */

public class Median {

	@Id
	public String mid;
	
	public String publisherId;

	public String time;
	
	public String median;

	public Median() {
	}

	public Median(String publisherId, String time, String median) {
		super();
		this.mid = "" + this.hashCode();
		this.publisherId = publisherId;
		this.time = time;
		this.median = median;
	}

	@Override
	public String toString() {
		return "Median [publisherId=" + publisherId + ", time=" + time
				+ ", median=" + median + "]";
	}
}
