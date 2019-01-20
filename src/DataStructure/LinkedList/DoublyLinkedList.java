package DataStructure.LinkedList;

public class DoublyLinkedList<T> extends LinkedList<T> {

	public DoublyLinkedList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addFirst(T data) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(data);
        if (count == 0)
            last = node;
        else
        {
            node.next = first;
            first.previous = node;
        }
        first = node;
        ++count;
	}

	@Override
	public void addLast(T data) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(data);
        if (count == 0)
            first = node;
        else {
            last.next = node;
            node.previous = last;
        }
        last = node;
        ++count;
	}

	@Override
	public void addBefore(Node<T> node, T data) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<T>(data);
		newNode.previous = node.previous;
		node.previous.next = newNode;
		node.previous = newNode;
		if ( node == first) {
			first = newNode;
		}
		++count;
	}

	@Override
	public void addAfter(Node<T> node, T data) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<T>(data);
        newNode.next = node.next;
        node.next.previous = newNode;
        node.next = newNode;
        newNode.previous = node;
        if (node == last)
        {
            last = newNode;
        }
        ++count;
	}

	@Override
	public void remove(Node<T> node) {
		// TODO Auto-generated method stub
		if (node == first)
            removeFirst();
        else if (node == last)
            removeLast();
        else
        {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            node.next = null;
            node.previous = null;
            --count;
        }
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		if (count == 0)
            throw new IndexOutOfBoundsException();
        else if (count == 1)
        {
            first = null;
            last = null;
        }
        else
        {
            Node<T> node = first.next;
            first.next = null;
            node.previous = null;
            first = node;
        }
        --count;
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		if (count == 0)
            throw new IndexOutOfBoundsException();
        else if (count == 1)
        {
            first = null;
            last = null;
        }
        else
        {
            Node<T> node = last.previous;
            last.previous = null;
            node.next = null;
            last = node;
        }
        --count;
	}

}
