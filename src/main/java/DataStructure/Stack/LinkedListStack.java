package DataStructure.Stack;

import java.util.EmptyStackException;

// "linked-list Stack"

public class LinkedListStack<T> implements Stack {

	public StackNode<T> top = null;
	
	public LinkedListStack() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object pop() {
		if ( top == null) 
			throw new EmptyStackException();
		
		T item = top.data;
		top = top.next;
		return item;
	}

	@Override
	public void push(Object data) {
		// TODO Auto-generated method stub
		StackNode<T> sn = new StackNode<T>((T)data);
		sn.next = top;
		top = sn;
	}
	
	@Override
	public Object peek() {
		if (top == null) 
			throw new EmptyStackException();
		
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		
		return top == null;
	}

	

}
