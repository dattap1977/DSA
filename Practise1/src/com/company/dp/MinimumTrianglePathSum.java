package com.company.dp;

import java.util.Scanner;

public class MinimumTrianglePathSum {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        System.out.println("Enter the triangle");
        int[][] triangle = new int[n][n];
        for(int i=0; i < n; i++){
            for(int j=0; j < i+1; j++){
                triangle[i][j] = s.nextInt();
            }
        }
        int[][] dp = new int[n][n];
        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                dp[i][j] = -1;
            }
        }
        int minCost = recurse(0,0,triangle,dp);
        System.out.println("minCost = " + minCost);
        dp = new int[n][n];

        int minCost1 = tabulization(triangle,dp);
        System.out.println("minCost1 = " + minCost1);

        int minCost2 = spaceOpt(triangle);
        System.out.println("minCost2 = " + minCost2);
    }

    static int recurse(int i,int j, int[][] triangle,int[][] dp){
         int n = triangle.length;

         if (i == n-1) return triangle[n-1][j];


         if (dp[i][j] != -1) return dp[i][j];

         int down = triangle[i][j] + recurse(i+1,j,triangle,dp);
         int dg = triangle[i][j] + recurse(i+1,j+1,triangle,dp);

         dp[i][j] = Math.min(down,dg);
         //System.out.println("i = " + i + "j = " + j + "dp  = " + dp[i][j] );
         return dp[i][j];
    }
    static int tabulization(int[][] triangle, int[][] dp){
        int n = triangle.length;
        for (int j = 0; j < n; j++ ){
            dp[n-1][j] = triangle[n-1][j];
        }

        for(int i=n-2; i >= 0 ; i--){
            for(int j=i; j >= 0; j--){
                int down = triangle[i][j] + dp[i+1][j];
                int dg = triangle[i][j] + dp[i+1][j+1];
                dp[i][j] = Math.min(down,dg);
            }
        }

        return dp[0][0];

    }

    static int spaceOpt(int[][] triangle){
        int n = triangle.length;
        int[] front = new int[n];
        for (int j = 0; j < n; j++ ){
            front[j] = triangle[n-1][j];
        }

        for(int i=n-2; i >= 0 ; i--){
            int[] curr = new int[n];
            for(int j=i; j >= 0; j--){
                int down = triangle[i][j] + front[j];
                int dg = triangle[i][j] + front[j+1];
                curr[j] = Math.min(down,dg);
            }
            front = curr;
        }

        return front[0];

    }
}
