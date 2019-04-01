package Quiz.Leetcode.Algorithm.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//635. Design Log Storage System
//Medium

//You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
//
//Design a log storage system to implement the following functions:
//
//void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
//
//
//int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
//
//Example 1:
//put(1, "2017:01:01:23:59:59");
//put(2, "2017:01:01:22:59:59");
//put(3, "2016:01:01:00:00:00");
//retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
//retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
//Note:
//There will be at most 300 operations of Put or Retrieve.
//Year ranges from [2000,2017]. Hour ranges from [00,23].
//Output for Retrieve has no order required.

public class Quiz635_DesignLogSystem {
    // tips : store in string, compare in integer
	// tips 2 : we dosen't treat it as a up-scaled question
	class LogSystem {
		TreeMap<String, Integer> logs = new TreeMap<String, Integer>();
	    List<String> graList = Arrays.asList(new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second"});
	    int[] range = new int[] {4,7,10,13,16,19};
	    public LogSystem() { 	
	    }
	    
	    public void put(int id, String timestamp) {
	    	logs.put(timestamp, id);
	    }
	    
	    public List<Integer> retrieve(String s, String e, String gra) {
	        ArrayList<Integer> res = new ArrayList<Integer>();
	        int pos = range[graList.indexOf(gra)];
	        String blank = "0000:00:00:00:00:00";
	        String blank2= "0000:12:31:23:59:59";
	        String start = s.substring(0, pos)+ blank.substring(pos);
	        String end = e.substring(0,pos) + blank2.substring(pos);
	        String sKey = logs.ceilingKey(start);
	        String eKey = logs.floorKey(end);
	        if( sKey == null || eKey== null || eKey.compareTo(sKey) < 0) return res;
	        
	        SortedMap<String, Integer> sub = logs.subMap(sKey, true, eKey, true);//(fromKey, fromInclusive,toKey, toInclusive)
	        for(String k: sub.keySet()) {
	        	res.add(sub.get(k));
	        }
	        return res;
	    }
	}
	
	public Quiz635_DesignLogSystem() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz635_DesignLogSystem q = new Quiz635_DesignLogSystem();
		LogSystem logSystem = q.new LogSystem();
		logSystem.put(1, "2017:01:01:23:59:59");
		logSystem.put(2, "2017:01:01:22:59:59");
		logSystem.put(3, "2016:01:01:00:00:00");
		List<Integer> res1 = logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
		List<Integer> res2 = logSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Second");  // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
	
	    System.out.println(res1.toString());
	    System.out.println(res2.toString());
	    
	    LogSystem logSystem2 = q.new LogSystem();
	    logSystem2.put(1, "2017:01:01:23:59:59");
	    List<Integer> res3 = logSystem2.retrieve("2017:01:01:23:59:58","2017:01:01:23:59:58","Second");
	    System.out.println(res3.toString());
	}

}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
