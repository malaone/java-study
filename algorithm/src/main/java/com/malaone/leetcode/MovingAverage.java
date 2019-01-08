package com.malaone.leetcode;

import java.util.LinkedList;

/**
 * 	346. Moving Average from Data Stream
 *
 */
public class MovingAverage {
	private LinkedList<Integer> list = new LinkedList<>();
	private int size;
	private long sum;
	
	public MovingAverage(int size) {
		this.size = size;
	}
	
	public double next(int value) {
		if(list.size() == size) {
			sum -= list.removeFirst();
		}
		sum += value;
		list.addLast(value);
		return sum/list.size();
	}
}
