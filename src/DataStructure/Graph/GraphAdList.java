package DataStructure.Graph;
import java.io.IOException;
import java.util.*;
// directed Graph / undirected Graph

public class GraphAdList<V> {

	Map<V, List< EdgeNode<V>>> graphMap = new HashMap<V, List< EdgeNode<V> >>();
	int edges;
	
	public GraphAdList() {
		// TODO Auto-generated constructor stub
	}

	/*
	 *  Add a vertex to the graph
	 * */
	public void add(V v) {
		if ( graphMap.containsKey(v)) 
			return;
		graphMap.put(v, new ArrayList<EdgeNode<V>>());
	}
	
	public void add(V from, V to, int weight) {
		this.add(from);
		this.add(to);
		graphMap.get(from).add(new EdgeNode<V>(to, weight));
	}
	
	public int getCountsOfEdges() {
		int sum = 0;
		for( List<EdgeNode<V>> outBounds : graphMap.values()) {
			sum += outBounds.size();
		}
		return sum;
	}
	/* 外分支度 */
	public int outDegreeOf(V v) {
		return graphMap.get(v).size();
	}
	
	public int inDegreeOf(V v) {
		return inBoundNeighborsOf(v).size();
	}
	
	public List<V> outBoundNeighborsOf(V v) {
		List<V> outList = new ArrayList<V>();
		for( EdgeNode<V> ed : graphMap.get(v)) {
			outList.add(ed.vertex);
		}
		return outList;
	}
	/*
	 * 因沒有建立反向鏈結，所以需要n*m 去找出指定vertex的內分支度
	 * */
	public List<V> inBoundNeighborsOf(V v) {
		List<V> inList = new ArrayList<V>();
		for (V to : graphMap.keySet()) {
			for ( EdgeNode e : graphMap.get(to)) {
				if( e.vertex.equals(v))
					inList.add(to);
			}
		}
		return inList;
	}
	
	public boolean contains(V v) {
		return graphMap.containsKey(v);
	}
	
	public boolean isEdge(V from, V to) {
		for (EdgeNode<V> edge : graphMap.get(from)) {
			if(edge.vertex.equals(to))
				return true;
		}
		return false;
	}
	
	public int getWeight(V from, V to) {
		for (EdgeNode<V> edge : graphMap.get(from)) {
			if(edge.vertex.equals(to))
				return edge.weight;
		}
		return -1;
	}
	
	/**
     * print graph.
     */
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (V v : graphMap.keySet())
            s.append("\n    " + v + " -> " + graphMap.get(v));
        return s.toString();
    }
	
    /* DFS Depth-First Search */
    void DFSsearch(V rootNode) {
    	
    	Map<V , Boolean> visited = new HashMap<V, Boolean>(); 
    	for (V vertex: graphMap.keySet()) {
    		visited.put(vertex, false);
    	}
    	
    	DFS(rootNode ,visited);
    	
    }
    
    private void DFS(V node,Map<V , Boolean> visited) {
    	
    	if(node == null) return;
    	visit(node);
    	visited.replace(node, true);
    	
    	for( EdgeNode<V> edge: graphMap.get(node)) {
    		if( visited.get(edge.vertex) == false) { 
    			DFS(edge.vertex , visited);
    		}
    	}
    	
    }
    
    /* BFS Breadth-First Search */
    void BFSsearch(V node) {
    	
    	Map<V , Boolean> visited = new HashMap<V, Boolean>(); 
    	for (V vertex: graphMap.keySet()) {
    		visited.put(vertex, false);
    	}
    	
    	Queue<V> queue = new ArrayDeque<V>();
    	
    	BFS( node, visited, queue);

    }
    // No recursive in BFS;
    private void BFS (V node, Map<V , Boolean> visited , Queue<V> queue) { 
    	visited.replace(node, true);
    	queue.add(node);
    	
    	while(!queue.isEmpty()) {
    		V node_ = queue.poll();
    		System.out.println("BFS: " + node_); // or any action
    		
    		for( EdgeNode<V> edge :graphMap.get(node_)) {
    			if(visited.get(edge.vertex) == false) {
    				visited.replace(edge.vertex, true);
    				queue.add(edge.vertex);
    			}
    		}
    	}
    }

    
    void visit (V vertex) {
    	System.out.println("DFS: " + vertex);
    }
    
    public static void main(String[] args) throws IOException {

    	GraphAdList<Integer> graph = new GraphAdList<Integer>();

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);

        graph.add(0, 1, 1);
        graph.add(1, 2, 2);
        graph.add(2, 3, 2);
        graph.add(3, 0, 2);
        graph.add(1, 3, 1);
        graph.add(2, 1, 5);
        
        graph.DFSsearch(0);
        graph.BFSsearch(0);
        
        System.out.println("======Graph 2======");
        
        GraphAdList<Integer> graph2 = new GraphAdList<Integer>();

        graph2.add(0);
        graph2.add(1);
        graph2.add(2);
        graph2.add(3);
        graph2.add(4);
        graph2.add(5);
        
        graph2.add( 0, 1, 1);
        graph2.add( 0, 2, 1);
        graph2.add( 0, 3, 1);
        graph2.add( 2, 4, 1);
        graph2.add( 2, 5, 1);
        
        graph2.BFSsearch(0);
        graph2.DFSsearch(0);

        
//        GraphAdList<VertexNode<Integer>> graph = new GraphAdList<VertexNode>();


//        System.out.println("The nr. of vertices is: " + graph.neighbors.keySet().size());
//        System.out.println("The nr. of edges is: " + graph.getNumberOfEdges()); // to be fixed
//        System.out.println("The current graph: " + graph);
//        System.out.println("In-degrees for 0: " + graph.inDegree(0));
//        System.out.println("Out-degrees for 0: " + graph.outDegree(0));
//        System.out.println("In-degrees for 3: " + graph.inDegree(3));
//        System.out.println("Out-degrees for 3: " + graph.outDegree(3));
//        System.out.println("Outbounds for 1: "+ graph.outboundNeighbors(1));
//        System.out.println("Inbounds for 1: "+ graph.inboundNeighbors(1));
//        System.out.println("(0,2)? " + (graph.isEdge(0, 2) ? "It's an edge" : "It's not an edge"));
//        System.out.println("(1,3)? " + (graph.isEdge(1, 3) ? "It's an edge" : "It's not an edge"));
//
//        System.out.println("Cost for (1,3)? "+ graph.getCost(1, 3));


    }
}
    
    
