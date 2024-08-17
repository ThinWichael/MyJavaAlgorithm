package Quiz.Leetcode.Algorithm.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//You have a list of words and a pattern, and you want to know which words in words matches the pattern.
//
//A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
//
//(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
//
//Return a list of the words in words that match the given pattern. 
//
//You may return the answer in any order.

//Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
//Output: ["mee","aqq"]
//Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
//"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
//since a and b map to the same letter.

//Note:
//
//1 <= words.length <= 50
//1 <= pattern.length = words[i].length <= 20

public class Quiz890_FindPattern {

	public Quiz890_FindPattern() {
		// TODO Auto-generated constructor stub
	}

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        int pLength = pattern.length();
        ArrayList<String> result = new ArrayList<String>();    	
    	
        // transform to Number array
        HashMap<String, String> map1 = new HashMap<String, String>();
        StringBuilder sb1 = new StringBuilder();
        
        int j = 1;
        for(char ca: pattern.toCharArray()) {
        	if( !map1.containsKey(String.valueOf(ca))) {
        		map1.put(String.valueOf(ca), Integer.toString(j));
        		j++;
        	}
        	sb1.append( map1.get(String.valueOf(ca)) );
        }
        
        // compare by length and pattern
        HashMap<String, String> map2;
        StringBuilder sb2;
        for(String word: words) {
        	if( word.length() == pLength) {
        		map2 = new HashMap<String, String>();
        		sb2 = new StringBuilder();
        		int jj = 1;
        		for(char ca: word.toCharArray()) {
        			if(!map2.containsKey(String.valueOf(ca))){
        				map2.put(String.valueOf(ca), Integer.toString(jj));
        				jj++;
        			}
        			sb2.append( map2.get( String.valueOf(ca) ) );
        		}
        		
        		if(sb2.toString().equals(sb1.toString())) result.add(word);
        	}
        }
        
        return result;
    }
	
    // better solution style
    public static List<String> findAndReplacePattern_(String[] words, String pattern) {
        List<String> res = new ArrayList();
        for (String word : words) {
            if (match(word, pattern))   res.add(word);
        }
        return res;
    }
    
    static boolean match(String word, String pattern) {
        Map<Character, Character> map = new HashMap();
        
        for(int i = 0; i < word.length(); ++i) {
            char p = pattern.charAt(i);
            char w = word.charAt(i);
            if (!map.containsKey(p)) map.put(p, w);
            if (map.get(p) != w)    return false;
        }
        
        Set<Character> set = new HashSet();
        for (char c : map.values()) {
            if (!set.add(c)) return false;
        }
        
        return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String[] words1 = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern1 = "abb";
        
        List<String> result1 = findAndReplacePattern_(words1 , pattern1);
        System.out.println(result1.toString());
        
        String[] words2 = {"abc","deq","meeee","aqqcvrr","dkd","ccc", "ieee"};
        String pattern2 = "nbbb";
        
        List<String> result2 = findAndReplacePattern(words2 , pattern2);
        System.out.println(result2.toString());
	}

}
