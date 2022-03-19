package com.company.dp;

import java.util.Scanner;

public class BuyAndSellStockProfitMax {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for(int i=0; i < n; i++){
            a[i] = s.nextInt();
        }

        int maxProfit = maximumProfit(a,n);
        System.out.println("maxProfit = " + maxProfit);
    }

    static int maximumProfit(int[] cost, int n){
        int mini = cost[0];
        int profit = 0;

        for(int i=1; i < n; i++){
            int cost1 = cost[i] - mini;
            profit = Math.max(profit,cost1);
            mini = Math.min(mini,cost[i]);
        }

        return profit;
    }
}
