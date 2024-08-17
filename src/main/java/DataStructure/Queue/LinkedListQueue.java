package DataStructure.Queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue {

	public QueueNode<T> first;
	public QueueNode<T> last;
	
	public LinkedListQueue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Object item) {
		QueueNode<T> newNode = new QueueNode<T>((T)item);
		if (last != null) {
			last.next = newNode;
		}
		last = newNode;
		if ( first == null) {
			first = last;
		}
		
	}

	@Override
	public Object poll() { 
		if(first == null) throw new NoSuchElementException();
		
		T data = first.data;
		first = first.next;
		if ( first == null ) {
			last = null;
		}
		return data;
	}

	@Override
	public Object peek() {
		if (first == null) throw new NoSuchElementException();
		return first.data;
	}

	@Override
	public boolean isEmpty() {
		
		return first == null;
	}

}
