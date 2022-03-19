package com.company.dp;

import java.util.Scanner;
import java.util.Stack;

public class PrintingLCS {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String s1 = s.next();
        String s2 = s.next();

        int N = s1.length();
        int M = s2.length();

        int[][] dp = new int[N+1][M+1];

        for(int i=0; i <= N; i++){
            for(int j=0; j <= M; j++ ){
                dp[i][j] = -1;
            }
        }

        int lcs = recurse(N,M,s1,s2,dp);
        System.out.println("length of the longest common subsequence = " + lcs);

        int lcs1 = tabulation(N,M,s1,s2,dp);
        System.out.println(" length of the longest common subseequence = " + lcs1);

        Stack<Character> stk = printingLCS(N,M,s1,s2,dp);

        String res = "";

        while(!stk.isEmpty()){
            res = res + stk.pop();
        }

        System.out.println("The longest common subsequence is = " + res);

    }

    static Stack<Character> printingLCS(int N,int M,String s1,String s2,int[][] dp){
        Stack<Character> stk = new Stack<>();

        int i= N;
        int j = M;

        while(i > 0 && j > 0){
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                stk.push(s1.charAt(i-1));
                i--;
                j--;
            }
            else{
                if (dp[i-1][j] > dp[i][j-1]){
                    i--;
                }
                else{
                    j--;
                }
            }
        }
        return stk;
    }

    static int recurse(int i,int j,String s1,String s2,int[][] dp){
        if (i == 0 || j == 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i-1) == s2.charAt(j-1)){
            dp[i][j] = 1 + recurse(i-1,j-1,s1,s2,dp);
        }
        else{
            dp[i][j] = Math.max(recurse(i,j-1,s1,s2,dp),recurse(i-1, j ,s1,s2,dp));
        }

        return dp[i][j];
    }

    static int tabulation(int N,int M, String s1,String s2,int[][] dp){
        for(int i=0; i <= N; i++){
            dp[i][0] = 0;
        }
        for(int j=0; j <= M; j++){
            dp[0][j] = 0;
        }

        for(int i=1; i <= N; i++ ){
            for(int j=1; j <= M; j++){
                if (s1.charAt(i-1) == s2.charAt(j -1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[N][M];
    }
}
