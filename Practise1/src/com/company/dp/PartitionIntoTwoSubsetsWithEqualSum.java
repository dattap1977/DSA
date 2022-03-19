package com.company.dp;

import java.util.Scanner;

public class PartitionIntoTwoSubsetsWithEqualSum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];
        int totSum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = s.nextInt();
            totSum = totSum + arr[i];
        }

        if (totSum%2 == 0) {
            int K = totSum / 2;

            int[][] dp = new int[N][K + 1];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= K; j++) {
                    dp[i][j] = -1;
                }
            }

            int ans = recurse(N - 1, K, arr, dp);
            if (ans == 1) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

            dp = new int[N][K + 1];

            int ans1 = tabulization(N, K, arr, dp);

            if (ans1 == 1) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

            int ans2 = spaceOpt(N, K, arr);
            if (ans2 == 1) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
        else{
            System.out.println("false");
        }
    }
    static int recurse(int i,int j, int[] arr,int[][] dp){



        if (j == 0) return 1;

        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];


        int nottake = recurse(i-1,j,arr,dp);
        int take = recurse(i-1,j - arr[i],arr,dp);


        if (nottake == 1 || take == 1) {
            dp[i][j] = 1;

        }
        else {
            dp[i][j] = 0;

        }

        // System.out.println("i=" + i + " j =" + j + "dp = " + dp[i][j]);
        return dp[i][j];
    }

    static int tabulization(int N,int K,int[] arr,int[][] dp){
        for(int j=0; j <= N-1; j++) {

            dp[j][0] = 1;
        }

        int nottake = 0;
        int take = 0;

        dp[0][arr[0]] = 1;
        for(int i=1; i <= N-1; i++){
            for(int j=1; j <= K; j = j + 1){
                nottake = dp[i-1][j];
                if (j >= arr[i])
                    take = dp[i-1][j - arr[i]];
                if (nottake == 1 || take == 1) {
                    dp[i][j] = 1;
                }
                else
                    dp[i][j] = 0;

                //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
            }
        }

        return dp[N-1][K];
    }

    static int spaceOpt(int N,int K, int[] arr){
        int[] prev = new int[K+1];


        prev[0] = 1;


        int nottake = 0;
        int take = 0;

        prev[arr[0]] = 1;
        for(int i=1; i <= N-1; i++){
            int[] curr = new int[K+1];
            for(int j=1; j <= K; j = j + 1){
                nottake = prev[j];
                if (j >= arr[i])
                    take = prev[j - arr[i]];
                if (nottake == 1 || take == 1) {
                    curr[j] = 1;
                }
                else
                    curr[j] = 0;

                //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
            }
            prev = curr;
        }

        return prev[K];
    }
}
