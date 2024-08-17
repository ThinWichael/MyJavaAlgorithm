package DataStructure.Stack;

// there are two kind of stack, sequential storage and linked list.


public interface Stack<T> {
	
	public T pop();
	public void push(T data);
	public T peek();
	public boolean isEmpty();
}
