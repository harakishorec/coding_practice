package com.dsa.coding;

import java.util.*;

public class JobScheduler {

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
        JobScheduler dagTopologicalSort = new JobScheduler();
        System.out.println("Result: " + dagTopologicalSort.findOrder(jobs));
    }
}
