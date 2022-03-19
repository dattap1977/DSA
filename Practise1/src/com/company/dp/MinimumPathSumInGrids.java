package com.company.dp;

import java.util.Scanner;

public class MinimumPathSumInGrids {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();

        int[][] arr = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                arr[i][j] = s.nextInt();
            }
        }

        int[][] dp = new int[m][n];

        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                dp[i][j] = -1;
            }
        }

        int ans = recurse(m-1,n-1,dp,arr);

        System.out.println("ans =" + ans);
        dp = new int[m][n];
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                dp[i][j] = 0;
            }
        }
        int anst = tabulization(m-1,n-1,dp,arr);
        System.out.println("ans 1 = " + anst);
        int[] curr = new int[n];
        int[] prev = new int[n];
        int ansso = spaceOpt(curr,prev,arr);
        System.out.println("ansso = " + ansso);

    }

    static int recurse(int i,int j,int[][] dp,int[][] arr){

        if(i < 0 || j < 0) return Integer.MAX_VALUE - 1000;
        if (i == 0 && j == 0) return  arr[0][0];


        if (dp[i][j] != -1) return dp[i][j];
        int up = 0;
        int left = 0;

        up = arr[i][j] + recurse(i-1,j,dp,arr);
        left = arr[i][j] + recurse(i,j-1,dp,arr);

        dp[i][j] = Math.min(up,left);
        //System.out.println("i = " + i + "j = " + j + "dp = " + dp[i][j]);
        return dp[i][j];
    }

    static int tabulization(int m,int n,int[][] dp,int[][] arr){

        int up = 0;
        int left = 0;
        for(int i=0; i <= m; i++){
            for(int j=0; j <= n; j++){
                up = 0;
                left = 0;

                    if (i == 0 && j == 0) {
                        dp[0][0] = arr[0][0];
                    } else {
                        if (i > 0)
                            up = dp[i - 1][j] + arr[i][j];
                        else
                            up = dp[i][j-1] + arr[i][j];

                        if (j > 0)
                            left = dp[i][j - 1] + arr[i][j];
                        else
                            left = dp[i-1][j] + arr[i][j];

                        dp[i][j] = Math.min(up,left);

                    }

            }
        }
        return dp[m][n];
    }

    static int spaceOpt(int[] curr,int[] prev,int[][] arr){
        int up = 0;
        int left = 0;
        int n = arr[0].length;
        int m = arr.length;
        prev[0] = arr[0][0];

        for(int i=0; i < m; i++){
            // up = 0;
            // left= 0;
            curr = new int[n];
            for(int j=0; j < n; j++){
                up = 0;
                left = 0;
                    if (i == 0 && j == 0) {
                        curr[0] = arr[0][0];
                    } else {
                        if (i > 0)
                            up = prev[j] + arr[i][j];
                        else
                            up = curr[j-1] + arr[i][j];

                        if (j > 0)
                            left = curr[j - 1] + arr[i][j];
                        else
                            left = prev[j] + arr[i][j];
                        curr[j] = Math.min(up,left);
                    }

            }
            prev = curr;
        }
        return prev[n-1];
    }
}
