package Quiz.SortandSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* Group Anagrams:
 * write a method to sort an array of strings so that all the anagrams 
 * are next to each other.
 * 
 * Anagrams are two words that have same characters but in different order.
 * 
 * { ten, you, care , race, net} => {care, race, net, ten, you}
 * */

/* Solution 1:
 * use comparator ! compare each string after they are sorted by char.
 * 
 * Solution 2:
 *  don't really sort between anagrams words. like {race, care} or {care, race} would be ok
 *  use hashtable
 * */

public class Quiz10_2GroupAnagrams {

	/* Solution 1 
	 * O (n logn)
	 * */

	static class AnagramComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return sortChars(arg0).compareTo(sortChars(arg1));
		}

	}

	public static String sortChars(String s) {
		char[] charAry = s.toCharArray();
		Arrays.sort(charAry);
		return new String(charAry);
	}

	/*
	 * Solution 2 : use hashtable to group anagrams words, then put back to array in
	 * order
	 *  O(n)
	 */

	public static void sortByAnagrams(String[] words) {

		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> list;
		for (String word : words) {
			String key = sortChars(word);

			if (mapList.get(key) == null) {
				list = new ArrayList<String>();
				list.add(word);
			} else {
				list = mapList.get(key);
				list.add(word);
			}
			mapList.put(key, list);
		}

		int i = 0;
		for (String key : mapList.keySet()) {
			ArrayList<String> li = mapList.get(key);
			for (String ele : li) {
				words[i] = ele;
				i++;
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = { "ten", "you", "care", "race", "net", "year", "ball", "yera", "yuo" };

		// Solution 1
		System.out.println("before : " + Arrays.toString(words));
		Arrays.sort(words, new AnagramComparator());
		System.out.println("after : " + Arrays.toString(words));

		// Solution 2  (better than solution 1 in this case)
		System.out.println("before : " + Arrays.toString(words));
		sortByAnagrams(words);
		System.out.println("after : " + Arrays.toString(words));

	}

}
