package com.dsa.techInterview;


/*

1. What is outside of matrix? Is it water?
2. Will there be anything apart from 1 and 0 in the grid?
3. Diagonal connected would be considered?

Approach:
We can solve using BFS and DFS, I am going to use DFS.
1. Will start from 0,0 and traverse till I find first 1, and then I will identify all connected to this 1 to identify the area of the island.
2. Keep a note of area being calculated so, at the end of the algorithm I can return it.


TC: O(N*M)
SC: O(N*M)

N- no of rows
M- no of columns


 */

public class Q8_MaxAreaIsland {

    private static boolean[][] visited;
    private static int[][] grid;

    public Q8_MaxAreaIsland(){}

    public Q8_MaxAreaIsland(int [][] grid){
        this.grid = grid;
    }

    public static void main(String[] args) {

        int gridMat[][] = {
                {1,1,0,0},
                {0,1,1,1},
                {0,1,0,0},
                {0,0,0,1}
        };

        int goldMat[][] = {
                {5,6,0,0},
                {0,7,3,2},
                {9,0,0,0},
                {9,9,9,1}
        };

        Q8_MaxAreaIsland areaIsland = new Q8_MaxAreaIsland(gridMat);
        System.out.println("Max Area: " + areaIsland.maxArea());
        System.out.println("No. of Islands: " + areaIsland.numberOfIslands());
        areaIsland = new Q8_MaxAreaIsland(goldMat);
        System.out.println("Max Gold: " + areaIsland.getMaximumGold());

    }

    public int maxArea(){
        if(null == grid || grid.length ==0 || grid[0].length ==0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int area,largest = -1;
        visited = new boolean[rows][cols];
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols;j++){
                area = maxAreaDFS(i,j);
                largest = Math.max(largest,area);
            }
        }
        return largest;
    }

    private static int maxAreaDFS(int i, int j) {

        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || visited[i][j] || grid[i][j] == 0){
            return 0;
        }
        visited[i][j] = true;

        int count = 1;

        count = count + maxAreaDFS(i,j+1);
        count = count + maxAreaDFS(i,j-1);
        count = count + maxAreaDFS(i+1,j);
        count = count + maxAreaDFS(i-1,j);

        return count;
    }


    public int numberOfIslands(){
        if(null == grid || grid.length ==0 || grid[0].length ==0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        visited = new boolean[rows][cols];
        int n = 0;
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols;j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    n++;
                    numberOfIslandsDFS(i,j);
                }

            }
        }

        return n;

    }

    private static void numberOfIslandsDFS(int i, int j) {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || visited[i][j] || grid[i][j] == 0){
            return;
        }
        visited[i][j] = true;
        numberOfIslandsDFS(i-1,j);
        numberOfIslandsDFS(i+1,j);
        numberOfIslandsDFS(i,j-1);
        numberOfIslandsDFS(i,j+1);

    }


    private static final int[] d = {0, 1, 0, -1, 0};
    public int getMaximumGold() {
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                ans = Math.max(ans, dfs( i, j, 0));
            }
        }
        return ans;
    }
    private int dfs( int i, int j, int sum) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || grid[i][j] > 1000)
            return sum;
        sum += grid[i][j];
        grid[i][j] += 1000; // mark visited.
        int mx = 0;
        for (int k = 0; k < 4; ++k) { // traverse 4 neighbors to get max value.
            mx = Math.max(mx, dfs( i + d[k], j + d[k + 1], sum));
        }
        grid[i][j] -= 1000; // change back.
        return mx;
    }


}
