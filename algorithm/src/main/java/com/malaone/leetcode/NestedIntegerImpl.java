package com.malaone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {

	private List<NestedInteger> list;
	private int value;
	
	public NestedIntegerImpl(List<NestedInteger> list) {
		this.list = list;
	}
	
	public NestedIntegerImpl(int value) {
		this.value = value;
	}
	
	@Override
	public boolean isInteger() {
		return list == null;
	}

	@Override
	public Integer getInteger() {
		return value;
	}

	@Override
	public List<NestedInteger> getList() {
		return list;
	}
	
	@Override
	public String toString() {
		String str;
		if(isInteger()) {
			str = value + "";
		} else {
			str = list.toString();
		}
		return str;
	}
	
	//[[1,1],2,[1,1]]
	//[1,[4,[6]]]
	public static List<NestedInteger> string2NestInt(String str) {
		List<NestedInteger> list = new ArrayList<>();
		String[] ele = str2Array(str);
		
		for(int i=0; i<ele.length; i++) {
			if(ele[i].contains("[")) {
				list.add(new NestedIntegerImpl(string2NestInt(ele[i])));
			} else {
				list.add(new NestedIntegerImpl(Integer.parseInt(ele[i])));
			}
		}
		return list;
	}
	
	//[[1,1],2,[1,1]] ----> [1,1],2,[1,1]
    //[1,[4,[6]]] ---->  1,[4,[6]]
	public static String[] str2Array(String str) {
		List<String> list = new ArrayList<>();
		int firstLeft = 0;
		int interLeft = 0;
		
		for(int i=1; i<str.length()-1; i++) {
			char ch = str.charAt(i);
			
			if(ch == '[') {
				if(firstLeft == 0) {
					firstLeft = i;
				} else {
					interLeft++;
				}
			} else if(ch == ']') {
				if(interLeft == 0) {
					list.add(str.substring(firstLeft, i+1));
					firstLeft = 0;
				} else {
					interLeft--;
				}
			}
			
			if(firstLeft == 0 && Character.isDigit(ch)) {
				list.add(str.substring(i, i+1));
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
}
