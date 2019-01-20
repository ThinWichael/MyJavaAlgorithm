package DataStructure.Tree;

public class BinaryTree {

	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}

	/* inorder traversal ���Ǩ��X*/
	
	void inOrderTraversal(BinaryTreeNode node) {
		if( node != null) {
			inOrderTraversal(node.left);
			visit(node);
			inOrderTraversal(node.right);
		}
	}
	
	/* preorder traversal �e�Ǩ��X*/
	void preOrderTraversal(BinaryTreeNode node) {
		if ( node != null) {
			visit(node);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}
	
	/* postOrder Traversal ��Ǩ��X*/
	void postOrderTraversal(BinaryTreeNode node) {
		if ( node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			visit(node);
		}
	}
	
	void visit(BinaryTreeNode node) {
	    System.out.println(node.data);	
	}
	
}
