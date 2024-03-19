package com.dsa.techInterview;

import java.util.*;

/*
1. What is K? Is it input?
2. If there are multiple pages with the same frequency, can a subset of them be taken into k result?
3. Are there any limits to the number of pages?

If it might not fit into memory,
Since the entire list of pages visited will not fit into memory, approaches will be:
1. Chunk the list externally before making a call to my solution for each chunk.
    And Then, we will need to merge the result to-gather there by updating the frequency of each page and calculating the top K heap.

Approach:
1. First Assumption, is that this list will fit in the memory.
2. I will create frequency map for all the pages in the list provided.
3. From that map, I will basically create another structure which would collect all the pages which same frequency together.
4. I will traverse that data structure in reverse that is highest freq to lowest freq till I can identify top most frequently visited K pages.


TC: O(m*n) m - No of pages in the list, n - no of pages with same frequency
SC: O(m+n)

 */

public class Q6_TopKFromLog {

    public Map<Integer,Integer> topKpages(int[] nums, int k) {

        List<Integer>[] freqSorted = new List[nums.length +1];
        Map<Integer, Integer> frequencyMap = new HashMap();
        List<Integer> res = new ArrayList();

        for(int n: nums)
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);

        System.out.println(Arrays.asList(frequencyMap));

        for(int key: frequencyMap.keySet()){
            if(freqSorted[frequencyMap.get(key)] == null)
                freqSorted[frequencyMap.get(key)] = new ArrayList();
            freqSorted[frequencyMap.get(key)].add(key);
        }

        System.out.println(Arrays.asList(freqSorted));

        for(int i = freqSorted.length - 1; i >= 0 && res.size() <= k; i--)
            if(freqSorted[i] != null){

                List<Integer> tempList = freqSorted[i];

                int kVal = k - res.size();
                if(tempList.size() > kVal) {
                    res.addAll(tempList.subList(0,kVal));
                }else{
                    res.addAll(tempList);
                }
            }

        frequencyMap.keySet().retainAll(res);

        return frequencyMap;
    }

    public static void main(String[] args) {
        //main Problem
        Q6_TopKFromLog tk = new Q6_TopKFromLog();

        int[] pages = {11,12,140,13,13,1,10,11,10,123,140,140,140,1,1};
        int k = 6;

        Map<Integer,Integer> result = tk.topKpages(pages,k);
        System.out.println(Arrays.asList(result));
    }
}
