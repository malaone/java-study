package com.malaone.leetcode;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
	
	public static void display(String nodeName,ListNode ln) {
		ListNode tmp = ln;
		System.out.print(nodeName + "->");
		while(tmp !=null) {
			System.out.print(tmp.val +"->");
			tmp = tmp.next;
		}
		System.out.println("null");
	}
}
