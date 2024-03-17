package com.dsa.coding;

import java.util.*;

public class DAGTopologicalSort {

    Map<Character,List<Character>> graph = new HashMap<>();
    Set<Character> visited = new HashSet<>();

    Deque<Character> stack = new ArrayDeque<>();
    Set<Character> recursionStack = new HashSet<>();

    public List<Character> findOrder(List<String> jobs){
        if(null == jobs) return null;

        for(String job:jobs){
            String[] parts = job.split("->");
            for(int i=0;i<parts.length-1;i++){
                char start = parts[i].charAt(0);
                char end = parts[i+1].charAt(0);
                graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);

            }
        }

        for(char job: graph.keySet()){
            if(!visited.contains(job) && dfs(job)){
                return new ArrayList<>();
            }
        }

        List<Character> order = new ArrayList<>(stack);

        return order;
    }

    private boolean dfs(char job) {
        visited.add(job);
        recursionStack.add(job);
        for(char neighbour: graph.getOrDefault(job,Collections.emptyList())){
            if(!visited.contains(neighbour)){
                if(dfs(neighbour)) return true;
            }
            else if(recursionStack.contains(neighbour)){
                return true;
            }
        }
        recursionStack.remove(job);
        stack.offer(job);
        return false;
    }

    public static void main(String[] args) {
        List<String> jobs = Arrays.asList("A->C->E->G", "A->B->D","C->F->H","D->G->H");
        DAGTopologicalSort dagTopologicalSort = new DAGTopologicalSort();
        for (String job: jobs) {
            System.out.println(job);
        }
        System.out.println();
        System.out.println();
        System.out.println("Result: " + dagTopologicalSort.findOrder(jobs));
    }
}


class DAGTopologicalSortSimple {

    Map<Character, List<Character>> graph = new HashMap<>();
    Set<Character> visited = new HashSet<>();
    List<Character> order = new ArrayList<>();
    boolean hasCycle = false;

    public List<Character> findOrder(List<String> jobs) {
        if (jobs == null) return null;

        // Build the graph
        for (String job : jobs) {
            String[] parts = job.split("->");
            for (int i = 0; i < parts.length - 1; i++) {
                char start = parts[i].charAt(0);
                char end = parts[i + 1].charAt(0);
                graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            }
        }

        // Perform DFS
        for (char job : graph.keySet()) {
            if (!visited.contains(job)) {
                dfs(job);
            }
        }

        // If cycle detected, return an empty list
        if (hasCycle) {
            return new ArrayList<>();
        }

        // Reverse the order to get the correct result
        //Collections.reverse(order);
        return order;
    }

    private void dfs(char job) {
        visited.add(job);
        for (char neighbor : graph.getOrDefault(job, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            } else if (!order.contains(neighbor)) {
                hasCycle = true;
                return;
            }
        }
        order.add(job);
    }

    public static void main(String[] args) {
        List<String> jobs = Arrays.asList("A->C->E->G", "A->B->D", "C->F->H", "D->G->H");
        DAGTopologicalSortSimple dagTopologicalSort = new DAGTopologicalSortSimple();
        System.out.println("Result: " + dagTopologicalSort.findOrder(jobs));
    }
}

