package com.company.dp;

import java.util.Arrays;

public class HouseRobber {
    public static void main(String[] args){
        int[] arr = {2,1,4,9};

        int idx = 3;
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        int maxCost = recurse(idx,arr,dp);
        Arrays.fill(dp, 0);
        int maxCost1 = tabulisation(arr,dp);

        int maxCost2 = spaceOpt(arr);

        System.out.println("maxCost = " + maxCost);
        System.out.println("maxCost1 = " + maxCost1);
        System.out.println("maxCost2 = " + maxCost2);
    }

     static int recurse(int idx,int[] arr,int[] dp) {
        if (idx == 0) return arr[0];
        if (idx < 0) return 0;

        if (dp[idx] != -1) return dp[idx];

        int pick = recurse(idx - 2, arr, dp) + arr[idx];
        int notPick = recurse(idx - 1, arr, dp);

        dp[idx] = Math.max(pick, notPick);

        return Math.max(pick, notPick);

    }

    static int tabulisation(int[] arr, int[] dp){

        dp[0] = arr[0];
        int neg = 0;
        for(int i=1; i < arr.length; i++){
            if (i > 1)
            dp[i] = Math.max(dp[i-2] + arr[i], dp[i-1]);
            else{
                dp[i] = Math.max(neg + arr[i],dp[i-1]);
            }
        }

        return dp[arr.length-1];

    }

    static int spaceOpt(int[] arr){
        int prev = arr[0];
        int prev2 = 0;
        int curr = 0;
        for(int i=1; i < arr.length; i++){
            if (i > 1)
                curr = Math.max(prev2 + arr[i], prev);
            else{
                curr = Math.max(arr[i],prev);
            }
            prev2 = prev;
            prev = curr;

        }
        return prev;
    }
}

