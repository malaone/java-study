package com.malaone.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;


public class Solution {
	
	
	
	
	
	/**
	 * 324. Wiggle Sort II
	 */
	public void wiggleSortII(int[] nums) {
		int[] temp = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(temp);
        int large = temp.length / 2 + (temp.length % 2 == 0 ? -1 : 0);
        int small = temp.length - 1;
        for (int i = 0, j = 1; i < temp.length; i+=2, j+=2) {
        	nums[i] = temp[large--];
            if(j < temp.length) nums[j] = temp[small--];
        }
    }
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[i+1];
		nums[i+1] = tmp;
	}
	
	/**
	 * 280. Wiggle Sort
	 */
	public void wiggleSort(int[] nums) {
		for(int i=0; i<nums.length-1; i++) {
			if((i%2==0 && nums[i]>nums[i+1]) || (i%2==1 && nums[i]<nums[i+1])) {
				int tmp = nums[i];
				nums[i] = nums[i+1];
				nums[i+1] = tmp;
			}
		}
	}
	
	public void wiggleSort2(int[] nums) {
		Arrays.sort(nums);
		for(int i=1; i<nums.length-1; i+=2) {
			int tmp = nums[i];
			nums[i] = nums[i+1];
			nums[i+1] = tmp;
		}
	}
	
	/**
	 * 682. Baseball Game
	 */
	public int calPoints(String[] ops) {
        int sum = 0;
        int tmp = 0;
        LinkedList<Integer> list = new LinkedList<>();
        
        for(String str : ops) {
        	switch(str) {
        	case "C":
        		tmp = 0 - list.removeLast();
        		break;
        	case "D":
        		tmp = 2*list.getLast();
        		list.addLast(tmp);
        		break;
        	case "+":
        		tmp = list.getLast() + list.get(list.size()-2);
        		list.add(tmp);
        		break;
        	default:
        		tmp = Integer.valueOf(str);
        		list.add(tmp);
        		break;
        	}
        	sum += tmp;
        }
        return sum;
    }
	
	public int calPoints2(String[] ops) {
        int sum = 0;
        int tmp = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(String str : ops) {
        	switch(str) {
        	case "C":
        		tmp = stack.pop();
        		break;
        	case "D":
        		tmp = 2*stack.peek();
        		stack.push(tmp);
        		break;
        	case "+":
        		int top = stack.pop();
        		tmp = stack.peek() + top;
        		stack.push(top);
        		stack.push(tmp);
        		break;
        	default:
        		tmp = Integer.valueOf(str);
        		stack.push(tmp);
        		break;
        	}
        	sum += tmp;
        }
        return sum;
    }
	
	/**
	 * 500. Keyboard Row
	 */
	public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        Set<Character> row1 = string2set("QWERTYUIOP");
        Set<Character> row2 = string2set("ASDFGHJKL");
        Set<Character> row3 = string2set("ZXCVBNM");
        
        for(String word : words) {
        	Set<Character> set = string2set(word);
        	if(row1.containsAll(set) || row2.containsAll(set) || row3.containsAll(set)) {
        		list.add(word);
        	}
        }
        return list.toArray(new String[0]);
    }
	
	private Set<Character> string2set(String str) {
		Set<Character> set = new HashSet<>();
		char[] chars = str.toUpperCase().toCharArray();
		for(char ch : chars) {
			set.add(ch);
		}
		return set;
	}
	
	public String[] findWords2(String[] words) {
	    return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
	}
	
	/**
	 * 557. Reverse Words in a String III
	 */
	public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        String[] array = s.split(" ");
        for(String str : array) {
        	result.append(new StringBuilder(str).reverse().toString()).append(" ");
        }
        return result.toString().trim();
    }
	
	public String reverseWords2(String s) {
        StringBuilder result = new StringBuilder();
        String[] array = s.split(" ");
        for(int i=0; i<array.length; i++) {
        	result.append(reverse(array[i])).append(" ");
        }
        return result.toString().trim();
    }
	
	private String reverse(String str) {
		StringBuilder result = new StringBuilder();
		for(int i=str.length()-1; i>=0; i--) {
			result.append(str, i, i + 1);
		}
		return result.toString();
	}
	
	/**
	 * 366. Find Leaves of Binary Tree
	 */
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		
		while(root != null) {
			List<Integer> leaves = new ArrayList<>();
			root = removeLeaves(root, leaves);
			result.add(leaves);
		}
		
		return result;
	}
	
	private TreeNode removeLeaves(TreeNode tree, List<Integer> leaves) {
		if(tree == null) {
			return null;
		}
		if(tree.left == null && tree.right == null) {
			leaves.add(tree.val);
			return null;
		}
		
		tree.left = removeLeaves(tree.left,leaves);
		tree.right = removeLeaves(tree.right,leaves);
		return tree;
	}
	
	/**
	 * 476. Number Complement
	 * Example 1:
	 * Input: 5
	 * Output: 2
	 * Explanation: The binary representation of 5 is 101 (no leading zero bits), 
	 * and its complement is 010. So you need to output 2.
	 */
    public int findComplement(int num) {
        int mask = 1;
        int tmp = num >> 1;
        while(tmp != 0) {
        	mask = (mask << 1) | 1;
        	tmp = tmp >> 1;
        }
        return mask ^ num;
    }
	
	/**338. Counting Bits
	 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
	 * calculate the number of 1's in their binary representation and return them as an array.
	 * Example:
	 * For num = 5 you should return [0,1,1,2,1,2].
	 * 
	 * i >> 1 = i/2, 那么i的二进制1的个数就等于i/2的1的位数 + 1或0（右移1位可能为1或0）
	 */
	public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for(int i=1; i<=num; i++) {
        	result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }
	
	public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        for(int i=1; i<=num; i++) {
        	int tmp = i;
        	while(tmp != 0) {
        		if((tmp & 1) == 1) {
        			result[i]++;
        		}
        		tmp = tmp >> 1;
        	}
        }
        return result;
    }
	
	/**419. Battleships in a Board
	 * 
	 * X..X        ...X
	 * ...X        XXXX
	 * ...X        ...X
	 *   2           0
	 * 
	 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
	 */
	public int countBattleships(char[][] board) {
        int count = 0;
        for(int i=0; i<board.length; i++) {
        	for(int j=0; j<board[0].length;j++) {
        		if(board[i][j] == 'X' && 
        		  (i == 0 || board[i-1][j] == '.') && 
        		  (j == 0 || board[i][j-1] == '.')) {
        			count++;
        		}
        	}
        }
		return count;
    }
	
	/**399. LeetCode Nested List Weight Sum
	 * Example 1:
	 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
	 * Example 2:
	 * Given the list [1,[4,[6]]], return 27. 
	 * (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
	 * 
	 */
	public int depthSum(List<NestedInteger> nestedList) {
		return dfs(nestedList, 1);
	}
	private int dfs(List<NestedInteger> nestedList, int depth){
	    int sum = 0;
	    for(NestedInteger item : nestedList){
	    	sum += item.isInteger() ? item.getInteger()*depth : dfs(item.getList(), depth+1);
	    }
	    return sum;
	}
	
	/** 537. Complex Number Multiplication
	 * Input: "1+1i", "1+1i"
	 * Output: "0+2i"
	 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, 
	 * and you need convert it to the form of 0+2i.
	 */
	public String complexNumberMultiply(String a, String b) {
		int[] arrayA = getComplexNumbers(a);
		int[] arrayB = getComplexNumbers(b);
		StringBuilder result = new StringBuilder();
        
		result.append(arrayA[0] * arrayB[0] - arrayA[1] * arrayB[1])
			  .append("+")
		      .append(arrayA[0] * arrayB[1] + arrayA[1] * arrayB[0])
		      .append("i");
		
		return result.toString();
    }
	
	private int[] getComplexNumbers(String a) {
		int[] complexNumbers = new int[2];
		int plusIndex = a.indexOf('+');
		int iIndex = a.indexOf('i');
		complexNumbers[0] = Integer.parseInt(a.substring(0, plusIndex));
		complexNumbers[1] = Integer.parseInt(a.substring(plusIndex+1, iIndex));
        return complexNumbers;
	}
	
	/**
	 * Input: [1,4,3,2]
	 * Output: 4
	 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	 */
	public int arrayPairSum(int[] nums) {
        int max = 0;
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length-1; i+=2) {
        	max += nums[i];
        }
        return max;
    }
	
	/**
	 * Input:                           Output:
	 * 	     Tree 1         Tree 2            Merged tree:      
	 *         1              2                     3        
	 *        / \            / \                   / \         
	 *       3   2          1   3                 4   5       
	 *      /                \   \               / \   \       
	 *     5                  4   7             5   4   7      
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {//recursion
        if(t1 == null) {
        	return t2;
        }
        if(t2 == null) {
        	return t1;
        }
        
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
		
		return t1;
    }
	
	public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {//stack
		if(t1==null) {
			return t2;
		}
		if(t2 == null) {
        	return t1;
        }
		
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1,t2});
        
        while(!stack.isEmpty()) {
        	TreeNode[] tn = stack.pop();
        	if(tn[0] == null || tn[1] == null) {
        		continue;
        	}
        	
        	tn[0].val += tn[1].val;
        	if(tn[0].left == null) {
        		tn[0].left = tn[1].left;
        	} else {
        		stack.push(new TreeNode[] {tn[0].left,tn[1].left});
        	}
        	
        	if(tn[0].right == null) {
        		tn[0].right = tn[1].right;
        	} else {
        		stack.push(new TreeNode[] {tn[0].right,tn[1].right});
        	}
        }
		return t1;
    }
	
	public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {//queue
		if(t1==null) {
			return t2;
		}
		if(t2 == null) {
        	return t1;
        }
		
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.add(new TreeNode[] {t1,t2});
        
        while(!queue.isEmpty()) {
        	TreeNode[] tn = queue.remove();
        	if(tn[0] == null || tn[1] == null) {
        		continue;
        	}
        	
        	tn[0].val += tn[1].val;
        	if(tn[0].left == null) {
        		tn[0].left = tn[1].left;
        	} else {
        		queue.add(new TreeNode[] {tn[0].left,tn[1].left});
        	}
        	
        	if(tn[0].right == null) {
        		tn[0].right = tn[1].right;
        	} else {
        		queue.add(new TreeNode[] {tn[0].right,tn[1].right});
        	}
        }
		return t1;
    }
	
	/**
	 * A self-dividing number is a number that is divisible by every digit it contains.
	 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
	 * Also, a self-dividing number is not allowed to contain the digit zero.
	 * Given a lower and upper number bound, output a list of every possible self dividing number, 
	 * including the bounds if possible.
	 * 
	 */
	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> resultList = new ArrayList<>();
		for(int i=left; i<=right; i++) {
			String digits = String.valueOf(i);
			boolean flag = true;
			
			for(char digit : digits.toCharArray()) {
				if((digit == '0') || (i % (digit - '0') > 0)) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				resultList.add(i);
			}
		}
		return resultList;
    }
	
	/**
	 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, 
	 * judge if this robot makes a circle, which means it moves back to the original place.
     * The move sequence is represented by a string. And each move is represent by a character. 
     * The valid robot moves are R (Right), L (Left), U (Up) and D (down). 
     * The output should be true or false representing whether the robot makes a circle.
     * 
	 */
	public boolean judgeCircle(String moves) {
		int x = 0, y = 0;
		for(char move : moves.toCharArray()) {
			switch(move) {
			case 'U':
				y++;
				break;
			case 'D':
				y--;
				break;
			case 'L':
				x--;
				break;
			case 'R':
				x++;
				break;
			}
		}
        return x==0 && y==0;
    }
	
	/**
	 * Input: x = 1, y = 4
	 * Output: 2
	 * Explanation:
	 * 1   (0 0 0 1)
	 * 4   (0 1 0 0)
     *        ↑   ↑
	 * The above arrows point to positions where the corresponding bits are different.
	 *
	 */
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}
	public int hammingDistance2(int x, int y) {
		int xor = x ^ y;
		int distance = 0;
		
		for(int i=0; i<32; i++) {
			distance += (xor >> i) & 1;
		}
		return distance;
	}
	
	/**
	 * Input: [3,2,1,6,0,5]
	 *  Output: return the tree root node representing the following tree:
	 *		      6
	 *		    /   \
	 *		   3     5
	 *		    \    / 
	 *		     2  0   
	 *		       \
	 *		        1
	 * 
	 */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	return recursionMakeTree(nums,0,nums.length);
    }
    
    private TreeNode recursionMakeTree(int[] nums, int leftIndex, int rightIndex) {
    	if(leftIndex==rightIndex) {
    		return null;
    	}
    	
    	int maxIndex = getMaxIndex(nums,leftIndex,rightIndex);
    	TreeNode treeNode = new TreeNode(nums[maxIndex]);
    	treeNode.left = recursionMakeTree(nums,leftIndex,maxIndex);
    	treeNode.right = recursionMakeTree(nums,maxIndex+1,rightIndex);
    	
    	return treeNode;
    }
    
    private int getMaxIndex(int[] nums, int leftIndex, int rightIndex) {
    	int maxIndex = leftIndex;
    	
    	for(int i=leftIndex+1; i<rightIndex; i++) {
    		if(nums[maxIndex] < nums[i]) {
    			maxIndex = i;
    		}
    	}
    	return maxIndex;
    }
	
	
	/**
	 * NBA季后赛对战机制
	 * Input: 8
	 * Output: (((1,8),(4,5)),((2,7),(3,6)))
     * Explanation: 
     * First round: (1,8),(2,7),(3,6),(4,5)
     * Second round: ((1,8),(4,5)),((2,7),(3,6))
     * Third round: (((1,8),(4,5)),((2,7),(3,6)))
     * Since the third round will generate the final winner, 
     * you need to output the answer (((1,8),(4,5)),((2,7),(3,6))).
     * 
	 */
	public String findContestMatch(int n) {
		if(n%2 !=0 && n>0) {
			return "n必须为偶数";
		}
		
		String[] array = new String[n];
		for(int i=0; i<n; i++) {
			array[i] = String.valueOf(i + 1);
		}
		
		while(n>1) {
			for(int i=0; i<n/2; i++) {
				array[i] = "(" + array[i] + "," + array[n-1-i] + ")";
			}
			n = n/2;
		}
		return array[0];
	}
	
	public String findContestMatch2(int n) {
		if(n%2 !=0 && n>0) {
			return "n必须为偶数";
		}
		
		String[] array = new String[n];
		for(int i=0; i<n; i++) {
			array[i] = String.valueOf(i + 1);
		}
		recursionContestMatch(n,array);
		return array[0];
	}
	
	private void recursionContestMatch(int n, String[] array) {
		if(n<=1) {
			return;
		}
		
		for(int i=0; i<n/2; i++) {
			array[i] = "(" + array[i] + "," + array[n-1-i] + ")";
		}
		recursionContestMatch(n/2,array);
	}
}
