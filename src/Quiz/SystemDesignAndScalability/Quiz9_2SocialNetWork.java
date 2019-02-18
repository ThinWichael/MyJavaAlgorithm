package Quiz.SystemDesignAndScalability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

// Social Network : how do you design the data structures for a very large social
// network like FaceBook or LinkedIn? Describe how you design an algorithm to
// show the shortest path between two people (e.g., Me -> Bob -> Susan -> Jason -> You)



public class Quiz9_2SocialNetWork {

	// Answer: Step 1, forget about the Millions of users
	// and use Bidirectional Breath-first search , O(k^q/2 + k^q/2) = O(k^q/2) 
	// the BFSearch is O(k^q)
	
	public LinkedList<Person> findPathBiBFS(HashMap<Integer,Person> allPeople, int source, int destination){

		BFSData sourceData = new BFSData(allPeople.get(source));
		BFSData destData = new BFSData(allPeople.get(destination));
		
		while(!sourceData.isFinished() && !destData.isFinished()){
			/* search out from source*/
			Person collisionFriend = searchLevel(allPeople, sourceData, destData);
			if(collisionFriend != null) {
				return mergePaths(sourceData, destData, collisionFriend.getID());
			}
	        /* search out from destination*/
			collisionFriend = searchLevel(allPeople, destData, sourceData);
			if(collisionFriend != null) {
				return mergePaths(sourceData, destData, collisionFriend.getID()); // I think the order of both data is non-effective
			}
		}
		
		return null; // no friend connection line
	}
	
	/* search one level and return collision if founded */
	public Person searchLevel(HashMap<Integer, Person> allPeople, BFSData primary,
			BFSData secondary) {
		int counts = primary.toVisit.size();
		
		for(int i = 0; i < counts; i++) {
			// pull out first node from queue
			PathNode pathNode = primary.toVisit.poll();
			
			/* check ID if it has already been visited by secondary BFS point*/
			int personId = pathNode.getPerson().getID();
			if(secondary.visited.containsKey(personId)) {
				return pathNode.getPerson(); // Get result !!
			}
			
			/* put all friends to queue and visited Hashmap*/
			ArrayList<Integer> friends = pathNode.getPerson().getFriends();
			for(Integer friendId: friends) {
				if(!primary.visited.containsKey(friendId)) { // filter the duplicated elements
					Person personFriend = allPeople.get(friendId);
					PathNode friendNode = new PathNode(personFriend, pathNode);
					primary.toVisit.add(friendNode);
					primary.visited.put(friendId, friendNode);
				}
			}	
		}
		return null;
	}
	
	/* merge paths between two ends */
	public LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connectionId){
		PathNode pathNode1 = bfs1.visited.get(connectionId);
		PathNode pathNode2 = bfs2.visited.get(connectionId);
		LinkedList<Person> sourceList = pathNode1.collapseToList(false);
		LinkedList<Person> endList = pathNode2.collapseToList(true); // "true" : reversely 
		
		endList.removeFirst(); // remove connection (duplicated)
		sourceList.addAll(endList); // link together
		
		return sourceList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Quiz9_2SocialNetWork() {
		// TODO Auto-generated constructor stub
	}

	
}
