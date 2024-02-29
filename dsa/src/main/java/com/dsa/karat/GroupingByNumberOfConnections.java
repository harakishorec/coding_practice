package com.dsa.karat;

import java.util.*;

public class GroupingByNumberOfConnections {


    public static void main(String[] args) {

        String[][] events = {
                {"Connect","A","B"},
                {"Connect","V","B"},
                {"Connect","D","B"},
                {"Connect","A","D"},
                {"Connect","A","C"},
                {"Connect","A","L"},
                {"Connect","K","B"},
                {"Disconnect","A","B"},
                {"Disconnect","D","B"},
                {"Connect","A","B"}


        };

        System.out.println(getConnectionGroups(events, 2));

    }

    public static List<List<String>> getConnectionGroups(String[][] events, int n){
        if(null == events) return null;
        List<String> peopleWithMoreThanNConnectionList = new ArrayList<String>();
        List<String> peopleWithLessThanNConnectionsList = new ArrayList<String>();
        Map<String, List<String>> connectionDetailsMap = new HashMap<String, List<String>>();
        for(String[] s: events){
            if(s[0].equalsIgnoreCase("Connect")){
                addConnection(connectionDetailsMap,s[1],s[2]);
            }else{
                deleteConnection(connectionDetailsMap,s[1],s[2]);
            }
        }

        for(String per: connectionDetailsMap.keySet() ){
            List<String> connections = connectionDetailsMap.get(per);
            if(connections.size() < n){
                peopleWithLessThanNConnectionsList.add(per);
            } else{
                peopleWithMoreThanNConnectionList.add(per);
            }
        }

        List<List<String>> result = new ArrayList<List<String>>();
        result.add(peopleWithLessThanNConnectionsList);
        result.add(peopleWithMoreThanNConnectionList);
        return result;
    }

    private static void deleteConnection(Map<String, List<String>> connectionDetailsMap, String s, String s1) {
        if(!connectionDetailsMap.containsKey(s) || !connectionDetailsMap.containsKey(s1)){
            return;
        }
        List<String> con = connectionDetailsMap.get(s);
        con.remove(s1);
        connectionDetailsMap.put(s,con);
        con = connectionDetailsMap.get(s1);
        con.remove(s);
        connectionDetailsMap.put(s1,con);
    }

    private static void addConnection(Map<String, List<String>> connectionDetailsMap, String s, String s1) {
        if(!connectionDetailsMap.containsKey(s)){
            connectionDetailsMap.put(s,new ArrayList<String>());
        }

        if(!connectionDetailsMap.containsKey(s1)){
            connectionDetailsMap.put(s1,new ArrayList<String>());
        }
        List<String> con1 = connectionDetailsMap.get(s);
        con1.add(s1);
        connectionDetailsMap.put(s,con1);

        List<String> con2 = connectionDetailsMap.get(s1);
        con2.add(s);
        connectionDetailsMap.put(s1,con2);
    }
}
