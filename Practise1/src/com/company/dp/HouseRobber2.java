package com.company.dp;

import java.util.Arrays;

public class HouseRobber2 {
    public static void main(String[] args){
        int[] arr = {9,1,4,8,9};

        int idx = arr.length - 2;
        int n = arr.length;
        int[] dp = new int[n-1];
        Arrays.fill(dp,-1);
        int[] temp1 = new int[arr.length - 1];
        int[] temp2 = new int[arr.length - 1];

        for(int i=1; i < arr.length; i++){
            temp1[i-1] = arr[i];
        }
        for(int i=0; i < arr.length - 1; i++){
            temp2[i] = arr[i];
        }
        int maxCostLast = recurse(idx,temp1,dp);
        Arrays.fill(dp,-1);
        int maxCostFirst = recurse(idx,temp2,dp);

        System.out.println("maxCost = " + Math.max(maxCostFirst,maxCostLast));
        Arrays.fill(dp, 0);
        int maxCost1Last = tabulisation(temp1,dp);
        Arrays.fill(dp,0);
        int maxCost1First = tabulisation(temp2,dp);



       // System.out.println("maxCost = " + maxCost);
        System.out.println("maxCost1 = " + Math.max(maxCost1First,maxCost1Last));

        int maxCost2Last = spaceOpt(temp1);
        int maxCost2First = spaceOpt(temp2);
        System.out.println("maxCost2 = " + Math.max(maxCost2First,maxCost2Last));
    }

    static int recurse(int idx,int[] arr,int[] dp) {
        if (idx == 0) return arr[0];
        if (idx < 0) return 0;

        if (dp[idx] != -1) return dp[idx];


        int pick = arr[idx] + recurse(idx - 2, arr, dp) ;
        int notPick = recurse(idx - 1, arr, dp);

        dp[idx] = Math.max(pick, notPick);

        return dp[idx];

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
