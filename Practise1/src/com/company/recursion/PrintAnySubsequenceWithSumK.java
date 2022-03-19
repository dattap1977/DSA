package com.company.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAnySubsequenceWithSumK {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];

        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }
        int K = s.nextInt();
        ArrayList<Integer> res = new ArrayList<>();
        boolean ans = printAllSubsequences(0,res,a,0,K);
    }

    static boolean printAllSubsequences(int ind,ArrayList<Integer> res,int[] a,int sum,int K){

        if (ind >= a.length){
            if (sum == K) {
                System.out.println(res);
                return true;
            }
            else
                return false;
        }

        if (printAllSubsequences(ind + 1,res,a,sum,K) == true)
            return true;
        res.add(a[ind]);
        if (printAllSubsequences(ind + 1,res,a,sum + a[ind],K)) return true;
        res.remove(Integer.valueOf(a[ind]));
        return false;
    }
}
