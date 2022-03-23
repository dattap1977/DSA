package com.company.dp;

import java.util.Scanner;

public class MCM {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] arr = new int[N];

        int[][] dp = new int[N][N];
        for(int i=0; i < N; i++){
            arr[i] = s.nextInt();
        }

        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                dp[i][j] = -1;
            }
        }


        int minOps = recurse(1,N-1,arr,dp);

        System.out.println("ans = " + minOps);

        dp = new int[N][N];

        int minOps1 = tabulation(N,arr, dp);

        System.out.println("ans = " + minOps1);

    }

    static int recurse(int i ,int j, int[] arr,int[][] dp){
        if (i == j)
            return 0;

        if (dp[i][j] != -1) return dp[i][j];
        int min = 1000000000;
        for(int k=i; k < j; k++){
            int steps = arr[i-1] * arr[k] * arr[j] + recurse(i,k,arr,dp) + recurse(k+1,j,arr,dp);
            if (min > steps){
                min = steps;
            }
        }
        dp[i][j] = min;
        return dp[i][j];
    }

    static int tabulation(int N,int[] arr,int[][] dp ){
            for(int i=0; i < N; i++)
                dp[i][i] = 0;

            for(int i= N-1; i >= 1; i--){
                for(int j=i+1; j <= N-1; j++){
                    int min = 1000000000;
                    for(int k=i; k < j; k++){
                        int steps = arr[i-1] * arr[k] * arr[j] + dp[i][k] + dp[k+1][j];
                        if (min > steps){
                            min = steps;
                        }
                    }
                    dp[i][j] = min;
                }
            }
            return dp[1][N-1];
    }
}
