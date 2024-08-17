package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * */

/* Example 1: Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 * */

/* Note: 
 * 1. schedule and schedule[i] are lists with lengths in range [1, 50].
   2. 0 <= schedule[i].start < schedule[i].end <= 10^8.
 * */


public class Quiz759_EmployeeFreeTime {

	// solution idea : It doesn't matter how many people there is because the range only 50 in people and interval number.
	// put all interval to a list and sort by start
	// then, iterate to expand the end tail or get the free time when temp.end < current.start
    // O (nlogn + n + n) > O( nlog(n) )
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
       List<Interval> result = new ArrayList<Interval>();
       List<Interval> timeList = new ArrayList<>();
       
       schedule.forEach(e -> timeList.addAll(e));
       Collections.sort(timeList, ((a, b) -> a.start - b.start));
		
       Interval temp = timeList.get(0);
       for(Interval current: timeList) {
    	   if(temp.end < current.start) { // get the free time gap
    		  result.add(new Interval(temp.end,current.start));
    		  temp = current;
    	   } else {
    		  temp = temp.end > current.end ? temp: current;
    	   }
       }
	   return result;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz759_EmployeeFreeTime quiz = new Quiz759_EmployeeFreeTime();
		List<Interval> employee1 = new ArrayList<Interval>();
		List<Interval> employee2 = new ArrayList<Interval>();
		List<Interval> employee3 = new ArrayList<Interval>();
		employee1.add(quiz.new Interval(1,3));
		employee1.add(quiz.new Interval(6,7));
		employee2.add(quiz.new Interval(2,4));
		employee3.add(quiz.new Interval(2,5));
		employee3.add(quiz.new Interval(9,12));
		
		List<List<Interval>> schedule1 = new ArrayList<List<Interval>>();
		schedule1.add(employee1);
		schedule1.add(employee2);
		schedule1.add(employee3);
		
		List<Interval> freeTimeList = quiz.employeeFreeTime(schedule1);
		freeTimeList.forEach(e -> System.out.println(e.start + "," + e.end));
	
	    employee1.add(quiz.new Interval(15, 3784));
	    employee2.add(quiz.new Interval(125, 374));
	    employee3.add(quiz.new Interval(3788, 4000));
	    
	    List<List<Interval>> schedule2 = new ArrayList<List<Interval>>();
		schedule2.add(employee1);
		schedule2.add(employee2);
		schedule2.add(employee3);
		
		List<Interval> freeTimeList2 = quiz.employeeFreeTime(schedule2);
		freeTimeList2.forEach(e -> System.out.println(e.start + "," + e.end));
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
	
}
