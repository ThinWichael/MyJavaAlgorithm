package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Quiz.Leetcode.Algorithm.Hard.Quiz642_AutoCompleteSystem.AutocompleteSystem.TrieNode;

// 527. Word Abbreviation
//
// Given an array of n distinct non-empty strings, you need to generate minimal possible 
// abbreviations for every word following rules below.
//
// Begin with the first character and then the number of characters abbreviated, which 
//followed by the last character.
//
//If there are any conflict, that is more than one words share the same abbreviation, 
//a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. 
//In other words, a final abbreviation cannot map to more than one original words.
//
//If the abbreviation doesn't make the word shorter, then keep it as original.
//Example:
//Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", 
//"intrusion"]
//Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
//Note:
//Both n and the length of each word will not exceed 400.
//The length of each word is greater than 1.
//The words consist of lowercase English letters only.
//The return answers should be in the same order as the original array.

public class Quiz527_WordAbbreviation {

	public Quiz527_WordAbbreviation() {
		// TODO Auto-generated constructor stub
	}

	// solution : by @mgispk and @jocelynayoga
	// Using a HashMap and Trie to solve in O(nL), L is the average length of words

	public List<String> wordsAbbreviation(List<String> libary) {
		// null case
		if(libary == null || libary.size() == 0) return null;
		// result List
		List<String> result = new LinkedList<String>();	
        HashMap<String, List<Integer>> abbrsMap = new HashMap<>();
	    // loop for N, initial result and same abbreviation map
		int n = libary.size();
        for(int i = 0; i < n; i++) {
        	result.add(libary.get(i)); // copy to result
        	// build List with same simple Abbreviation
        	String shortest = getAbbr(libary.get(i));
        	if(!abbrsMap.containsKey(shortest)) {
        		abbrsMap.put(shortest, new ArrayList<Integer>());
        	}
        	abbrsMap.get(shortest).add(i);
        }
        
		// Loop and handle conflict with Trie 
	    for(int i = 0; i < libary.size(); i++) {
	    	String shortest = getAbbr(libary.get(i));
	    	if (abbrsMap.get(shortest).size() == 1) {
	    		result.set(i, shortest);
	    	} else {
	    		conflictHelp(abbrsMap, shortest, result, libary);
	    	}
	    }
	    
	    return result;
	}
	
	private static String getAbbr(String s) {
    	if( s == null || s.length() <= 3) {
    		return s;
    	}
    	StringBuilder st = new StringBuilder();
    	st.append(s.charAt(0));
    	st.append(s.length() -2);
    	st.append(s.charAt(s.length() -1 ));
    	return st.toString();
    }

	// handle conflict with Trie
	private void conflictHelp(HashMap<String, List<Integer>> map, String shortest, List<String> result, List<String> libary) {
		// build a Trie (prefix tree) , google Trie !!
		TrieNode root = new TrieNode();
				
		for(int index: map.get(shortest)) {
           String str = libary.get(index);
           TrieNode node = root;
           
           for(int i=0; i < str.length(); i++) {
        	   char cur = str.charAt(i);
        	   if(node.children[cur - 'a'] == null) {
        		   node.children[cur - 'a'] = new TrieNode();
        	   }
        	   node = node.children[cur - 'a'];
        	   node.count++;
           }
		}
		
		for(int index: map.get(shortest)) {
			String str = libary.get(index);
			// to find the repeat length, i, between each words in Trie.
			int i = 0;
			TrieNode node = root;
			while(i < str.length() && node.children[str.charAt(i) - 'a'].count > 1) {
				node = node.children[str.charAt(i) - 'a']; // next node
				i++;
			}
			
			if(i >= str.length() - 3) { // almost all are repeat , no need to do abbreviation
				result.set( index ,str);
			} else {
				StringBuilder st = new StringBuilder();
				st.append(str.substring(0, i + 1));
				st.append(str.length() - 1 -i - 1); //abbreviated non repeat number, -tail -repeat -(a display non-repeat unit)  
				st.append(str.charAt(str.length() - 1));
				result.set(index, st.toString());
			}
		}
	}
	
	class TrieNode {
		int count;
		TrieNode[] children; 
		// mathematics concept prefix node . Otherwise, can use HashMap<Character,TrieNode> children;
		
		TrieNode(){
			children = new TrieNode[26];
			count = 0;
		};
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz527_WordAbbreviation q = new Quiz527_WordAbbreviation();
		String[] test1 = {"like", "god", "internal", "me", "internet",
				"interval", "intension", "face", "intrusion"};
		List<String> test1List = new ArrayList<String>();
		for(String s:test1) test1List.add(s);
		List<String> abbresResult = q.wordsAbbreviation(test1List);
		abbresResult.forEach(e -> System.out.print(e + " "));
		
	}

}
