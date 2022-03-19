package com.company.kadanealgo;

import java.util.Scanner;

public class KadaneAlgorithm {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];
        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }
        int max = kadaneAlgorithm(a);

        System.out.println("max = " + max);
    }

   static  int kadaneAlgorithm(int[] a){
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i < a.length; i++){
            if (sum < 0){
                sum = 0;
            }
            else{
                sum = sum + a[i];
                max = Math.max(max,sum);
            }
        }

        return max;
    }
}
