package DataStructure.LinkedList.Dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

// https://www.baeldung.com/java-dijkstra

public class DijkstraImpl {

	public DijkstraImpl() {
		// TODO Auto-generated constructor stub
	}

	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0); // startNode to startNode is 0

		Set<Node> closeSet = new HashSet<>(); // the group that shortest path are fixed
		PriorityQueue<Node> openSet = new PriorityQueue<Node>((a,b) -> ( a.getDistance() - b.getDistance())); // always get the small one 

		openSet.add(source);

		while (openSet.size() != 0) {
			Node curr = openSet.poll();
			
			// loop for every edge
			for (Entry<Node, Integer> edge : curr.getAdjacentNodes().entrySet()) {
				Node next = edge.getKey();
				Integer edgeWeight = edge.getValue();
				
				if (!closeSet.contains(next)) {
					calculateMinimumDistance(next, edgeWeight, curr);
					openSet.add(next);
				}
			}
			closeSet.add(curr); // finish one point
		}
		return graph;
	}

	private static void calculateMinimumDistance(Node target, Integer edgeWeigh, Node source) {
		Integer sDist = source.getDistance(); 
		if (sDist + edgeWeigh < target.getDistance()) { // default of target distance is Integer.MaxValue
			// update distance
			target.setDistance(sDist + edgeWeigh);
			
			LinkedList<Node> shortestPath = new LinkedList<>(source.getShortestPath());
			shortestPath.add(source);
			target.setShortestPath(shortestPath); // memory the footprint
		}
	}

	// replace by piorityQueue !
//	private static Node getLowestDistanceNode(Set<Node> openSet) {
//		Node minDistNode = null;
//		int minDist = Integer.MAX_VALUE;
//		for (Node node : openSet) {
//			int currDist = node.getDistance();
//			if (currDist < minDist) {
//				minDist = currDist;
//				minDistNode = node;
//			}
//		}
//		return minDistNode;
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D"); 
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		 
		nodeA.addDestination(nodeB, 10); // A--B---D
		nodeA.addDestination(nodeC, 15); //  \  \ / \
		                                 //   C  F  /
		nodeB.addDestination(nodeD, 12); //    \ / /
		nodeB.addDestination(nodeF, 15); //     E_/
		 
		nodeC.addDestination(nodeE, 10);
		 
		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);
		 
		nodeF.addDestination(nodeE, 5);
		 
		Graph graph = new Graph();
		 
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		 
		graph = calculateShortestPathFromSource(graph, nodeA);
		printResult(graph);
		
		
//		node D ShortestPath: A---B---
//		node A ShortestPath: 
//		node E ShortestPath: A---B---D---
//		node B ShortestPath: A---
//		node C ShortestPath: A---
//		node F ShortestPath: A---B---D---
		
	}

	static void printResult(Graph graph) {
		for(Node node : graph.nodes) {
			List<Node> list = node.getShortestPath();
			StringBuilder sb = new StringBuilder();
			for(Node e: list) {
				sb.append(e.getName() + "---");
			}
			System.out.println("To node " + node.getName() +" ShortestPath: " + sb);
		} 
		
	}
	
}
