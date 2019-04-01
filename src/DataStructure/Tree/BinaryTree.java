package DataStructure.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

	BinaryTreeNode<T> root;
	int i = 0;
	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public BinaryTree(int[] A) {
		i = 0;
        root = preOrderBuild(A , Integer.MAX_VALUE);
	}
	// pre-order build: easy to imbalance --> check out for-loop "add" !
	private BinaryTreeNode<T> preOrderBuild(int[] A , int upbound) {
		if( i == A.length || A[i] > upbound) return null;
		
		BinaryTreeNode<T> node = new BinaryTreeNode<>(A[i]);
		i++;
		
		node.left = preOrderBuild(A, node.value);
		node.right = preOrderBuild(A, upbound);
		return node;
	}
	
	void add(int value, T data) {
		root = addRecursively(root, value, data);
	}
	
	private BinaryTreeNode addRecursively(BinaryTreeNode node, int value, T data) {
		if( node == null) 
			return new BinaryTreeNode(value, data);
		
		if(value < node.value) {
			node.left = addRecursively(node.left, value, data);
		} else if ( value > node.value) {
			node.right = addRecursively(node.right, value, data);
		} else {
			return node; // value already exists
		}
		
		return node;
	}
	
	/* inorder traversal 中序走訪 */

	void inOrderTraversal(BinaryTreeNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			visit(node);
			inOrderTraversal(node.right);
		}
	}

	/* preorder traversal 前序走訪 */
	void preOrderTraversal(BinaryTreeNode node) {
		if (node != null) {
			visit(node);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}

	/* postOrder Traversal 後序走訪 */
	void postOrderTraversal(BinaryTreeNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			visit(node);
		}
	}

	/* level-by-level Traversal 階序走訪 - Recursively */
	void levelTraversal(BinaryTreeNode node) {
		int height = getHeight(node);
		
		for(int i = 1; i <= height; i++) {
			visiteBylevel(node, i);
		}
	}

    void visiteBylevel(BinaryTreeNode node, int level) {
		
		if(node == null )
			return;
		
		if(level == 1) { // reach the level
			visit(node);
		} else if(level > 1) { // go to that level
			visiteBylevel(node.left, level - 1);
			visiteBylevel(node.right, level - 1);
		}
		
	}
	
	/* level-by-level Traversal 階序走訪 - Iteratively */
	static void levelTraversal2(BinaryTreeNode node) {
		
		/* Use Queue */
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(node);
		System.out.println("==level by level==");
		
		while(!queue.isEmpty()) {
			BinaryTreeNode tempNode = queue.poll();
			if(tempNode != null) visit(tempNode);
			else {
				System.out.println("null");
				continue;
			}
			
			/* load left child to Queue */
			if(tempNode.left != null) {
				queue.add(tempNode.left);
			} else queue.add(null);
			
			/* load right child to Queue */
			if(tempNode.right != null) {
				queue.add(tempNode.right);
			} else queue.add(null);
			
		}
	}
	
	
	int getHeight(BinaryTreeNode node) {
		if (node == null) {
			return 0;
		}
		/*
		 * Recursive way to record and compare 
		 * the height of left and right
		 */

		int lheight = getHeight(node.left);
		int rheight = getHeight(node.right);

		if (lheight > rheight)
			return (lheight + 1);
		else
			return (rheight + 1);
	}
	
	static void visit(BinaryTreeNode node) {
		System.out.println("data :" + node.data + " value: " + node.value);
	}

	int countPathsWithSumFromNode(BinaryTreeNode node, int targetSum, int currentSum) {
		if(node == null) return 0;
		
		currentSum += node.value;
		
		int totalPaths = 0;
		if( currentSum == targetSum) {
			totalPaths++;
		}
		
		System.out.println("node.value " + node.value + " currentSum: " + currentSum);
		
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
		return totalPaths;
	}
	
	private static BinaryTree createBinaryTree() {
	    BinaryTree<Integer> bt = new BinaryTree<Integer>();
	 
	    bt.add(10,null);
	    bt.add(5,null);
	    bt.add(3,null);
	    bt.add(1,null);
	    bt.add(4,null);
	    bt.add(7,null);
	    bt.add(12,null);
	 
	    return bt;
	}
	
	public static void main(String[] args) { 
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		BinaryTree testBT = createBinaryTree();
//		int answer = bt.countPathsWithSumFromNode(testBT.root, 13, 0);
		
//		int[] a = {8,5,1,7,10,12};
//		BinaryTree bt2 = new BinaryTree(a);
//		levelTraversal2(bt2.root);
		
		int[] test = {7, 1, 8, 9, 2};
		BinaryTree<Integer> bt3 = new BinaryTree<Integer>(test);
		BinaryTree<Integer> bt4 = new BinaryTree<Integer>();
		for(int e: test)
			bt4.add(e, null);
//		bt3.preOrderTraversal(bt3.root);
		levelTraversal2(bt3.root);
		levelTraversal2(bt4.root);
//		System.out.print(b);
	}
	
//	int getNext(int n) {
//		int c = n;
//		int c0 = 0;
//		int c1 = 0;
//		while( ((c & 1) == 0) && (c != 0)) {
//			c0 ++;
//			c >>= 1;  // c = c >> 1
//		}
//		
//		while((c & 1) == 1)
//		
//	}
}
