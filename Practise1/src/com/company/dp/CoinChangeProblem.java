package com.company.dp;

import java.util.Scanner;

public class CoinChangeProblem {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] coin = new int[N];
        for(int i=0; i < N; i++){
            coin[i] = s.nextInt();
        }
        int T = s.nextInt();
        int[][] dp = new int[N][T+1];
        for(int i=0; i < N; i++){
            for(int j=0; j <= T; j++){
                dp[i][j] = -1;
            }
        }

        int minNoOfCoins = recurse(N-1,T,coin,dp);
        if (minNoOfCoins == 1000000000)
        {
            minNoOfCoins = -1;
        }
        System.out.println("Minimum no of coins = " + minNoOfCoins);
        dp = new int[N][T+1];
        int minNoOfCoins1 = tabulization(N,T,coin,dp);
        System.out.println("Minimum no of coins = " + minNoOfCoins1);
        int minNoOfCoins2 = spaceOPt(N,T,coin);
        System.out.println("Minimum no of coins = " + minNoOfCoins2);

    }

    static int recurse(int i,int j,int[] coin,int[][] dp){


           if (i == 0){
               if (j % coin[0] == 0) return j/coin[0];
           }

           if (i < 0 || j < 0) return 1000000000;

           if (dp[i][j] != -1) return dp[i][j];

           int notPick = 0 + recurse(i-1,j,coin,dp);

           int pick = 1000000000;

           if (j >= coin[i]){
               pick = 1 + recurse(i,j-coin[i],coin,dp);
           }

           dp[i][j] = Math.min(notPick,pick);


           return dp[i][j];
    }

    static int tabulization(int N,int T,int[] coin,int[][] dp){
        for(int i=0; i <= T; i++){
            if (i % coin[0] == 0) dp[0][i] = i/coin[0];
            else
                dp[0][i] = 1000000000;
        }

        for(int i=1; i < N; i++) {

           for(int j=0; j <= T; j++) {
               int notPick = 0 + dp[i - 1][j];

               int pick = 1000000000;

               if (j >= coin[i]) {
                   pick = 1 + dp[i][j - coin[i]];
               }

               dp[i][j] = Math.min(notPick, pick);
           }
        }
        if (dp[N-1][T] == 1000000000){
            return -1;
        }
        else
        return dp[N-1][T];

    }

    static int spaceOPt(int N,int T,int[] coin){
        int[] prev = new int[T+1];
        for(int i=0; i <= T; i++){
            if (i % coin[0] == 0) prev[i] = i/coin[0];
            else
                prev[i] = 1000000000;
        }

        for(int i=1; i < N; i++) {
            int[] curr = new int[T+1];
            for(int j=0; j <= T; j++) {
                int notPick = 0 + prev[j];

                int pick = 1000000000;

                if (j >= coin[i]) {
                    pick = 1 + curr[j - coin[i]];
                }

                curr[j] = Math.min(notPick, pick);
            }
            prev = curr;
        }
        if (prev[T] == 1000000000)
            return -1;
        else
            return prev[T];
    }
}
