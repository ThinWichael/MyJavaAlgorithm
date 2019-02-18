package Quiz.SystemDesignAndScalability;

import java.util.LinkedList;

/* equal to EdgeNode */ 

public class PathNode {

	private Person person = null ;
	private PathNode previousNode = null ;
	
	public PathNode(Person p, PathNode previous) {
		this.person = p;
		this.previousNode = previous;
	}
	
	public Person getPerson() {
		return this.person;
	}
	
	public LinkedList<Person> collapseToList(boolean startWithRoot) {
		LinkedList<Person> path = new LinkedList<Person>();
		PathNode node = this;
		
		while(node != null) {
			if (startWithRoot) {
				path.addLast(node.person);
			} else {
				path.addFirst(node.person);
			}
			node = node.previousNode;
		}
		return path;
	}

}
