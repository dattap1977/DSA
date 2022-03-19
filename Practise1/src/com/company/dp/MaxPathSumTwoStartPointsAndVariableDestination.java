package com.company.dp;

import java.util.Scanner;

/**
 * There are two fixed starting points one the top left corner of a grid and another is the
 * top right corner of the grid. Now from the top left corner Alice starts to move downwards
 * either diagonally or straight below the current row. The same is for Bob from the top
 * right corner. Now we have to find the maximum sum path possible after traversing till the
 * bottom row.
 */


public class MaxPathSumTwoStartPointsAndVariableDestination {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();

        int[][] grid = new int[N][M];

        for(int i=0; i < N; i++){
            for(int j=0; j < M; j++){
                grid[i][j] = s.nextInt();
            }
        }

        int[][][] dp = new int[N][M][M];
        for(int i=0; i < N; i++){
            for(int j=0; j < M; j++){
                for(int k=0; k < M; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        int maxCost = recurse(0,0,M-1,grid,dp);
        System.out.println("maxCost = " + maxCost);
        dp = new int[N][M][M];
        for(int i=0; i < N; i++){
            for(int j=0; j < M; j++){
                for(int k=0; k < M; k++){
                    dp[i][j][k] = 0;
                }
            }
        }
        int maxCost1 = tabulization(N,M,grid,dp);

        System.out.println("maxCost1 = " + maxCost1);

        int maxCost2 = spaceOpt(N,M,grid);

        System.out.println("maxCost2 = " + maxCost2);
    }

    static int recurse(int i,int j1,int j2, int[][] grid,int[][][] dp){
        int N = grid.length;
        int M = grid[0].length;
        if (j1 < 0 || j1 >= M || j2 < 0 || j2 >= M ) return -100000000;

        if (i == N-1){
            if (j1 == j2) return grid[i][j1];

            if (j1 != j2) return grid[i][j1] + grid[i][j2];
        }

        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

        int[] dx = {-1,0,1};

        int[] dy = {-1,0,1};

        int maxi = -1;
        for(int i1 = 0; i1 <= 2; i1++){
            for(int i2 = 0; i2 <= 2; i2++){
                if (j1 == j2){
                      maxi = Math.max(maxi, grid[i][j1] + recurse(i+1,j1 + dx[i1],j2 + dy[i2],grid,dp));
                }
                else{
                    maxi = Math.max(maxi, grid[i][j1] + grid[i][j2] +  recurse(i+1,j1 + dx[i1],j2 + dy[i2],grid,dp));                }
            }
        }

        dp[i][j1][j2] = maxi;
        return maxi;
    }

    static int tabulization(int N,int M,int[][] grid,int[][][] dp) {
        for (int j1 = 0; j1 < M; j1++) {
            for (int j2 = 0; j2 < M; j2++) {
                if (j1 == j2) dp[N-1][j1][j2] =  grid[N-1][j1];

                if (j1 != j2) {
                    dp[N - 1][j1][j2] = grid[N - 1][j1] + grid[N - 1][j2];
                }
                }
            }

        int maxi = -1;

        int[] dx = {-1,0,1};


        for(int i=N-2; i >= 0; i--){
            for(int j1=0; j1 < M;j1++){
                for(int j2=0; j2 < M; j2++){
                    maxi = -1;
                    for(int i1 = 0; i1 <= 2; i1++){
                        for(int i2 = 0; i2 <= 2; i2++){
                            if (j1 + dx[i1] >= 0 && j1 + dx[i1] <= M-1 && j2 + dx[i2] >= 0 && j2 + dx[i2] <= M-1){
                            if (j1 == j2){

                                maxi = Math.max(maxi,grid[i][j1] + dp[i+1][j1 + dx[i1]][j2 + dx[i2]]);
                            }
                            else {
                                maxi = Math.max( maxi,grid[i][j1] + grid[i][j2] + dp[i+1][j1 + dx[i1]][j2 + dx[i2]]);
                               }
                            }
                            else
                                dp[i][j1][j2] = -100000000;
                            }


                    }

                    dp[i][j1][j2] = maxi;

                    //System.out.println(" i = " + i + "j1 = " + j1 + "j2 = " + j2 + "dp = " + dp[i][j1][j2]);
                }
            }
        }
        return dp[0][0][M-1];
    }

    static int spaceOpt(int N,int M,int[][] grid){
        int[][] prev = new int[M][M];
        for (int j1 = 0; j1 < M; j1++) {
            for (int j2 = 0; j2 < M; j2++) {
                if (j1 == j2) prev[j1][j2] =  grid[N-1][j1];

                if (j1 != j2) {
                    prev[j1][j2] = grid[N - 1][j1] + grid[N - 1][j2];
                }
            }
        }

        int maxi = -1;

        int[] dx = {-1,0,1};


        for(int i=N-2; i >= 0; i--){
            int[][] curr = new int[M][M];
            for(int j1=0; j1 < M;j1++){
                for(int j2=0; j2 < M; j2++){
                    maxi = -1;

                    for(int i1 = 0; i1 <= 2; i1++){
                        for(int i2 = 0; i2 <= 2; i2++){
                            if (j1 + dx[i1] >= 0 && j1 + dx[i1] <= M-1 && j2 + dx[i2] >= 0 && j2 + dx[i2] <= M-1){
                                if (j1 == j2){

                                    maxi = Math.max(maxi,grid[i][j1] + prev[j1 + dx[i1]][j2 + dx[i2]]);
                                }
                                else {
                                    maxi = Math.max( maxi,grid[i][j1] + grid[i][j2] + prev[j1 + dx[i1]][j2 + dx[i2]]);
                                }
                            }
                            else
                                curr[j1][j2] = -100000000;
                        }


                    }

                    curr[j1][j2] = maxi;

                    //System.out.println(" i = " + i + "j1 = " + j1 + "j2 = " + j2 + "dp = " + dp[i][j1][j2]);
                }
            }
            prev = curr;
        }
        return prev[0][M-1];
    }
}
