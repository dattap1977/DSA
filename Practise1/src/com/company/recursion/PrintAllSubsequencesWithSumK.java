package com.company.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAllSubsequencesWithSumK {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];

        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }
        int K = s.nextInt();
        ArrayList<Integer> res = new ArrayList<>();
        printAllSubsequences(0,res,a,0,K);
    }

    static void printAllSubsequences(int ind,ArrayList<Integer> res,int[] a,int sum,int K){

        if (ind >= a.length){
            if (sum == K)
            System.out.println(res);
            return;
        }

        printAllSubsequences(ind + 1,res,a,sum,K);
        res.add(a[ind]);
        printAllSubsequences(ind + 1,res,a,sum + a[ind],K);
        res.remove(Integer.valueOf(a[ind]));
        return;
    }
}
