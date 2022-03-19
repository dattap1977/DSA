package com.company.dp;

import java.util.Scanner;

/**
 *  Assign + or - signs to given array elements and count the total number of ways
 *  you can do that so that the sum is K
 */
public class AssignSignsToArrayElements {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];
        int totSum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = s.nextInt();
            totSum = totSum + arr[i];
        }
        int K = s.nextInt();

        int sum = (totSum - K) / 2;

        if (totSum - K >= 0 && (totSum - K) % 2 == 0) {
            int[][] dp = new int[N][sum + 1];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= sum; j++) {
                    dp[i][j] = -1;
                }
            }

            int ans = recurse(N - 1, sum, arr, dp);

            System.out.println("ans = " + ans);

            dp = new int[N][sum + 1];

            int ans1 = tabulization(N, sum, arr, dp);

            System.out.println("ans1 = " + ans1);

            int ans2 = spaceOpt(N, sum, arr);

            System.out.println("ans2 = " + ans2);

        }
    }
        static int recurse(int ind,int K,int[] arr,int[][] dp){
            if (K == 0) return 1;

            // if (arr[0] == K) {return 1;}

            if (ind < 0 || K < 0) return 0;



            if (dp[ind][K] != -1) return dp[ind][K];
            int notPick = recurse(ind - 1,K,arr,dp);
            int pick = recurse(ind - 1, K - arr[ind],arr,dp);
            dp[ind][K] = notPick + pick;
            return notPick + pick;
        }

        static int tabulization(int N,int K,int[] arr,int[][] dp){
            for(int i=0; i < N; i++){
                dp[i][0] = 1;
            }

            if (arr[0] <= K) dp[0][arr[0]] = 1;
            int take = 0;
            int nottake = 0;
            for(int i=1; i <= N-1; i++){
                for(int j=0; j <= K; j = j + 1){
                    nottake = dp[i-1][j];
                    take = 0;
                    if (j >= arr[i])
                        take = dp[i-1][j - arr[i]];
                    dp[i][j] = take + nottake;
                    //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
                }
            }



            return dp[N-1][K];
        }

        static int spaceOpt(int N,int K,int[] arr){
            int[] prev  = new int[K+1];
            prev[0] = 1;


            if (arr[0] <= K) prev[arr[0]] = 1;
            int take = 0;
            int nottake = 0;
            for(int i=1; i <= N-1; i++){
                int[] curr = new int[K+1];
                for(int j=0; j <= K; j = j + 1){
                    nottake = prev[j];
                    take = 0;
                    if (j >= arr[i])
                        take = prev[j - arr[i]];
                    curr[j] = take + nottake;
                    //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
                }
                prev = curr;
            }



            return prev[K];


        }

    }
