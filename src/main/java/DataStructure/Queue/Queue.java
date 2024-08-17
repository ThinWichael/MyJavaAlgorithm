package DataStructure.Queue;

public interface Queue<T> {
	
   public void add(T item);
   public T poll();
   public T peek();
   public boolean isEmpty();
}
