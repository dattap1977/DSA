package com.company.recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAllPermutationsArray {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] a = new int[N];
        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> ds = new ArrayList<>();
        boolean[] freq = new boolean[N];
        recursePermute(a,ds,freq,ans);
        System.out.println(ans);
        ans = new ArrayList<>();
        recursePermute(0,a,ans);
        System.out.println(ans);
    }

    static void recursePermute(int[] a, ArrayList<Integer> ds,boolean[] freq,ArrayList<ArrayList<Integer>> ans){
             if (ds.size() == a.length){
                 ans.add(new ArrayList<>(ds));
                 return;
             }

             for(int i = 0; i < a.length; i++){
                 if (!freq[i]){
                     ds.add(a[i]);
                     freq[i] = true;
                     recursePermute(a,ds,freq,ans);
                     ds.remove(ds.size() - 1);
                     freq[i] = false;
                 }
             }
    }
    static void recursePermute(int index,int[] a,ArrayList<ArrayList<Integer>> ans){
        if (index == a.length){
            ArrayList<Integer> ds = new ArrayList<>();
            for(int i=0; i < a.length; i++){
                ds.add(a[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i= index; i < a.length; i++){
            swap(index,i,a);
            recursePermute(index + 1,a,ans);
            swap(index,i,a);
        }
    }
    static void swap(int index,int i,int[] a){
        int temp = a[index];
        a[index] = a[i];
        a[i] = temp;
    }

}
