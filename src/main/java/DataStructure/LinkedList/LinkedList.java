package DataStructure.LinkedList;

//°Ñ·Ó http://emn178.pixnet.net/blog/post/93557502-%E9%80%A3%E7%B5%90%E4%B8%B2%E5%88%97%28linked-list%29

abstract class LinkedList<T> { // can be use as a Queue

    public int count;
    public Node<T> first;
    public Node<T> last;
    
	abstract public void addFirst(T data);
	abstract public void addLast(T data);
	abstract public void addBefore(Node<T> node, T data);
	abstract public void addAfter(Node<T> node, T data);
	abstract public void remove(Node<T> node);
	abstract public void removeFirst();
	abstract public void removeLast();
}


