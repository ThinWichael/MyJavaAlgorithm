package DataStructure.LinkedList;

// parent Parameter: 
//public int count;
//public Node<T> First;
//public Node<T> Last;

public class SinglyLinkedList<T> extends LinkedList<T> {

	public SinglyLinkedList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addFirst(T data) {
		
		Node<T> node = new Node<T>(data);
		
		if ( count == 0) {
			last = node;
		} else {
			node.next = first;
		}
		
		first = node;
		++count;
		
	}

	@Override
	public void addLast(T data) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(data);
		if ( count == 0) {
			first = node;
		} else {
			last.next = node;
		}
		
		last = node;
		++ count;
	}

	@Override
	public void addBefore(Node<T> node, T data) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<T>(data);
		if ( node == first) {
			first = newNode;
		} else {
			Node<T> preNode = findPreviousNode(node);
		    preNode.next = newNode;
		}
		newNode.next = node;
		++count;
	}

	@Override
	public void addAfter(Node<T> node, T data) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<T>(data);
		newNode.next = node.next;
		node.next = newNode;
		if (node == last) {
			last = newNode;
		}
		++count;
			
	}

	@Override
	public void remove(Node<T> node) {
		// TODO Auto-generated method stub
		if(node == first)
			removeFirst();
		else if ( node == last) 
			removeLast();
		else {
			Node<T> preNode = findPreviousNode(node);
			if ( preNode == null)
				throw new IndexOutOfBoundsException();
			preNode.next = node.next;
			node.next = null;
			--count;
		}
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		if ( count == 0)
			throw new IndexOutOfBoundsException();
		else if ( count == 1) {
			first = null;
			last = null;
		} else {
			Node<T> node = first.next;
			first.next = null;
			first = node;
		}
		--count;
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		if (count == 0)
			throw new IndexOutOfBoundsException();
		else if ( count == 1) {
			first = null;
			last = null;
		} else {
			Node<T> node = findPreviousNode(last);
			node.next = null;
			last = node;
		}
		--count;
	}

	private Node<T> findPreviousNode(Node<T> node) {
		Node<T> preNode = first;
		while (preNode != null) {
			if ( node == preNode.next) 
				break;
			preNode = preNode.next;
		}
		
		return preNode;
	}
}
