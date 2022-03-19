package com.company.dp;

import java.util.Scanner;
import java.util.Stack;

public class LengthOfShortestCommonSuperSequence {
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
        System.out.println("length of the shortest common supersequence = " + (N+M - lcs));

        int lcs1 = tabulation(N,M,s1,s2,dp);
        System.out.println("length of the shortest common supersequence = " + (N+M - lcs1));

        int lcs2 = spaceOpt(N,M,s1,s2);
        System.out.println("length of the shortest common supersequence = " + (N+M - lcs2));

        String res = printSCS(N,M,s1,s2,dp);

        System.out.println("Shortest common supersequence is = " + res);
    }

    static String printSCS(int N,int M, String s1,String s2,int[][] dp){
        int i = N;
        int j = M;
        Stack<Character> stk = new Stack<>();
        while(i > 0 && j > 0){
            if (s1.charAt(i- 1) == s2.charAt(j-1)){
                stk.push(s1.charAt(i-1));
                i--;
                j--;
            }
            else{
                if (dp[i-1][j] > dp[i][j-1]){
                    stk.push(s1.charAt(i-1));
                    i--;
                }
                else{
                    stk.push(s2.charAt(j-1));
                    j--;
                }
            }
        }

        while(i > 0){
            stk.push(s1.charAt(i-1));
            i--;
        }
        while(j > 0){
            stk.push(s2.charAt(j-1));
            j--;
        }

        String ans = "";

        while(!stk.isEmpty()){
            ans = ans + stk.pop();
        }

        return ans;
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

    static int spaceOpt(int N,int M, String s1,String s2){
        int[] prev = new int[M+1];
        for(int j=0; j <= M; j++){
            prev[j] = 0;
        }
        int[] cur = new int[M+1];
        for(int i=1; i <= N; i++ ){
            cur = new int[M+1];
            for(int j=1; j <= M; j++){
                if (s1.charAt(i-1) == s2.charAt(j -1)){
                    cur[j] = 1 + prev[j-1];
                }
                else{
                    cur[j] = Math.max(cur[j-1],prev[j]);
                }
            }
            prev = cur;
        }
        return prev[M];

    }
}
