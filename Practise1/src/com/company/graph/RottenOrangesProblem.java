package com.company.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
     0 -> empty cell
     1 -> fresh orange
     2 -> rotten orange
 */
public class RottenOrangesProblem {
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int N = s.nextInt();
        int[][] grid = new int[N][N];
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                grid[i][j] = s.nextInt();
            }
        }

        int totalCountRotten = 0;
        Queue<int[]> q = new LinkedList<>();
        int totalCountNonEmpty = 0;
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                if (grid[i][j] == 2){
                    q.add(new int[]{i,j});
                    totalCountRotten++;
                    totalCountNonEmpty++;
                }
                if (grid[i][j] == 1){
                    totalCountNonEmpty++;
                }
            }
        }

        int minTime = bfs(q,grid,totalCountRotten,totalCountNonEmpty);
        System.out.println("minTime = " + minTime);
    }

    static int bfs(Queue<int[]> q,int[][] grid,int k,int tot){
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        int ans = 0;
        int cnt = 0;
        while(!q.isEmpty()){
            k = q.size();
            cnt = cnt + k;
            while(k > 0 ) {
                k--;
                int[] coord = new int[2];
                coord = q.poll();
                int x = coord[0];
                int y = coord[1];
                for(int i=0; i < 4; i++){
                    if (x+dx[i] >= 0 && x + dx[i] < grid.length && y + dy[i] >= 0 && y+dy[i] < grid.length
                         && grid[x+dx[i]][y+dy[i]] != 0 && grid[x+dx[i]][y + dy[i]] != 2){
                        q.add(new int[]{x+dx[i],y + dy[i]});
                        grid[x+dx[i]][y + dy[i]] = 2;
                    }
                }


            }
            if (!q.isEmpty()){
                ans++;
            }
        }
        return tot == cnt ? ans : -1;
    }
}

