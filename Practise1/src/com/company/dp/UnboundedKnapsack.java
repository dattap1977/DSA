package com.company.dp;

import java.util.Scanner;

public class UnboundedKnapsack {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        int W = s.nextInt();

        int[] wt = new int[N];

        for(int i=0; i < N; i++){
            wt[i] = s.nextInt();
        }
        int[] val = new int[N];
        for(int i=0; i < N; i++){
            val[i] = s.nextInt();
        }
        int ind = N-1;
        int[][] dp = new int[N][W+1];
        for(int i=0; i < N; i++){
            for(int j=0; j < W+1; j++){
                dp[i][j] = -1;
            }
        }

        int maxVal = recurse(ind,W,wt,val,dp);
        System.out.println("Max value is = " + maxVal);

        dp = new int[N][W+1];
        int maxVal1 = tabulization(N,W,wt,val,dp);
        System.out.println("Max value is = " + maxVal1);

        int maxVal2 = spaceOPt(N,W,wt,val);
        System.out.println("Max value is = " + maxVal2);
    }

    static int recurse(int ind,int W,int[] wt,int[] val,int[][] dp){

        if (ind < 0 || W < 0){
            return Integer.MIN_VALUE;
        }

        if (ind == 0 && wt[0] <= W){
            return (W/wt[0])*val[0];
        }

        if (dp[ind][W] != -1) return dp[ind][W];
        int notPick = 0 + recurse(ind - 1,W,wt,val,dp);
        int pick = Integer.MIN_VALUE;
        if (W - wt[ind] >= 0)
            pick = val[ind] + recurse(ind,W - wt[ind],wt,val,dp);

        dp[ind][W] = Math.max(notPick,pick);
        return dp[ind][W];

    }

    static int tabulization(int N, int W,int[] wt,int[] val,int[][] dp){
        for(int i=0; i <= W; i++){
            if (i >= wt[0])
                dp[0][i] = (i/wt[0])*val[0];
        }

        for(int i=1; i < N; i++){
            for(int j=0; j <= W ; j=j+1){

                int notPick = 0 + dp[i-1][j];
                int pick = Integer.MIN_VALUE;
                if (j >= wt[i])
                    pick = val[i] + dp[i][j - wt[i]];
                dp[i][j] = Math.max(notPick,pick);
                //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
            }
        }
        return dp[N-1][W];
    }

    static int spaceOPt(int N,int W,int[] wt,int[] val){
        int[] prev = new int[W+1];
        for(int i=0; i <= W; i++){
            if (i >= wt[0])
                prev[i] = (i/wt[0])*val[0];
        }

        for(int i=1; i < N; i++){
            int[] curr = new int[W+1];
            for(int j=0; j <= W ; j=j+1){

                int notPick = 0 + prev[j];
                int pick = Integer.MIN_VALUE;
                if (j >= wt[i])
                    pick = val[i] + prev[j - wt[i]];
                curr[j] = Math.max(notPick,pick);
                //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
            }
            prev = curr;
        }
        return prev[W];
    }
}
