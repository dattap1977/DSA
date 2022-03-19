package com.company.dp;

import java.util.Scanner;

public class NoOfWaysCoinsChangeProblem {
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

        int totalWays = recurse(N-1,T,coin,dp);
         System.out.println("Total ways = " + totalWays);
        dp = new int[N][T+1];
        int totalWays1 = tabulization(N,T,coin,dp);
        System.out.println("Total ways = " + totalWays1);
        int totalWays2 = spaceOPt(N,T,coin);
        System.out.println("Total ways =  " + totalWays1);

    }
    static int recurse(int i,int j,int[] coin,int[][] dp){


        if (i == 0){
            if (j % coin[0] == 0) return 1;

        }

        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int notPick = recurse(i-1,j,coin,dp);

        int pick = 0;

        if (j >= coin[i]){
            pick = recurse(i,j-coin[i],coin,dp);
        }

        dp[i][j] = pick + notPick;


        return dp[i][j];
    }

    static int tabulization(int N,int T,int[] coin,int[][] dp){
        for(int i=0; i <= T; i++){
            if (i % coin[0] == 0) dp[0][i] = 1;
            else
                dp[0][i] = 0;
        }

        for(int i=1; i < N; i++) {

            for(int j=0; j <= T; j++) {
                int notPick =  dp[i - 1][j];

                int pick = 0;

                if (j >= coin[i]) {
                    pick =  dp[i][j - coin[i]];
                }

                dp[i][j] = pick + notPick;
            }
        }
            return dp[N-1][T];

    }

    static int spaceOPt(int N,int T,int[] coin){
        int[] prev = new int[T+1];
        for(int i=0; i <= T; i++){
            if (i % coin[0] == 0) prev[i] = 1;
            else
                prev[i] = 0;
        }

        for(int i=1; i < N; i++) {
            int[] curr = new int[T+1];
            for(int j=0; j <= T; j++) {
                int notPick = prev[j];

                int pick = 0;

                if (j >= coin[i]) {
                    pick = curr[j - coin[i]];
                }

                curr[j] = pick + notPick;
            }
            prev = curr;
        }

            return prev[T];
    }
}
