package com.malaone.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution2 {
	
	
	
	
	/**8. String to Integer (atoi)
	 * 
	 */
    public int myAtoi(String str) {
    	int i = 0;
        return i;
    }
	
    /**7. Reverse Integer   
     * 
     */
    public int reverse(int x) {
        int rever = 0;
        while(x != 0) {
        	int tail = x % 10;
        	int tmpRever = 10 * rever + tail;
        	
        	if((tmpRever - tail) / 10 != rever) {
        		return 0;
        	}
        	rever = tmpRever;
        	x = x/10;
        }
        return rever;
    }
	
	/**6. ZigZag Conversion
	 * 
	 */
	public String convert(String s, int numRows) {
		int n = s.length();
		if(n <= numRows || numRows==1) {
			return s;
		}
		
        String result = "";
        int gap = numRows-1;
        int gap2 = 2*numRows-2;
        result += getFirstLastRow(s,numRows,true);
        
        for(int i=1; i<numRows-1; i++) {
        	for(int j=i; j<n; j+=gap2) {
        		result += s.charAt(j);
        		int index = numRows-1-2*i+j+gap;
        		if(index<n)
        			result += s.charAt(index);
    		}
        }
        result += getFirstLastRow(s,numRows,false);
		return result;
    }
	
	private String getFirstLastRow(String s, int numRows, boolean isFirst) {
		 String result = "";
		 int i = isFirst ? 0 : (numRows-1);
		 int gap = 2*numRows-2;
		 
		 for(; i<s.length(); i += gap) {
			 result += s.charAt(i);
		 }
		 return result;
	}
	
	/**5. Longest Palindromic Substring
	 * 
	 */
	public String longestPalindrome(String s) {
	    int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);
	        int len2 = expandAroundCenter(s, i, i + 1);
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
	        }
	    }
	    return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--;
	        R++;
	    }
	    return R - L - 1;
	}
	
	//耗时太长， Time Limit Exceeded
	public String longestPalindrome2(String s) {
        String palindrome = "";
        int n = s.length();
        for(int i=0; i<n; i++) {
        	int j=i;
        	
        	while(j < n) {
        		String tmp = s.substring(i, ++j);
        		if(new StringBuilder(tmp).reverse().toString().equals(tmp)
        		   && tmp.length() > palindrome.length()) {
        				palindrome = tmp;
        		}
        	}
        }
		return palindrome;
    }
	
	/**4. Median of Two Sorted Arrays
	 * 
	 */
	public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
	
	/**3. Longest Substring Without Repeating Characters
	 * Given a string, find the length of the longest substring without repeating characters.
	 * Examples:
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 */
	public int lengthOfLongestSubstring(String s) {
		int length = 0;
		char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        
        for(int i=0; i<chars.length; i++) {
        	for(int j=i; j<chars.length; j++) {
        		
        		if(set.contains(chars[j])) {
        			break;
        		} else {
        			set.add(chars[j]);
        		}
        	}
        	if(length < set.size()) {
        		length = set.size();
        	}
        	set.clear();
        }
        return length;
    }
	
	// Sliding Window
	public int lengthOfLongestSubstring2(String s) {
        HashSet<Character> set = new HashSet<>();
        int length = 0, i=0, j=0;
        int n = s.length();
        
        while(i < n && j < n) {
        	if(!set.contains(s.charAt(j))) {
        		set.add(s.charAt(j++));
        		length = Math.max(length, j-i);
        	} else {
        		//abcdb-->cdb,将前面的重复字符及其之前的全部删除
        		set.remove(s.charAt(i++));
        	}
        }
        return length;
    }
	
	// Sliding Window
	public int lengthOfLongestSubstring3(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int length = 0, n = s.length();
        
        for(int i=0, j=0; j<n; j++) {
        	if(map.containsKey(s.charAt(j))) {
        		//只要确保窗内无重复即可，这里排除窗左的重复
        		i = Math.max(map.get(s.charAt(j))+1, i);
        	}
        	length = Math.max(length, j+1-i);
        	map.put(s.charAt(j), j);
        }
        return length;
    }
	
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
	
	/**2. Add Two Numbers
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 * Explanation: 342 + 465 = 807.
	 * 
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode p1=l1, p2=l2, curr=resultHead;
        int carry = 0;
        
        while(p1 != null || p2 != null) {
        	int x = p1!=null ? p1.val : 0;
        	int y = p2!=null ? p2.val : 0;
        	int sum = x + y + carry;
        	carry = sum/10;
        	
        	curr.next = new ListNode(sum%10);
        	curr = curr.next;
        	
        	if(p1 != null) p1 = p1.next;
        	if(p2 != null) p2 = p2.next;
        }
        
        if(carry > 0) {//最大位数进1
        	curr.next = new ListNode(carry);
        }
		return resultHead.next;
    }
	
	/**1. Two Sum
	 * Given nums = [2, 7, 11, 15], target = 9,
	 * Because nums[0] + nums[1] = 2 + 7 = 9,
	 * return [0, 1].
	 * 
	 */
	public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
        	if(map.containsKey(nums[i])) {
        		return new int[]{map.get(nums[i]),i};
        	}
        	map.put(target-nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
