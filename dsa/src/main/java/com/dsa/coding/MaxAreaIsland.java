package com.dsa.coding;

public class MaxAreaIsland {

    private static boolean[][] visited;
    private static int[][] grid;

    public MaxAreaIsland(){}

    public MaxAreaIsland(int [][] grid){
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

        MaxAreaIsland areaIsland = new MaxAreaIsland(gridMat);
        System.out.println("Max Area: " + areaIsland.maxArea());
        System.out.println("No. of Islands: " + areaIsland.numberOfIslands());
        areaIsland = new MaxAreaIsland(goldMat);
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
        return 1 + maxAreaDFS(i+1,j) + maxAreaDFS(i-1,j) + maxAreaDFS(i,j+1) + maxAreaDFS(i,j-1);
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
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || grid[i][j] > 100)
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
