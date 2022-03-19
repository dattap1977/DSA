package com.company.dp;

import java.util.Scanner;

public class WIldCard {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String pattern = s.next();
        String s2 = s.next();

        int N = pattern.length();
        int M = s2.length();

        int[][] dp = new int[N+1][M+1];

        for(int i=0; i <= N; i++){
            for(int j=0; j <= M; j++){
                dp[i][j] = -1;
            }
        }

        int match = recurse(N,M,pattern,s2,dp);

        if (match == 1) {
            System.out.println("The pattern matches the string");
        }
        else{
            System.out.println("The pattern does not match the string");
        }

        dp = new int[N+1][M+1];
        int match1 = tabulation(N,M,pattern,s2,dp);
        if (match1 == 1) {
            System.out.println("The pattern matches the string");
        }
        else{
            System.out.println("The pattern does not match the string");
        }

        int match2 = spaceOpt(N,M,pattern,s2);
        if (match2 == 1) {
            System.out.println("The pattern matches the string");
        }
        else{
            System.out.println("The pattern does not match the string");
        }

    }

    static int spaceOpt(int N,int M,String pattern,String s2){
        int[] prev = new int[M+1];
        prev[0] = 1;

        for(int j=1; j <= M; j++){
            prev[j] = 0;
        }

        int[] cur = new int[M+1];


        for(int i=1; i <= N; i++){
            cur = new int[M+1];
            int flag = 1;
            for(int ii=1; ii <= i; ii++ ){
                if (pattern.charAt(ii-1) != '*') {
                    flag = 0;
                    break;
                }
            }
            cur[0] = flag;
            for(int j=1; j <= M; j++){
                if (pattern.charAt(i-1) == s2.charAt(j-1) || pattern.charAt(i-1) == '?'){
                    cur[j] =  prev[j-1];
                }
                else{
                    if (pattern.charAt(i-1) == '*')
                        cur[j] = prev[j] | cur[j-1];
                    else{
                        cur[j] = 0;
                    }
                }
            }
            prev = cur;
        }

        return prev[M];

    }
    static int tabulation(int N,int M,String pattern,String s2,int[][] dp){
        dp[0][0] = 1;

        for(int j=1; j <= M; j++){
            dp[0][j] = 0;
        }

        for(int i=1; i <= N; i++){
            int flag = 1;
            for(int ii=1; ii <= i; ii++ ){
                if (pattern.charAt(ii-1) != '*') {
                    flag = 0;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for(int i=1; i <= N; i++){
            for(int j=1; j <= M; j++){
                if (pattern.charAt(i-1) == s2.charAt(j-1) || pattern.charAt(i-1) == '?'){
                    dp[i][j] =  dp[i-1][j-1];
                }
                else{
                    if (pattern.charAt(i-1) == '*')
                        dp[i][j] = dp[i-1][j] | dp[i][j-1];
                    else{
                        dp[i][j] = 0;
                    }
                }
            }
        }

        return dp[N][M];

    }
    static int recurse(int i,int j,String pattern, String s2, int[][] dp){
        if (i == 0 && j == 0) return 1;
        if (i == 0 && j > 0) return 0;

        if (i > 0 && j == 0){
            for(int ii=1; ii <= i; ii++ ){
                if (pattern.charAt(ii-1) != '*')
                    return 0;
            }
            return 1;
        }

        if (dp[i][j] != -1) return 1;
        if (pattern.charAt(i-1) == s2.charAt(j-1) || pattern.charAt(i-1) == '?'){
            dp[i][j] =  recurse(i-1,j-1,pattern,s2,dp);
        }
        else{
            if (pattern.charAt(i-1) == '*')
            dp[i][j] = recurse(i-1,j,pattern,s2,dp) | recurse(i,j-1,pattern,s2,dp);
            else{
                dp[i][j] = 0;
            }
        }

        return dp[i][j];
    }
}
