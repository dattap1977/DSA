package com.company.recursion;

import java.util.Scanner;

public class countAllSubsequncesWithSumK {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];

        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }
        int K = s.nextInt();

        int res = countAllSubsequences(0,a,0,K);
        System.out.println("count = " + res);
    }

    static int countAllSubsequences(int ind,int[] a,int sum,int K){

        if (ind >= a.length){
            if (sum == K)
                return 1;
            else
            return 0;
        }

       int l =  countAllSubsequences(ind + 1,a,sum,K);
       int r =  countAllSubsequences(ind + 1,a,sum + a[ind],K);

        return l + r;
    }
}
