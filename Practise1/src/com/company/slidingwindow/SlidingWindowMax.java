package com.company.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SlidingWindowMax {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int K = s.nextInt();
        int[] a = new int[N];
        for(int i=0; i < N; i++){
            a[i] = s.nextInt();
        }

        int[] ans = new int[N-K+1];

        ans = slidingWIndowNaive(a,N,K);
        for(int i1 : ans){
            System.out.print(i1 + " ");
        }
        System.out.println();

        ans = slidingWindowUsingDeque(a,N,K);
        for(int i1 : ans){
            System.out.print(i1 + " ");
        }
        System.out.println();
    }

    static int[] slidingWIndowNaive(int[] a,int N,int K){

        int[] ans = new int[N-K+1];
        for(int i=0; i <= N - K; i++){
            int max = Integer.MIN_VALUE;
            for(int j = i; j <= i + K - 1; j++){
                max = Math.max(max,a[j]);
            }
            ans[i] = max;
        }
        return ans;
    }

    static int[] slidingWindowUsingDeque(int[] a ,int N, int K) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[N-K+1];
        int j = 0;
        int ind = 0;
        for (int i = 0; i < N; i++){
            if (i == 0){
                j++;
                dq.addLast(i);
                if (j == K){
                    ans[ind++] = a[dq.peek().intValue()];
                    j = 0;
                    dq.poll();
                }
            }
            else{

                if ( i >= K){
                    if (!dq.isEmpty() && a[i]  < a[dq.peekFirst().intValue()]){
                        dq.add(i);
                        //System.out.println(a[dq.peekFirst().intValue()]);
                    }
                    else{
                        if (!dq.isEmpty() && a[i]  >= a[dq.peekFirst().intValue()]){
                            while(!dq.isEmpty()){
                                dq.pollFirst();
                            }
                            dq.add(i);
                        }
                    }
                    j++;
                    ans[ind++] = a[dq.peekFirst()];
                }
                else{
                    if (j < K && i < K){
                        if (!dq.isEmpty() && a[i]  < a[dq.peekFirst().intValue()]){
                            dq.add(i);
                          //  System.out.println(a[dq.peekFirst().intValue()]);
                        }
                        else{
                            if (!dq.isEmpty() && a[i]  >= a[dq.peekFirst().intValue()]){
                                while(!dq.isEmpty()){
                                    dq.pollFirst();
                                }
                                dq.add(i);
                            }
                        }
                        j++;
                    }
                    if (j == K){
                        if (!dq.isEmpty() && a[i]  >= a[dq.peekFirst().intValue()]){
                            while(!dq.isEmpty()){
                                dq.pollFirst();
                            }
                            dq.add(i);
                        }
                        while(!dq.isEmpty() && a[dq.peekLast().intValue()] < a[i])
                            dq.pollLast();
                        if (!dq.isEmpty()) {
                            ans[ind++] = a[dq.peekFirst().intValue()];
                            //System.out.println(ans[ind-1]);
                        }

                        //j = 1;
                    }
                   j++;
                }

            }
        }
        return ans;
    }
}
