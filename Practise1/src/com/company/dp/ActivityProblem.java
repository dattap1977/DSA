package com.company.dp;

/**
 * Given a two dimensional array of NX3, find out the maximum money earned when the
 * same activity cam't be done for two consecutive days
 */

public class ActivityProblem {
    public static void main(String[] args){
        int[][] arr = {
                       {3,10,1},
                       {4,7,11},
                       {6,1,2},
                       {5,2,3}
                      };
        int n = arr.length;
        int[][] dp = new int[n][4];
        for(int i=0; i < n; i++){
            for(int j=0; j < 4; j++){
                dp[i][j] = -1;
            }
        }
        int idx = n-1;
        int last = 3;
        int maxi = -2;
        int maxCost = recurse(idx,last,arr,dp);
        System.out.println("maxCost is = " + maxCost);
        dp = new int[n][4];
        for(int i=0; i < n; i++){
            for(int j=0; j < 4; j++){
                dp[i][j] = 0;
            }
        }
        int maxCost1 = tabulisation(arr,dp);
        System.out.println("maxCost1 = " + maxCost1);
        int maxCost2 = spaceOpt(arr);
        System.out.println("maxCost2 = " + maxCost2);
    }

    static int recurse(int idx, int last, int[][] arr,int[][] dp){
               if (idx == 0){
                   int maxi = 0;
                   for(int i=0; i <= 2; i++){
                       if (i != last){
                            maxi = Math.max(arr[0][i] ,maxi);
                          // System.out.println("maxi = " + maxi + "idx0 = " + idx);
                       }
                   }
                   return maxi;
               }

               if (dp[idx][last] != -1 ) return dp[idx][last];

               int maxi = 0;
               for(int i=0; i < 3; i++){

                   if (i != last){
                       int max = arr[idx][i] + recurse(idx-1,i,arr,dp);
                       maxi = Math.max(max,maxi);
                      // System.out.println("max = " + maxi + "idx = " + idx);

                   }


               }

        dp[idx][last] = maxi;

        return dp[idx][last];
    }

    static int tabulisation(int[][] arr,int[][] dp){
            int maxi = 0;
            int last = 0;
            for(int i=0; i <= 2; i++){
                    if (maxi < arr[0][i]) {
                        maxi = arr[0][i];
                        last = i;
                        //dp[0][i] = maxi;
                    }
                    // System.out.println("maxi = " + maxi + "idx0 = " + idx);

            }
            dp[0][last] = maxi;
            //System.out.println("max0 = " + dp[0][last]);
            int max = 0;
            int j=0;
        for( j=1; j <= arr.length - 1; j++) {
            max = 0;
            int maxind = -1;
        for(int i=0; i < 3; i++){
            //System.out.println(i + " " + last);

            if (i != last){
                    if (max < arr[j][i] + dp[j-1][last]) {
                        max = arr[j][i] + dp[j-1][last];
                        maxind = i;
                        //System.out.println("max1 = " + max + "last = " + last);
                    }
                }
            }
            last = maxind;
            dp[j][last] = Math.max(max,maxi);
            maxi = dp[j][last];

            //System.out.println("max = " + maxi + "last = " + last);
        }
        return dp[arr.length-1][last];
    }

    static int spaceOpt(int[][] arr){
        int maxi = 0;
        int last = 0;
        for(int i=0; i <= 2; i++){
            if (maxi < arr[0][i]) {
                maxi = arr[0][i];
                last = i;
                //dp[0][i] = maxi;
            }
            // System.out.println("maxi = " + maxi + "idx0 = " + idx);

        }
        int prev = maxi;
        //System.out.println("max0 = " + dp[0][last]);
        int max = 0;
        int j=0;
        for( j=1; j <= arr.length - 1; j++) {
            max = 0;
            int maxind = -1;
            for(int i=0; i < 3; i++){
                //System.out.println(i + " " + last);

                if (i != last){
                    if (max < arr[j][i] + prev) {
                        max = arr[j][i] + prev;
                        maxind = i;
                        //System.out.println("max1 = " + max + "last = " + last);
                    }
                }
            }
            last = maxind;
            int curr = Math.max(max,maxi);
            maxi = curr;
            prev = curr;

            //System.out.println("max = " + maxi + "last = " + last);
        }
        return prev;
    }
}
