package com.company.dp;


import java.util.Scanner;

public class RodCuttingProblem {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        int[] price = new int[N];
        for(int i=0; i < N; i++){
            price[i] = s.nextInt();
        }

        int[][] dp = new int[N][N+1];

        for(int i=0; i < N; i++){
            for(int j=0; j <= N; j++){
                dp[i][j] = -1;
            }
        }

        int maxProfit = recurse(N-1,N,price,dp);
        System.out.println("maxProfit = " + maxProfit);
        dp = new int[N][N+1];
        int maxProfit1 = tabulization(N,price,dp);
        System.out.println("maxProfit = " + maxProfit1);
        int maxProfit2 = spaceOpt(N,price);
        System.out.println("maxProfit = " + maxProfit2);
        int maxPofit3 = spaceOpt1d(N,price);
        System.out.println("maxProfit = " + maxPofit3);
    }

    static int recurse(int i,int j, int[] price,int[][] dp){
        if (i < 0 || j < 0) return Integer.MIN_VALUE;

        if(i == 0){
            return j*price[0];
        }

        if (dp[i][j] != -1) return dp[i][j];

        int nontake = 0 + recurse(i-1, j,price,dp);

        int take = Integer.MIN_VALUE;

        if (i+1 <= j)
            take = price[i] + recurse(i,j - i-1,price,dp);

        dp[i][j] = Math.max(take,nontake);

       // System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
        return dp[i][j];
    }

    static int tabulization(int N,int[] price,int[][] dp){

        for(int i=0; i <= N; i++){
            dp[0][i] =  i*price[0];
        }

        for(int i=1; i < N; i++) {
            for (int j = 0; j <= N; j++) {

                int nontake = 0 + dp[i - 1][j];

                int take = Integer.MIN_VALUE;

                if (i + 1 <= j)
                    take = price[i] + dp[i][ j - i - 1];

                dp[i][j] = Math.max(take, nontake);
            }
        }
        // System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
        return dp[N-1][N];

    }
    static int spaceOpt(int N,int[] price){
        int[] prev = new int[N+1];
        for(int i=0; i <= N; i++){
            prev[i] =  i*price[0];
        }

        for(int i=1; i < N; i++) {
            int[] curr = new int[N+1];
            for (int j = 0; j <= N; j++) {

                int nontake = 0 + prev[j];

                int take = Integer.MIN_VALUE;

                if (i + 1 <= j)
                    take = price[i] + curr[ j - i - 1];

                curr[j] = Math.max(take, nontake);
            }
            prev = curr;
        }
        // System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
        return prev[N];

    }
    static int spaceOpt1d(int N,int[] price){
        int[] prev = new int[N+1];
        for(int i=0; i <= N; i++){
            prev[i] =  i*price[0];
        }

        for(int i=1; i < N; i++) {
            //int[] curr = new int[N+1];
            for (int j = 0; j <= N; j++) {

                int nontake = 0 + prev[j];

                int take = Integer.MIN_VALUE;

                if (i + 1 <= j)
                    take = price[i] + prev[ j - i - 1];

                prev[j] = Math.max(take, nontake);
            }

        }
        // System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
        return prev[N];

    }
}
