package com.company.dp;

import java.util.Scanner;

public class DistinctSubsequences {
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

        int count = recurse(N,M,s1,s2,dp);
        System.out.println("count of " + s2 + " in " + s1 + " = " + count);
        dp = new int[N+1][M+1];
        int count1 = tabulization(N,M,s1,s2,dp);
        System.out.println("count using tabulization = " + count1);
        int count2 = spaceOpt(N,M,s1,s2);
        System.out.println("count using space optimisation = " + count2);
        int count3 = spaceOptUsing1Array(N,M,s1,s2);
        System.out.println("count using further space optimisation = " + count3);

    }

    static int recurse(int i,int j, String s1,String s2,int[][] dp){
        if (j == 0) return 1;
        if (i == 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i-1) == s2.charAt(j-1)){
            dp[i][j] = recurse(i-1,j-1,s1,s2,dp) + recurse(i-1, j , s1,s2,dp);
        }
        else{
            dp[i][j] = recurse(i-1,j,s1,s2,dp);
        }

        return dp[i][j];
    }

    static int tabulization(int N,int M,String s1,String s2,int[][] dp){
        for(int j=0; j <= M; j++){
            dp[0][j] = 0;
        }
        for(int i=0; i <= N; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i <= N; i++){
            for(int j=1; j <= M; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j] ;
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][M];
    }

   static  int spaceOpt(int N,int M,String s1,String s2){
        int[] prev = new int[M+1];
//           for(int j=0; j <= M; j++){
//               prev[j] = 0;
//           }
           prev[0] = 1;
           int[] curr = new int[M+1];
           curr[0] = 1;

       for(int i=1; i <= N; i++){
           //curr = new int[M+1];
           for(int j=1; j <= M; j++){
               if (s1.charAt(i-1) == s2.charAt(j-1)){
                   curr[j] = prev[j-1] + prev[j] ;
               }
               else{
                   curr[j] = prev[j];
               }
           }
           prev = curr;
       }
       return prev[M];
    }
    static int spaceOptUsing1Array(int N,int M,String s1,String s2){
        int[] prev = new int[M+1];
//           for(int j=0; j <= M; j++){
//               prev[j] = 0;
//           }
        prev[0] = 1;

        for(int i=1; i <= N; i++){
            //curr = new int[M+1];
            for(int j=M; j >= 1; j--){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    prev[j] = prev[j-1] + prev[j] ;
                }

            }
        }
        return prev[M];
    }
}
