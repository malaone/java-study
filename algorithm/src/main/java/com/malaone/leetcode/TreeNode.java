package com.malaone.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
	
	public static String displayTreeNode(TreeNode rootNode) {
		if(rootNode == null) {
			return "[]";
		}
		
		StringBuilder str = new StringBuilder("[");
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(rootNode);
		
		while(!nodeQueue.isEmpty()) {
			TreeNode treeNode = nodeQueue.remove();
			
			if(treeNode == null) {
				str.append("null,");
				continue;
			}
			
			str.append(treeNode.val).append(",");
			nodeQueue.add(treeNode.left);
			nodeQueue.add(treeNode.right);
		}
		str.deleteCharAt(str.length()-1).append("]");
		return str.toString();
	}
	
   public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (node == null) {
              output += "null,";
              continue;
            }
    
            output += String.valueOf(node.val) + ",";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }
}
