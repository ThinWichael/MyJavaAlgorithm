import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;



class Result2 {
	static Result2 result = new Result2();
	static MyComparator myComparator = result.new MyComparator("thisisaReference");
	static ArrayList<Integer> chainCountsAry = new ArrayList<Integer>();
    /*
     * Complete the 'longestChain' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY words as parameter.
     */

    public static int longestChain(List<String> words) {
    // Write your code here
    	
    	return 5;
    }

    public class Word {
    	String word;
    	Integer wordLength;
    	ArrayList<String> compareCandidates;
    	Integer chainCounts;
    	
    	Word(String word, ArrayList<String> compareCandidates){
    		
    	}
    	
    }
    
    public class CookingCenter {
    	ArrayList<String> dictionary;// [a, b , c , ab , bb, bc ,abc ,abb , bca]
    	ArrayList<Integer> continuousNumberChain;
    	ArrayList<Integer> chainCountsAry;
    	
    	CookingCenter( ArrayList<String> words ){
    		// sort dictionary ascending
    		Collections.sort(words, myComparator);
    		this.dictionary = words;
    	}
    	
    	public int checkDictionary() {
        	
    		checkPattern();
    		
            // make dictionary to many subDictionary according to this continuousNumberChain
        	
        	// each subDictionary do recursively finding the string chain and save it
    		
    		// goRecrusivelyCheck()
			return 1;
        }
    	
    	public void goRecrusivelyCheck (Integer index){
    		String str = dictionary.get(index);
    		
    		for(Integer i = 0; i < str.length(); i++){
    			String result = str.substring(0, i) + str.substring(i+1);
    			
    			for(String str_: dictionary){
    				if(str_.length() == str.length() - 1 && 
    						result.equalsIgnoreCase(str_)){
//    					checkRemoveLetter();
    				}
    			}
    		}
    	}
    	
    	public void checkPattern(){
        	ArrayList<Integer> list = new ArrayList<Integer>(dictionary.size());
        	
        	for(String word: dictionary) {
        		list.add(word.length());
        	}
        	
        	Collections.sort(list); // ascending
        	
        	// �s��Ʀr�� [10,8,9,4,...,3] �����Ʀr�[�_�Ӥ��W�L 60
        	continuousNumberChain = new ArrayList<Integer>();
        	
        	Integer lastNumber = null;
        	Integer counts = 0;
        	for(Integer wordsNumber: list) {
        		
        		if(lastNumber == null) {
        			lastNumber = wordsNumber;
        			counts++;
        		} else if(wordsNumber == lastNumber + 1 || wordsNumber == lastNumber){
        			lastNumber = wordsNumber;
        			counts++;
        		} else if(wordsNumber > lastNumber + 1){
        			continuousNumberChain.add(counts);
        			lastNumber = wordsNumber;
        			counts = 1;
        		}
        	}        	
        }
    	
    }
    
    public class MyComparator implements java.util.Comparator<String> {

        private int referenceLength;

        public MyComparator(String reference) {
            super();
            this.referenceLength = reference.length();
        }

        public int compare(String s1, String s2) {
            int dist1 = Math.abs(s1.length() - referenceLength);
            int dist2 = Math.abs(s2.length() - referenceLength);

            return dist1 - dist2;
        }
    }
    
    
        
}

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int wordsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, wordsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        int result = Result2.longestChain(words);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
