package com.dsa.technicalInterviewPractice;

public class NoOfIslandIntWithoutVisited {

    public int dfsNoOfIsland(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;
        int noOfIsland = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    dfsNoOfIsland(grid,i,j);
                    noOfIsland++;
                }
            }
        }
        return noOfIsland;
    }

    public void dfsNoOfIsland(int[][] grid, int row, int col){

        if(row< 0 || col < 0 ||
                row >= grid.length ||
                col >= grid[0].length ||
                grid[row][col] == 0){
            return;
        }
        grid[row][col] = 0;
        dfsNoOfIsland(grid, row, col-1);
        dfsNoOfIsland(grid, row-1, col);
        dfsNoOfIsland(grid, row, col+1);
        dfsNoOfIsland(grid, row+1, col);
    }

    public int dfsMaxAreaOfIsland(int[][] grid){

        int m = grid.length;
        int n = grid[0].length;
        int maxAreaOfIsland =0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 1){
                    maxAreaOfIsland = Math.max(maxAreaOfIsland,dfsMaxAreaOfIsland(grid,i,j));
                }
            }
        }

        return maxAreaOfIsland;
    }

    public int dfsMaxAreaOfIsland(int[][] grid, int row, int col){

        if(row < 0 || col < 0 ||
            row >= grid.length ||
            col >= grid[0].length ||
            grid[row][col] == 0){
                return 0;
        }

        grid[row][col]=0;
        int count = 1;

        count = count + dfsMaxAreaOfIsland(grid,row,col-1);
        count = count + dfsMaxAreaOfIsland(grid,row-1,col);
        count = count + dfsMaxAreaOfIsland(grid,row,col+1);
        count = count + dfsMaxAreaOfIsland(grid,row+1,col);

        return count;
    }




    public void printMatrix(int[][] grid){

        for(int i =0; i< grid.length;i++){
            for(int j=0; j<grid[0].length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int gridMat[][] = {
                {1,1,0,0},
                {0,1,1,1},
                {0,1,0,0},
                {0,0,0,0}
        };

        NoOfIslandIntWithoutVisited islandInt = new NoOfIslandIntWithoutVisited();

        islandInt.printMatrix(gridMat);

//        int noOfIsland = islandInt.dfsNoOfIsland(gridMat);
//        System.out.println("No of Island: "+ noOfIsland);

        int maxArea = islandInt.dfsMaxAreaOfIsland(gridMat);
        System.out.println("Max Area: "+maxArea);

    }
}

