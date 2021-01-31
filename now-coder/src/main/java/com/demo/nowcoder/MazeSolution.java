package com.demo.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

public class MazeSolution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nms = scanner.nextLine();
            String[] rowCols= nms.split(" ");
            int n =Integer.valueOf(rowCols[0]);
            int m =Integer.valueOf(rowCols[1]);
            int[][] maze = new int[n][m];
            for(int i=0;i<n;i++){
                String valueStr = scanner.nextLine();
                String[] values= valueStr.split(" ");
                for (int j=0;j<m;j++){
                    int value =Integer.valueOf(values[j]);
                    maze[i][j]=value;
                }

            }
            printMazeWay(n,m,maze);
        }





    }


    /**
     *
     * 0 0 1 0 0
     * 0 0 0 1 0
     * 0 0 0 0 1
     * 0 1 1 0 0
     * 0 0 0 1 0
     *
     *
     * @param maze
     */
    public static void printMazeWay(int n,int m,int[][] maze){

        int[][] mazeRoute = new int[n][m];
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                mazeRoute[i][j] = maze[i][j];
            }
        }
        //处理 第n行
        for(int i=m-1;i>0;i--){
            if(mazeRoute[n-1][i]!=0){
                mazeRoute[n-1][i-1] =1;
            }
        }
        //处理 第m列
        for(int k=n-1;k>0;k--){
            if(mazeRoute[k][m-1]!=0){
                mazeRoute[k-1][m-1] =1;
            }
        }
        //处理 1~n 行的 1-m列

        for(int i=n-2;i>=0;i--){
            for(int j=m-2;j>=0;j--){
               if(mazeRoute[i][j+1]==1&&mazeRoute[i+1][j]==1){
                   mazeRoute[i][j]=1;
               }
            }
        }
        int row = 0;int col=0;
        while (row<n&&col<m){
            if(mazeRoute[row][col]==0){
                prinLocation(row,col);

                if(row+1<n&&mazeRoute[row+1][col]==0){
                    row++;
                }else{
                    col++;
                }
            }
        }
    }

    private static void prinLocation(int r,int c){
        System.out.println("("+r+","+c+")");
    }



    public static void test(){
        //        int[][] maze = new int[5][5];
//        maze[0] =new int[] {0,1,0,0,0};
//        maze[1] =new int[] {0,1,0,1,0};
//        maze[2] =new int[] {0,0,0,0,0};
//        maze[3] =new int[] {0,1,1,1,0};
//        maze[4] =new int[] {0,0,0,1,0};
    }
}
