package com.dsa.karat;

import java.util.*;

public class EntryWithoutExits {
/*
* Code which has an ordered list of entry and exit events fro employees and should return two lists:
* 1. List of all employees with only Entry ( and no exit)
* 2. List of all employees with only exits ( and no entries)
*
* */
    public static void main(String[] args) {
        String[][] r1 = {
                {"P", "entry"},
                {"P", "exit"}
        };


        String[][] r2 = {
                {"P", "entry"},
                {"P", "entry"}
        };


        String[][] r3 = {
                {"P", "exit"},
                {"P", "entry"}
        };

        String[][] r4 = {
                {"R", "entry"},
                {"P", "entry"},
                {"P", "exit"},
                {"P", "exit"},
                {"P", "entry"},
                {"R", "entry"}
        };

        System.out.println(getEntryExitLists(r1));
        System.out.println(getEntryExitLists(r2));
        System.out.println(getEntryExitLists(r3));
        System.out.println(getEntryExitLists(r4));

    }

/*
* Input is a 2-D array of records and we will return List <List<String>>
*
* */
    public static List<List<String>> getEntryExitLists(String[][] records){
        List<String> entryWithoutExitsList = new ArrayList<String>(); // Will have List of all employees with entry records without exits captured
        List<String> exitsWithoutEntryList = new ArrayList<String>();// Will have List of all employees with exit records without entry captured

        List<List<String>> result = new ArrayList<List<String>>();

        result.add(entryWithoutExitsList);
        result.add(exitsWithoutEntryList);
        Set<String> entrySet = new HashSet<String>(); // Set to maintain all unique employees who wil be part of entryWithoutExitsList
        Set<String> exitSet = new HashSet<String>(); // Set to maintain all unique employees who wil be part of exitsWithoutEntryList
        Map<String,Integer> entryMap = new HashMap<String,Integer>(); // Map to track entry for each employee


        for(String[] s : records) {
            // We will have two possible values for events "Entry/Exit"
            if(s[1].equalsIgnoreCase("entry")){
                if(entryMap.containsKey(s[0])){
                    entrySet.add(s[0]); // If entry was already captured previously then it will be present in entryMap for the employee --> i.e. employee exited without swiping his badge and reentered the room so needs to be part of entryWithoutExitList
                }else{
                    entryMap.put(s[0],0); // Capturing new entry for employee ( Can be either brand new or reentry after exit was captured)
                }
            }else{
                if(!entryMap.containsKey(s[0])){ // If event is Exit and no entry for employee is found in entry --> need to add to exit without entry List
                    exitSet.add(s[0]);
                }else{
                    entryMap.remove(s[0]); //If entry record for employee is found, then we simply delete the entry record from the map
                }
            }
        }

        //Need to capture all pending employees who only had entry captured and no exit captured
        //Reason we have this is because in question it is stated that at end of List all employees have exited the room
        entrySet.addAll(entryMap.keySet());

        entryWithoutExitsList.addAll(entrySet);
        exitsWithoutEntryList.addAll(exitSet);



        return result;
    }



}
