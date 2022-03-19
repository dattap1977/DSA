package com.company.dp;

import java.util.Scanner;

public class UniquePaths {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();



        int[][] dp = new int[m][n];

        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                dp[i][j] = -1;
            }
        }

        int ans = recurse(m-1,n-1,dp);

        System.out.println("ans =" + ans);
        dp = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                dp[i][j] = 0;
            }
        }
        int anst = tabulization(m-1,n-1,dp);
        System.out.println("ans 1 = " + anst);
        int[] curr = new int[n];
        int[] prev = new int[n];
        int ansso = spaceOpt(curr,prev,m);
        System.out.println("ansso = " + ansso);

    }

    static int recurse(int i,int j,int[][] dp){
         if (i == 0 && j == 0) return 1;
         if(i < 0 || j < 0) return 0;

         if (dp[i][j] != -1) return dp[i][j];

         int up = recurse(i-1,j,dp);
         int left = recurse(i,j-1,dp);

         dp[i][j] = up + left;

         return dp[i][j];
    }

    static int tabulization(int m,int n,int[][] dp){

        int up = 0;
        int left = 0;
        for(int i=0; i <= m; i++){
            for(int j=0; j <= n; j++){
                up = 0;
                left = 0;
                if (i == 0 && j == 0){
                    dp[0][0] = 1;
                }
                else {
                    if (i > 0)
                        up = dp[i-1][j];

                    if (j > 0)
                        left = dp[i][j-1];
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m][n];
    }

    static int spaceOpt(int[] curr,int[] prev,int m){
        int up = 0;
        int left = 0;
        int n = curr.length;

        prev[0] = 1;

        for(int i=0; i < m; i++){
           // up = 0;
           // left= 0;
               curr = new int[n];
            for(int j=0; j < n; j++){
             up = 0;
             left = 0;
             if (i == 0 && j == 0){
                                    curr[0] = 1;
                                }
                                else {
                                    if (i > 0)
                                        up = prev[j];

                                    if (j > 0)
                                        left = curr[j-1];
                                    curr[j] = up + left;
                                }
              }
            prev = curr;
        }
        return curr[n-1];
    }
}
