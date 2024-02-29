package com.dsa.karat;

import java.util.*;

public class GetGroceries {

    public static void main(String[] args) {
        String[][] products = {
                {"C","D"},
                {"Ca","P"},
                {"Po","P"},
                {"CT","Pa"},
                {"RL","P"},
                {"CM","D"},
                {"IL","P"},
                {"Co","Pa"},
                {"Pas","Pa"},
                {"M","D"},
                {"BB","P"},
                {"F","Pa"}
        };

        String[] list1 = {"C","Po","Pas","CM","Co"};
        String[] list2 = {"Ca","Po","RL","IL","BB"};

        System.out.println(shopping(products,list1));
        System.out.println(shopping(products,list2));
    }

    public static int shopping(String[][] products, String[] list){

        Map<String,String> seqMap = new HashMap<String, String>();
        Set<String> deptSet = new HashSet<String>();
        for(String[] s: products){
            seqMap.put(s[0],s[1]);
        }

        int seqCount = 0;
        int effCount;
        String currDept = "";
        for(String item: list){
            if(!currDept.equalsIgnoreCase(seqMap.get(item))){
                currDept = seqMap.get(item);
                seqCount++;
            }
            deptSet.add(seqMap.get(item));
        }
        effCount = deptSet.size();

        return seqCount-effCount;
    }
}
