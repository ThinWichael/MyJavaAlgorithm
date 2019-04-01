package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 428. Serialize and Deserialize N-ary Tree

//Serialization is the process of converting a data structure or object into a sequence of bits
//so that it can be stored in a file or memory buffer, or transmitted across a network connection
//link to be reconstructed later in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//For example, you may serialize the following 3-ary tree
//
//        1
//     /  |  \
//    3   2   4
//   / \
//  5   6

//as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//
//
//Note:
//
// 1. N is in the range of [1, 1000]
// 2. Do not use class member/global/static variables to store states. 
//Your serialize and deserialize algorithms should be stateless. << very important

public class Quiz428_De_SerializeTree {

	public Quiz428_De_SerializeTree() {
		// TODO Auto-generated constructor stub
	}

	class Node {
		public int val;
		public List<Node> children;

		public Node(int _val) {
			val = _val;
			children = new ArrayList<Node>();
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}

		public void setChildren(List<Node> _children) {
			children = _children;
		}
	};

	// Encodes a tree to a single string.
	public static String serialize(Node root) {

		String result = "";
//		StringBuilder sb = new StringBuilder();

		result = preOrderSerialize(root, result);
//		result = sb.toString();
		System.out.println(result);

		return result;
	}

	public static String preOrderSerialize(Node node, String result) {

		result += "{" + node.val;

		if (node.children != null && !node.children.isEmpty()) {
			for (Node child : node.children) {
				String temp = "";
				result += preOrderSerialize(child, temp);
			}
		}
		result += "}";

		return result; // e.g. {1{3{5}{6}}{2}{4}}
	}

	// Decodes your encoded data to tree.
	// e.g. {199{3{5}{6}}{2}{4}} three pattern {digit{ , digit}{ , digit}}
	public Node deserialize(String data) {
		int i = 0;
		int n = data.length();
		Node root = null;
		Stack<Node> stack = new Stack<Node>();

		while (i < n) {
			if("{".equals(Character.toString(data.charAt(i)))) i++;
			int start = i;
			
			while (Character.isDigit(data.charAt(i))) i++;
			if (start == i) { // encounter "digit}}" 
				Node child = stack.pop();
				if (stack.isEmpty()) { root = child; break;}
				stack.peek().children.add(child);
			} else { // "{digit{" or "digit}{"
				stack.push(new Node(Integer.parseInt(data.substring(start, i))));
				// if "digit}{"
				if("}".equals(Character.toString(data.charAt(i)))) {
					Node child = stack.pop();
					stack.peek().children.add(child);
				}
			}
			i++;
		}
		return root;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz428_De_SerializeTree quiz = new Quiz428_De_SerializeTree();
		Node node1 = quiz.new Node(1);
		Node node2 = quiz.new Node(2);
		Node node3 = quiz.new Node(3);
		Node node4 = quiz.new Node(4);
		Node node5 = quiz.new Node(5);
		Node node6 = quiz.new Node(6);

		List<Node> list1 = new ArrayList();
		list1.add(node3);
		list1.add(node2);
		list1.add(node4);
		node1.setChildren(list1);

		List<Node> list3 = new ArrayList();
		list3.add(node5);
		list3.add(node6);
		node3.setChildren(list3);

		String serialStr = serialize(node1);
        Node root = quiz.deserialize(serialStr);
        String serialStr2 = serialize(node1);
	}

}
