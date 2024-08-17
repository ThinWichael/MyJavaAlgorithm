package Quiz.Leetcode.Algorithm.Medium;

import java.util.Arrays;
import java.util.TreeSet;

//855. Exam Room
//Medium
//
//In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
//
//When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
//
//Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p. 
//
//Example 1:
//
//Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
//Output: [null,0,9,4,2,null,5]
//Explanation:
//ExamRoom(10) -> null
//seat() -> 0, no one is in the room, then the student sits at seat number 0.
//seat() -> 9, the student sits at the last seat number 9.
//seat() -> 4, the student sits at the last seat number 4.
//seat() -> 2, the student sits at the last seat number 2.
//leave(4) -> null
//seat() -> 5, the student sits at the last seat number 5.
//​​​​​​​
//
//Note:
//
//1 <= N <= 10^9
//ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
//Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

public class Quiz855_ExamRoom {

	public Quiz855_ExamRoom() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz855_ExamRoom q = new Quiz855_ExamRoom();
		ExamRoom room = q.new ExamRoom(10);
		System.out.println(room.seat());
		System.out.println(room.seat());
		System.out.println(room.seat());
		System.out.println(room.seat());
		room.leave(9);

	}

	
	
	class ExamRoom {
		int n;
		TreeSet<Integer> students; // self balancing tree, always keep integer in order
		public ExamRoom(int N) {
			this.students = new TreeSet<Integer>();
			n = N;
//	        this.person = new Integer[N+1];
		}

		public int seat() {
			int student = 0;
			
			if(students.size() > 0) {
				// compare each two seats
				int dMax = students.first(); // distance between new point and left site
				Integer pre = null; // pre-seat
				for(Integer s: students) {
					if(pre != null) {
						int d = (s - pre)/2;
						if(d > dMax) {
							dMax = d;
							student = pre + dMax; // update to dMax position
						}
					}
					
					pre = dMax; // start form left-most one
				}
				
				// right corner case
				if( (n - 1) - students.last() > dMax) {
					student = n - 1;
				}
			}
			
			students.add(student);
			return student;
		}

		public void leave(int p) {
			students.remove(p);
		}
	}

}
