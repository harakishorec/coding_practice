package com.dsa.techInterview;

import java.util.*;

/*
1. would you be able to provide me an example? I just want to be sure my understanding is correct before I start.
2. what is that entire word is there in the dictionary ? like "leetcode" then will it come True?
3. Are list values unique or repetitive?
4. Will all elements be single string and case sensitive?
5. Should we traverse in same order or in any order?

Approach:
I will use DFS approach here,
1. I will traverse though the word, character by character.

2. When first prefix will be found in the list, it will check second part of the word.
like if "leet" is present in the List then it will check "code" in the list first to match.

3. If it doesn't find second half ("code"), then it will start from next substring and continue to search.

TC: O(2^N * N):  2^N - recursive, N - substring. - Without Memoization

TC: O(N^3) - Using Memoization and hence eliminating few unnecessary runs.
SC: O(D) - Words in a hashset

N SUBSTRING operations
 */

public class Q7_Word_Segmentation {

    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> memo = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordDict);

        //Variation 1
        Map<String, String> result = new HashMap<>();

//        for(String word: wordDict){
//            wordSet.add(word.toLowerCase());
//        }
        return dfs(s, wordSet, memo,result);
    }

    private boolean dfs(String s, Set<String> wordSet, Map<String, Boolean> memo, Map<String, String> result) {

        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (wordSet.contains(s)) {
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.toLowerCase().substring(0, i);
            if (wordSet.contains(prefix) && dfs(s.substring(i), wordSet, memo, result)) {
                memo.put(s.toLowerCase(), true);

                //main Problem
                //return true;

                //Variation 1
                result.put(prefix,s.substring(i));
            }
        }
        memo.put(s.toLowerCase(), false);

        System.out.println("Result: "+ Arrays.asList(result));
        System.out.println("Memo: "+ Arrays.asList(memo));

        //Variation 1
        if(!result.isEmpty()){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Q7_Word_Segmentation ws = new Q7_Word_Segmentation();
//        String s = "leetcode";
//        List<String> dict = new ArrayList<String>();
//        dict.add("leet");
//        dict.add("code");
//        boolean wordSegmentation = ws.wordBreak(s,dict);
//        System.out.println("Word Segmentation for leetcode:" + wordSegmentation);



//        String s1 = "Walmart";
//        List<String> dict1 = new ArrayList<String>();
//        dict1.add("wall");
//        dict1.add("smart");
//        dict1.add("wal");
//        dict1.add("lmart");
//        dict1.add("martt");
//        boolean wordSegmentation1 = ws.wordBreak(s1,dict1);
//        System.out.println("Word Segmentation for Walmart:" + wordSegmentation1);

        String s2 = "Walmart";
        List<String> dict2 = new ArrayList<String>();
        dict2.add("walmar");
        dict2.add("walma");
        dict2.add("walm");
        dict2.add("wal");
        dict2.add("t");
        dict2.add("rt");
        dict2.add("art");
        boolean wordSegmentation2 = ws.wordBreak(s2,dict2);
        System.out.println("Word Segmentation for Walmart2:" + wordSegmentation2);
//
//        String s3 = "catsandog";
//        List<String> dict3 = new ArrayList<String>();
//        dict3.add("cats");
//        dict3.add("dog");
//        dict3.add("sand");
//        dict3.add("and");
//        dict3.add("cat");
//        boolean wordSegmentation3 = ws.wordBreak(s3,dict3);
//        System.out.println("Word Segmentation for leetcode:" + wordSegmentation3);
    }
}
