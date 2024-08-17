package DataStructure.String;

import java.util.ArrayList;

import static java.lang.Character.*;

public class EncryptAndDecryptQuestion {

    //**Instructions**
    //
    //You are given a string of encrypted text (ciphertext).
    //
    //The encryption algorithm used to create the
    //ciphertext simply shifts all the alphabetic characters
    //in the original (unencrypted) string by the same
    //amount. But you don't know what this amount is.
    //
    //Write the **decipher** function that takes the
    //encrypted string as input, and returns the original,
    //unencrypted string.
    //
    //For example, imagine that the original message was
    //"hello" and we shifted each letter by two. The
    //resulting ciphertext would be "jgnnq".
    //
    //If the original message were "Coding tests are fun
    //and challenging!" and we shifted each character by
    //two, the resulting ciphertext would be "Epi vguvu
    //ctg hwp cpf ejcnngpikpi!"
    //
    //The **decipher** function takes two arguments: the
    //ciphertext, and a word that we know appeared in
    //the original plain text. Using these clues, the
    //function must output the original text.
    //
    //We will follow the English alphabet for this question.
    //Note that the last letter of the alphabet Z will be
    //followed by A. Likewise, z will be followed by a.
    //
    //If the word you are searching for in the original
    //string does not appear there, return "Invalid".
    //
    //**Example 1**
    //Input:
    //ㆍ "Eqfkpi vguvu ctg hwp!"
    //ㆍ "tests"
    //Output:
    //ㆍ "Coding tests are fun!"
    //
    //Explanation:
    //ㆍ "tests" is a five-letter word. In the encrypted
    //string, the only five-letter word is "vguvu".
    //Therefore the encrypted version of "tests"
    //may be "vguvu". On comparing "tests" to
    //"vguvu", it is clear that the encryption process
    //has shifted every character in the plaintext by
    //2. So, the plaintext in this case is "Coding
    //tests are fun!".
    //
    //**Example 2**
    //
    //Input:
    //ㆍ"cdeb nqxg"
    //ㆍ "love"
    //Output:
    //ㆍ "abcz love"
    //
    //Explanation:
    //"In this case, "love" could have been encrypted
    //to either "cdeb" or "nqxg". On closer
    //examination, it is clear that "nqxg" is the
    //correct option, with every character shifted by
    //2. (No such relationship exists between "love"
    //and "cdeb")
    //
    //For this test you're using **Java OpenJDK 15.0.2**
    //Feel free to add comments in your code explaining your
    //solution.

    // solution
//    first, the uppercase and lowercase are matter.
//    second, we can match the known word by the word length and calculation.

    // 1.we prepare a map key-value as (a,1) (b,2) ... (y, 25) (z, 26),(A, 27) ... (Z, 52)
      // no need to do this map, char in the string can do the calculation

    // 2.slice the whole chipherText by " ", and return a string arraylist {"Hello", "HR", "Manager!"}

    // compare your know text with the same length string, if the result is like 55555 the decrypt answer is 5
    // if answer is more than 2 like 55555 and 77777. Then, return both answer.

    // "abc def" -> encrypt +2-> "cbd fgh" -> de -3-> "xyz abc" two answers
    // "cbd fgh" and "abc" -> "abc def" , "xyz abc"
    // "Eqfkpi vguvu ctg hwp!" "tests" -> "Coding tests are fun!"
    // "cdeb nqxg" "love" -> "abcz love"
    // "abc def" "" or " " or "!" -> invalid
    // "" or " " or "!" -> invalid
    // "d" "a" -> "a"
    // "Eqf!kp!i vgu!vu ctg hwp!" "tes!ts" -> "Cod!in!g tes!ts are fun!"

    ArrayList<String> result;
    ArrayList<Integer> shiftNumbers;

    public ArrayList<String> decryptMsg(String chipStr, String knowStr)
    {
        result = new ArrayList<>();


        String[] splitStr = chipStr.split("\\s+");

        if(splitStr.length == 0)
        {
            result.add("Invalid");
            return result;
        }

        // e.g. splitStr ["Eqfkpi", "vguvu", "ctg", "hwp!"]
        shiftNumbers = getShiftNumbers(knowStr, splitStr);

        // check shiftNumber to see if we find the shift number
        if(shiftNumbers.isEmpty())
        {
            result.add("Invalid");
            return result;
        }

        result = getDecodeMsg(chipStr, shiftNumbers);
        // and decode according to the shift number
        return result;

    }

    private ArrayList<Integer> getShiftNumbers(String knowStr, String[] splitStr) {
        shiftNumbers = new ArrayList<>();

        for(String enStr : splitStr)
        {
            if(enStr.length() == knowStr.length())
            {
                int standard = 0;

                for(int i = 0; i < enStr.length(); i++ )
                {
                    char enChar = enStr.charAt(i);
                    char knowChar = knowStr.charAt(i);
                    if(isAlphabetic(enChar))
                    {
                        if(standard == 0)
                        {
                            standard = enChar - knowChar;
                        }
                       if(standard != (enChar - knowChar) )
                       {
                           standard = 0;
                           break;
                       }
                    }
                }

                if(standard != 0) {
                    // Get the Answer!
                    shiftNumbers.add(standard);
                }
            }
        }
        return shiftNumbers;
    }

    private ArrayList<String> getDecodeMsg(String encodeText, ArrayList<Integer> shiftNumbers)
    {
        ArrayList<String> decodeMsgs = new ArrayList<>();

        for(Integer shiftNum: shiftNumbers)
        {
            decodeMsgs.add(getDecodeMsg(encodeText, shiftNum));
        }
        return decodeMsgs;
    }

    private String getDecodeMsg(String encodeText, int shiftNum)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < encodeText.length(); i++)
        {
            char current = encodeText.charAt(i);
            if(isAlphabetic(current))
            {
                int temp = current - shiftNum;
                // Debug: char 'a' is 97 int, 'A' is 65
                if( (isLowerCase(current) & temp < 97) || (isUpperCase(current) & temp < 65) )
                {
                    temp = temp + 26;
                }

                sb.append(toChars(temp));
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        EncryptAndDecryptQuestion thisClass = new EncryptAndDecryptQuestion();


        // Test 1
        // "Eqfkpi vguvu ctg hwp!" "tests" -> "Coding tests are fun!"
        ArrayList<String> answers = thisClass.decryptMsg("Eqfkpi vguvu ctg hwp!", "tests");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");
        answers = thisClass.decryptMsg("cde fgh", "abc");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");

        answers = thisClass.decryptMsg("Eqf!kp!i vgu!vu ctg hwp!", "tes!ts");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");

        answers = thisClass.decryptMsg("CDE FGH", "ABC");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");

        answers = thisClass.decryptMsg("cdeb nqxg", "love");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");

        answers = thisClass.decryptMsg("abc def", "!");
        for(String ans:answers)
        {
            System.out.println(ans);
        }
        System.out.println("==================");
    }

    // "cbd fgh" and "abc" -> "abc def" , "xyz abc"
    // "Eqfkpi vguvu ctg hwp!" "tests" -> "Coding tests are fun!"
    // "cdeb nqxg" "love" -> "abcz love"
    // "abc def" "" or " " or "!" -> invalid
    // "" or " " or "!" -> invalid
    // "d" "a" -> "a"
    // "Eqf!kp!i vgu!vu ctg hwp!" "tes!ts" -> "Cod!in!g tes!ts are fun!"

//    Map<String, Integer> alphabetMap1 = Map.of("a", (Integer) 1,"b", (Integer) 2,"c", (Integer)3,"d", (Integer) 4,
//            "e",(Integer) 5,"f",(Integer) 6,"g",(Integer) 7,"h",(Integer) 8,
//            "i",(Integer) 9,"j",(Integer) 10);
//
//    Map<String, Integer> alphabetMap2 = Map.of("k",(Integer) 11,"l",(Integer) 12,"m",(Integer) 13,"n",(Integer) 14,
//            "o",(Integer) 15,"p",(Integer) 16,"q",(Integer) 17,"r",(Integer) 18,"s",(Integer) 19,"t",(Integer) 20
//    ) ;
//
//    Map<String, Integer> alphabetMap3 = Map.of(
//            "u",(Integer) 21,"v",(Integer) 22,
//            "w",(Integer) 23,"x",(Integer) 24,"y",(Integer) 25,"z",(Integer) 26) ;
//
//    Map<String, Integer> alphabetCapitalMap1 = Map.of("A", (Integer) 1,"B", (Integer) 2,"C", (Integer)3,"D", (Integer) 4,
//            "E",(Integer) 5,"F",(Integer) 6,"g",(Integer) 7,"h",(Integer) 8,
//            "i",(Integer) 9,"j",(Integer) 10);
//
//    Map<String, Integer> alphabetCapitalMap2 = Map.of("k",(Integer) 11,"l",(Integer) 12,"m",(Integer) 13,"n",(Integer) 14,
//            "o",(Integer) 15,"p",(Integer) 16,"q",(Integer) 17,"r",(Integer) 18,"s",(Integer) 19,"t",(Integer) 20
//    ) ;
//
//    Map<String, Integer> alphabetCapitalMap3 = Map.of(
//            "u",(Integer) 21,"v",(Integer) 22,
//            "w",(Integer) 23,"x",(Integer) 24,"y",(Integer) 25,"z",(Integer) 26) ;

    }
