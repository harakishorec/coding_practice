package com.dsa.techInterview;
import java.util.*;

/*

Sliding window Problem
 */


public class Q9_permutation_String_SW {

    //Main Problem
    public boolean checkInclusion(String mainString, String searchString) {
        if(mainString.isEmpty() || searchString.isEmpty()){
            return false;
        }
        Hashtable<Character , Integer> hashtable1 = new Hashtable<>();
        Hashtable<Character , Integer> hashtable2 = new Hashtable<>();
        List<Character> res = new ArrayList<>();

        //create frequency of each character in the search string, so that we can check if we find a similar one in main string.
        for (char c : searchString.toCharArray()) {
            hashtable1.put(c , hashtable1.getOrDefault(c , 0) +1);
        }

        for (int i = 0; i < mainString.length(); i++) {
            char c = mainString.charAt(i);
            hashtable2.put( c, hashtable2.getOrDefault(c , 0) +1);
            res.add(c);
            if (i >= searchString.length()) {
                char c2 = mainString.charAt(i - searchString.length());
                if (hashtable2.get(c2) == 1){
                    hashtable2.remove(c2);
                }else {
                    hashtable2.put( c2, hashtable2.getOrDefault(c2 , 1) -1);
                }
                res.remove(0);
            }

            if (hashtable1.equals(hashtable2)) {
                System.out.println(Arrays.toString(res.toArray()));
                return true;
            }
        }
        return false;
    }

    //Variation 1
    public String checkSubString(String mainString, String searchString) {

        if(mainString.isEmpty() || searchString.isEmpty()){
            return "";
        }
        Map<Character, Integer> searchStrMap = new HashMap<Character,Integer>();

        for(char c: searchString.toCharArray()){
            searchStrMap.put(c,searchStrMap.getOrDefault(c,0)+1);
        }
        System.out.println();

        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int count = searchString.length();

        for(int right =0; right<mainString.length(); right++){

            char currentChar = mainString.charAt(right);

            if(searchStrMap.containsKey(currentChar)){
                searchStrMap.put(currentChar,searchStrMap.get(currentChar)-1);

                if(searchStrMap.get(currentChar) >=0){
                    count--;
                }
            }

            while(count == 0){
                //update min window substring
                if(right-left+1 < minLength){
                    minLength = right-left+1;
                    minLeft = left;
                }

                //move left pointer to find smaller window
                char leftChar = mainString.charAt(left);

                if(searchStrMap.containsKey(leftChar)){
                    searchStrMap.put(leftChar,searchStrMap.get(leftChar)+1);

                    if(searchStrMap.get(leftChar) > 0){
                        count++;
                    }
                }
                left++;
            }
        }
        String res ;
        if(minLength == Integer.MAX_VALUE){
            res = "";
        }else{
            res = mainString.substring(minLeft, minLeft+minLength);
        }
        return res;
    }

    public static void main(String[] args) {

        Q9_permutation_String_SW sw = new Q9_permutation_String_SW();
        System.out.println(sw.checkInclusion("walmdart","tar"));

        //Variation 1
        System.out.println(sw.checkSubString("walmartaataem","mat"));
    }
}
