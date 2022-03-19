package com.company.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAllSubsequences {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];

        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }
        ArrayList<Integer> res = new ArrayList<>();
        printAllSubsequences(0,res,a);
    }

    static void printAllSubsequences(int ind,ArrayList<Integer> res,int[] a){

        if (ind >= a.length){
            System.out.println(res);
            return;
        }

        printAllSubsequences(ind + 1,res,a);
        res.add(a[ind]);
        printAllSubsequences(ind + 1,res,a);
        res.remove(Integer.valueOf(a[ind]));
        return;
    }
}
