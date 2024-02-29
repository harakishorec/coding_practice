package com.dsa.karat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
* we are trying to keep similar flowers close together, and want to see what the farthest apart pair fo the same flower are.
*
* */
public class DistantFlower {

    /*
    question to ask for clarification:
    1. what if garden has only one flower, do we need to return -1?

    */




    public static void main(String[] args) {
        String[] garden1 = {"I","R","I","Y","R","D","Ir","R","L"};
        String[] garden2 = {"R"};
        String[] garden3 = {"I","R","Y","A","D","Ir","L","Re","Ro"};
        String[] garden4 = {"I","I"};
        String[] garden5 = {"R","R","R","R","R"};

        System.out.println(farthestDistance(garden1));
        System.out.println(farthestDistance(garden2));
        System.out.println(farthestDistance(garden3));
        System.out.println(farthestDistance(garden4));
        System.out.println(farthestDistance(garden5));

    }

    public static int farthestDistance(String[] garden){
        if(null == garden || garden.length == 0){ // If garden is empty we return -1
            return -1;
        }

        //create map and store all Positions of each flower in the array
        Map<String, List<Integer>> positions = new HashMap<String, List<Integer>>();
        List<Integer> pos = null;
        for(int i =0 ; i<garden.length; i++){
            if(positions.containsKey(garden[i])){
                pos = positions.get(garden[i]);
            }else{
                pos = new ArrayList<Integer>();
            }
            pos.add(i);
            positions.put(garden[i],pos);
        }
        int result = -1;
        //For each Flower entry in the map calculate max distance and return difference only if >0 else return -1
        for (Map.Entry<String, List<Integer>> data:positions.entrySet()) {
            List<Integer> values = data.getValue();
            int distance = -1;

            if(values.size() > 0){
                distance = values.get(values.size()-1) - values.get(0);
            }
             result = Math.max(result,distance); //This is used to ensure we will return the farthest pair
        }



        return result > 0 ? result : -1;

    }

    /**
     * Time Complexity: O(n) --> Since there are two independent for loops
     * Space complexity: O(n) --> Since only one map was created to hold all the positions
     *
     * */
}
