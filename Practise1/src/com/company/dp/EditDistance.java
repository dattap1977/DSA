package com.company.dp;

import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String s1 = s.next();
        String s2 = s.next();

        int N = s1.length();
        int M = s2.length();

        int[][] dp = new int[N+1][M+1];

        for(int i=0; i <= N; i++){
            for(int j=0; j <= M; j++){
                dp[i][j] = -1;
            }
        }
        int min = recurse(N,M,s1,s2,dp);
        System.out.println("min = " + min);
        dp = new int[N+1][M+1];
        int min1 = tabulation(N,M,s1,s2,dp);
        System.out.println("min1 = " + min1);
        int min2 = spaceOpt(N,M,s1,s2);
        System.out.println("min2 = " + min2);
    }

    static int recurse(int i,int j,String s1,String s2,int[][] dp){
            if (i == 0) return j;
            if (j == 0) return i;

            if (dp[i][j] != -1) return dp[i][j];

            if (s1.charAt(i-1) == s2.charAt(j-1)){
                dp[i][j] = recurse(i-1,j-1,s1,s2,dp);
            }
            else{
                dp[i][j] = 1 + Math.min(recurse(i-1, j ,s1,s2,dp),Math.min(recurse(i,j-1,s1,s2,dp),
                                 recurse(i-1,j-1,s1,s2,dp)));
            }

            return dp[i][j];
    }

    static int tabulation(int N,int M,String s1,String s2,int[][] dp){
           for(int i=0; i <= N; i++){
               dp[i][0] = i;
           }
           for(int j=0; j <= M; j++){
               dp[0][j] = j;
           }

           for(int i=1; i <= N; i++){
               for(int j=1; j <= M; j++){
                   if (s1.charAt(i-1) == s2.charAt(j-1)){
                       dp[i][j] = dp[i-1][j-1];
                   }
                   else{
                       dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1],dp[i-1][j-1]));
                   }
               }
           }
           return dp[N][M];
    }
    static int spaceOpt(int N,int M,String s1,String s2){

        int[] prev = new int[M+1];
        for(int j=0; j <= M; j++){
            prev[j] = j;
        }
        int[] cur = new int[M+1];
        for(int i=1; i <= N; i++){
            cur = new int[M+1];
            cur[0] = i;
            for(int j=1; j <= M; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = prev[j-1];
                }
                else{
                    cur[j] = 1 + Math.min(prev[j], Math.min(cur[j-1],prev[j-1]));
                }
            }
            prev = cur;
        }
        return prev[M];
    }
}
