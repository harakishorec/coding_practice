package com.dsa.techInterview;

import java.util.*;

/*
1. Can I start with the assumption that all jobs are unique name? case insensitive.
2. This looks like I need to create Graph like Structure and may be traverse level by level. It looks like Topological Sort.
3. I will start my implementation assuming there are no loops.
4. a job can only be executed exactly once or multiple times by job schedule processor?

Approach:
1. First I will create the dependency map that will act like an adjacency matrix to track immediate dependent neighbours for each jobs.
2. Using dependency map, I will traverse it via DFS recursively to ensure that I can successfully complete all the jobs.

TC: O(J*N) SC: O(J+N)  --> J ==Number of Jobs, N == Number of Neighbors
If every job has multiple dependencies, few are same without cycles, then no of neighbour space is more. so O(N+J) and loops will traverse multiple time so O(J*N)
 */

public class Q5_Job_DAG {
    Map<String, List<String>> graph = new HashMap<>();
    Set<String> visited = new HashSet<>();
    List<String> order = new ArrayList<>();
    Set<String> recursionStack = new HashSet<>();
    boolean hasCycle = false;

    public static void main(String[] args) {
        List<String> jobs = Arrays.asList("A->C->E->G", "A->B->D", "C->F->H", "D->G->H");
        Q5_Job_DAG dagTopologicalSort = new Q5_Job_DAG();

        System.out.println("Simple : X3 meets: result:" + dagTopologicalSort.findOrderX3Meets(jobs));
        System.out.println("X4 meets: result: " + dagTopologicalSort.findOrderX4Meets(jobs));
    }

    private List<String> findOrderX4Meets(List<String> jobs) {
        if (jobs == null) return null;
        graph = new HashMap<>();
        // Build the graph
        createDependencyMap(jobs);

        visited = new HashSet<>();
        order = new ArrayList<>();
        hasCycle = false;
        //DoDFS
        for (String job : graph.keySet()) {
            if (!visited.contains(job)) {
                dfsX4Meets(job);
            }
        }

        if(hasCycle) return  new ArrayList<>();

        Collections.reverse(order);
        return order;
    }

    private void dfsX4Meets(String job) {
        visited.add(job);
        for (String neighbor : graph.getOrDefault(job, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsX4Meets(neighbor);
            }else if(!order.contains(neighbor)){
                hasCycle = true;
                return;
            }
        }
        order.add(job);
    }

    //TC: O(J*N) SC: O(J +N)  --> J ==Number of Jobs, N == Number of Neighbors
    //Assumption: No cycles are present
    private List<String> findOrderX3Meets(List<String> jobs) {
        if (jobs == null) return null;

        graph = new HashMap<>();
        visited = new HashSet<>();
        order = new ArrayList<>();

        // Build the graph
        createDependencyMap(jobs);

        //Do DFS
        for (String job : graph.keySet()) {
            if (!visited.contains(job)) {
                dfsX3Meets(job);
            }
        }
        Collections.reverse(order);
        return order;
    }

    private void dfsX3Meets(String job) {
        visited.add(job);
        //System.out.println(Arrays.asList(graph.getOrDefault(job, Collections.emptyList())));
        for (String neighbor : graph.getOrDefault(job, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsX3Meets(neighbor);
            }
        }
        order.add(job);
    }

    private void createDependencyMap(List<String> jobs) {
        for (String job : jobs) {
            String[] parts = job.split("->");
            for (int i = 0; i < parts.length - 1; i++) {
                String start = parts[i];
                String end = parts[i + 1];
                graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            }

        }
    }
}
