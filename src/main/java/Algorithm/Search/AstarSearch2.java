package Algorithm.Search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//Node version from https://gist.github.com/raymondchua/8064159

public class AstarSearch2 {

	public AstarSearch2() {
		// TODO Auto-generated constructor stub
	}

    public static void findShortestPath2(Node start, Node goal) {
		// close data (HashSet)
    	Set<Node> closeSet = new HashSet<Node>();
    	// open data (PiorityQueue)
    	PriorityQueue<Node> pq = new PriorityQueue<Node>(20,
    			(a,b) -> (a.f - b.f));
    	
    	// put start in pq 
    	start.g = 0;
    	pq.add(start);
    	//while loop
    	while(!pq.isEmpty()) {
    		Node curr = pq.poll();
    		
    		if(curr.name.equals(goal.name)) break;
    		
    		for(Edge e: curr.adj) {
    			Node next = e.to;
    			int temp_g = curr.g + e.cost;
    			int temp_f = temp_g + next.h; // f = g + h
    			
    			if(closeSet.contains(next) && temp_f >= next.f) continue; // want one with smaller f 
    			if(pq.contains(next) && temp_f >= next.f) continue;
    			
    			next.parent = curr; // footprint at parent
    			next.g = temp_g;
    			next.f = temp_f;
    					
    			pq.add(next);
    		}
    		closeSet.add(curr);
    	}
	}
	
    class Node {
    	
    	final String name; 
    	
    	int f = 0;
    	int g;
    	final int h;
    	
    	public Node parent;
    	public List<Edge> adj; // adjacent node
    	
    	Node(String name, int h){
    		this.name = name;
    		this.h = h;
    	}
    }
    
    class Edge {
    	final Node to;
    	final int cost;
    	
    	Edge(Node target, int cost) {
    		this.to = target;
    		this.cost = cost;
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AstarSearch2 aa = new AstarSearch2();
		// test from https://gist.github.com/raymondchua/
		
		//initialize the graph base on the Romania map
        Node n1 = aa.new Node("Arad",366);
        Node n2 = aa.new Node("Zerind",374);
        Node n3 = aa.new Node("Oradea",380);
        Node n4 = aa.new Node("Sibiu",253);
        Node n5 = aa.new Node("Fagaras",178);
        Node n6 = aa.new Node("Rimnicu Vilcea",193);
        Node n7 = aa.new Node("Pitesti",98);
        Node n8 = aa.new Node("Timisoara",329);
        Node n9 = aa.new Node("Lugoj",244);
        Node n10 = aa.new Node("Mehadia",241);
        Node n11 = aa.new Node("Drobeta",242);
        Node n12 = aa.new Node("Craiova",160);
        Node n13 = aa.new Node("Bucharest",0);
        Node n14 = aa.new Node("Giurgiu",77);

        //initialize the edges

        //Arad
        n1.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n2,75),
        		aa.new Edge(n4,140),
        		aa.new Edge(n8,118)});
         
         //Zerind
        n2.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n1,75),
        		aa.new Edge(n3,71)
        });
         

         //Oradea
        n3.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n2,71),
        		aa.new Edge(n4,151)
        });
         
         //Sibiu
        n4.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n1,140),
        		aa.new Edge(n5,99),
        		aa.new Edge(n3,151),
        		aa.new Edge(n6,80),
        });
         

         //Fagaras
        n5.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n4,99),

                //178
        		aa.new Edge(n13,211)
        });
         
         //Rimnicu Vilcea
        n6.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n4,80),
        		aa.new Edge(n7,97),
        		aa.new Edge(n12,146)
        });
         
         //Pitesti
        n7.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n6,97),
        		aa.new Edge(n13,101),
        		aa.new Edge(n12,138)
        });
         
         //Timisoara
        n8.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n1,118),
        		aa.new Edge(n9,111)
        });
         
         //Lugoj
        n9.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n8,111),
        		aa.new Edge(n10,70)
        });

         //Mehadia
        n10.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n9,70),
        		aa.new Edge(n11,75)
        });
         
         //Drobeta
        n11.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n10,75),
        		aa.new Edge(n12,120)
        });

         //Craiova
        n12.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n11,120),
        		aa.new Edge(n6,146),
        		aa.new Edge(n7,138)
        });

        //Bucharest
        n13.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n7,101),
        		aa.new Edge(n14,90),
        		aa.new Edge(n5,211)
        });
         
         //Giurgiu
        n14.adj = Arrays.asList(new Edge[]{
        		aa.new Edge(n13,90)
        });
        
        findShortestPath2(n1,n13);
        printPath(n13);
	}

	public static void printPath(Node goal) {
		for(Node curr = goal; curr != null; curr = curr.parent) {
			if(curr == goal)System.out.print("[Goal: " + curr.name + " ]");
			else System.out.print( "--" + curr.name + "--");	
		}
	}
	
}
