package DataStructure.Tree;

// 進階: 優化效能，中序走訪的引線二元樹 threaded Binary Tree
// 追加 itag , rtag 指向(中序走訪時的) 前驅者、後繼者
public class BinaryTreeNode<T> {

	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	
	public BinaryTreeNode() {
		// TODO Auto-generated constructor stub
	}

}

/* Sorting 相關應用*/
/* 最大堆積樹 最小堆積樹 Min-Heap Max-Heap
 * https://www.geeksforgeeks.org/max-heap-in-java/ */
