package com.malaone.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 359. Logger Rate Limiter
 */
public class Logger {
	Map<String,Integer> map = new HashMap<>();
	
	public boolean shouldPrintMessage(int timestamp, String message) {
		if(timestamp < map.getOrDefault(message, 0)) {
			return false;
		}
		map.put(message, timestamp + 10);
		return true;
	}
}
