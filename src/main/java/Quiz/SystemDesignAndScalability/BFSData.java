package Quiz.SystemDesignAndScalability;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFSData {

	public Queue<PathNode> toVisit = new LinkedList<PathNode>();
	public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>();
	
	
	
	public BFSData(Person rootPerson) {
		// TODO Auto-generated constructor stub
		PathNode sourceNode = new PathNode(rootPerson, null);
		toVisit.add(sourceNode);
		visited.put(rootPerson.getID(), sourceNode);
	}
	
	public boolean isFinished() {
		return toVisit.isEmpty();
	}

}
