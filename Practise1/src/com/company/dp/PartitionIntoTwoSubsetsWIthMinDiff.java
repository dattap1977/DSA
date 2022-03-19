package com.company.dp;

import java.util.Scanner;

public class PartitionIntoTwoSubsetsWIthMinDiff {
    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];
        int totSum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = s.nextInt();
            totSum = totSum + arr[i];
        }


        int[][] dp = new int[N][totSum + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= totSum; j++) {
                dp[i][j] = 0;
            }
        }

        int ans = tabulization(N,totSum,arr,dp);
        int mini = Integer.MAX_VALUE;
        for(int j=0; j <= totSum; j++){
            if (dp[N-1][j] == 1){
                mini = Math.min(mini,Math.abs(j - (totSum - j)));
            }
        }
        System.out.println("Minimum absolute diffrence of sum of any two partitions = " + mini);
        int[] prev = new int[totSum + 1];
        int ans1 = spaceOpt(N,totSum,arr,prev);

        System.out.println("Minimum absolute diffrence of sum of any two partitions using limited space = " + ans1);
    }


    static int tabulization(int N, int K, int[] arr, int[][] dp) {
        for (int j = 0; j <= N - 1; j++) {

            dp[j][0] = 1;
        }

        int nottake = 0;
        int take = 0;

        dp[0][arr[0]] = 1;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= K; j = j + 1) {
                nottake = dp[i - 1][j];
                if (j >= arr[i])
                    take = dp[i - 1][j - arr[i]];
                if (nottake == 1 || take == 1) {
                    dp[i][j] = 1;
                } else
                    dp[i][j] = 0;

                //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
            }
        }

        return dp[N - 1][K];
    }

    static int spaceOpt(int N,int K, int[] arr,int[] prev){



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

        int mini = Integer.MAX_VALUE;
        for(int j=0; j <= K; j++){
            if (prev[j] == 1){
                mini = Math.min(mini,Math.abs(j - (K - j)));
            }
        }

        return mini;
    }
}

