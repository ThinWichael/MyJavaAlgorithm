package Quiz.Leetcode.Algorithm.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import MyTool.SpeedTest;

//939. Minimum Area Rectangle

//Given a set of points in the xy-plane, determine the minimum 
//area of a rectangle formed from these points, with sides parallel 
//to the x and y axes.
//If there isn't any rectangle, return 0.

//Note:
//
//1 <= points.length <= 500
//0 <= points[i][0] <= 40000
//0 <= points[i][1] <= 40000
//All points are distinct.

//Example 1:
//
//Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
//Output: 4
//Example 2:
//
//Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
//Output: 2

public class Quiz939_MinimumAreaRectangle {

	public Quiz939_MinimumAreaRectangle() {
		// TODO Auto-generated constructor stub
	}

	// brute force (because N = just 500) O(n^2)
	public int minAreaRect(int[][] points) {
//		Set<> seen = new HashSet<>(points);
		Set<Integer> seen = new HashSet<Integer>();
//		int[] temp = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			seen.add(points[i][0] * 100000 + points[i][1]); // x*10^5 + y
		}
//		Collections.addAll(seen, temp);
		int n = points.length;
		int res = Integer.MAX_VALUE;
		for (int[] p1 : points) {
			for (Integer p2 : seen) {
				int x2 = p2 / 100000;
				int y2 = p2 % 100000;
				Integer x1y2 = p1[0] * 100000 + y2; // (x1, y2)
				Integer x2y1 = x2 * 100000 + p1[1]; // (x2, y1)
				if (seen.contains(x1y2) && seen.contains(x2y1)) {
					// to do
					int area = Math.abs(p1[0] - x2) * Math.abs(p1[1] - y2);
					if (area != 0)
						res = Math.min(area, res);
				}
			}
//			seen.add(p1[0],)
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}

	// solution 2: O (N^1.5) worest case: O (N^2)
	// filter the unnecessary compute
	public int minAreaRect2(int[][] points) {
		int n = points.length;
		int nx, ny;
		Set<Integer> setX = new HashSet<Integer>();
		Set<Integer> setY = new HashSet<Integer>();
		for (int[] e : points) {
			setX.add(e[0]);
			setY.add(e[1]);
		}
		nx = setX.size();
		ny = setY.size();
        if(nx == n || ny == n) return 0;
        
        Map<Integer,ArrayList<Integer>> p = new HashMap<Integer , ArrayList<Integer>>();
        if( nx > ny ) { // x as key
        	for(int[] ele: points) { 
        		if(!p.containsKey(ele[0])) p.put(ele[0], new ArrayList<Integer>());
        		p.get(ele[0]).add(ele[1]);
        	}
        } else { // y as key
        	for(int[] ele: points) {
        		if(!p.containsKey(ele[1])) 
        			p.put(ele[1], new ArrayList<Integer>());
        		
        		p.get(ele[1]).add(ele[0]);
        	}
        }
        
        Map<String, Integer> lastX = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for(int x : p.keySet()) {
        	ArrayList<Integer> arY = p.get(x); // y arraylist
        	Collections.sort(arY);
        	
        	for(int i = 0; i < arY.size(); i++) {
        		for(int j = 0; j < i; j++) {
        			int y1 = arY.get(j);
        			int y2 = arY.get(i);
        			if(lastX.containsKey(y1 + "," + y2)) {
        				int x2 = lastX.get(y1 + "," + y2);
        			    int area = (x - x2) * (y2 -y1);
        				if(area != 0) res = Math.min(res, area);
        			}
        			lastX.put(y1 + "," + y2, x); // for pairing x
        		}
        	}	
        }
        
        return res == Integer.MAX_VALUE? 0: res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz939_MinimumAreaRectangle q = new Quiz939_MinimumAreaRectangle();

		int[][] test1 = { { 1, 1 }, { 1, 3 }, { 3, 1 }, { 3, 3 }, { 2, 2 } };
		System.out.println(q.minAreaRect(test1));

		int[][] test2 = { { 1, 1 }, { 1, 3 }, { 3, 1 }, { 3, 3 }, { 4, 1 }, { 4, 3 } };
		System.out.println(q.minAreaRect(test2));

//		Big case
		int[][] test3 = new int[500][2];
		for (int[] e : test3) {
			Random r = new Random();
//			int a = r.nextInt(40001);
//			int b = r.nextInt(40001);
			int a = r.nextInt(201);
			int b = r.nextInt(201);
			e[0] = a;
			e[1] = b;
		}

		SpeedTest st = new SpeedTest();
		st.start();
		System.out.println(q.minAreaRect(test3));
		st.end();
		
		System.out.println("== method 2 ==");
		System.out.println(q.minAreaRect2(test1));
		System.out.println(q.minAreaRect2(test2));
		st.start();
		System.out.println(q.minAreaRect2(test3));
		st.end();
//		4
//		2
//		637
//		excution time : 25ms
//		== method 2 ==
//		4
//		2
//		637
//		excution time : 2ms
	}

}
