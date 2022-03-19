package com.company.dp;

import java.util.Scanner;

/**
 * Start from any point in the first row of the grid and find the maximum path sum
 * upto the last row in the grid.
 */
public class VariableSourceAndDestinationInGrid {
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

        int[][] dp = new int[N][M];

        for(int i=0; i < N; i++){
            for(int j=0; j < M; j++){
                dp[i][j] = -1;
            }
        }
        int maxi = -1;
        for(int i1 = 0; i1 < grid[0].length; i1++){
            maxi = Math.max(recurse(N-1,i1,grid,dp),maxi);
        }

        int maxCost = maxi;

        System.out.println("maxCost = " + maxCost);
        dp = new int[N][M];
        int maxCost1 = tabulization(N,M,grid,dp);
        System.out.println("maxCost1 = " + maxCost1);
        int maxCost2 = spaceOpt(N,M,grid);
        System.out.println("maxCost1 = " + maxCost2);
    }

    static int recurse(int i,int j,int[][] grid,int[][] dp){
        if(j < 0 || j >= grid[0].length) return -1000000000;

        if (i == 0) return grid[0][j];

        if (dp[i][j] != -1) return dp[i][j];

            int up = grid[i][j] + recurse(i - 1, j, grid, dp);
            int ld = grid[i][j] + recurse(i - 1, j - 1, grid, dp);
            int rd = grid[i][j] + recurse(i - 1, j + 1, grid, dp);
            dp[i][j] = Math.max(up,Math.max(ld,rd));


        return dp[i][j];
    }

    static int tabulization(int N,int M,int[][] grid,int[][] dp){
        for(int j=0; j < M; j++){
            dp[0][j] = grid[0][j];
        }
        int up = 0;
        int ld = 0;
        int rd = 0;
        for(int i=1; i < N; i++){
            for(int j=0; j < M; j++){
                 up = grid[i][j] + dp[i-1][j];
                 if (j > 0)
                 ld = grid[i][j] + dp[i-1][j-1];
                 else
                     ld = -100000000;
                 if (j < M-1)
                 rd = grid[i][j] + dp[i-1][j+1];
                 else
                     rd = -100000000;

                 dp[i][j] = Math.max(up,Math.max(ld,rd));
            }
        }
        int maxi = -1;
        for(int i=0; i < M; i++){
            maxi = Math.max(maxi,dp[N-1][i]);
        }

        return maxi;
    }

    static int spaceOpt(int N, int M, int[][] grid){
        int[] prev = new int[M];
        int[] curr = new int[M];
        for(int j=0; j < M; j++){
            prev[j] = grid[0][j];
        }
        int up = 0;
        int ld = 0;
        int rd = 0;
        for(int i=1; i < N; i++){
            curr = new int[M];
            for(int j=0; j < M; j++){
                up = grid[i][j] + prev[j];
                if (j > 0)
                    ld = grid[i][j] + prev[j-1];
                else
                    ld = -100000000;
                if (j < M-1)
                    rd = grid[i][j] + prev[j+1];
                else
                    rd = -100000000;

                curr[j] = Math.max(up,Math.max(ld,rd));
            }
            prev = curr;
        }
        int maxi = -1;
        for(int i=0; i < M; i++){
            maxi = Math.max(maxi,prev[i]);
        }

        return maxi;
    }
}
