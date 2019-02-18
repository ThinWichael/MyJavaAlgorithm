package DataStructure.Tree;

// �i��: �u�Ʈį�A���Ǩ��X���޽u�G���� threaded Binary Tree
// �l�[ itag , rtag ���V(���Ǩ��X�ɪ�) �e�X�̡B���~��
public class BinaryTreeNode<T> {

	public T data;
	int value;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode(int value ,T data) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreeNode(int value) {
        this.value = value;
		this.data = null;
		this.left = null;
		this.right = null;
	}

}

/* Sorting ��������*/
/* �̤j��n�� �̤p��n�� Min-Heap Max-Heap
 * https://www.geeksforgeeks.org/max-heap-in-java/ */
