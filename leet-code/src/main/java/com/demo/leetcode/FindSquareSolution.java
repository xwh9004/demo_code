package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
 * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
 * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
 *
 [1,0,1],
 [0,0,1],
 [0,0,1]
 输出: [1,0,2]
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:21 on 2021/3/24
 * @version V0.1
 * @classNmae FindSquareSolution
 */
public class FindSquareSolution {

    public static void main(String[] args) {
        int size = 3;
        int[][] matrix = new int[size][size];
        for (int i = 0;i<matrix.length;i++){
            for (int j = 0;j<matrix[i].length;j++){
                 if(j==i){
                     matrix[i][j] =0;
                 }else{
                     matrix[i][j] =1;
                 }
            }
        }
//        matrix[0][0] =1;
        FindSquareSolution solution = new FindSquareSolution();
        int[] result =solution.findSquare(matrix);
        System.out.print(result[0]+",");
        System.out.print(result[1]+",");
        System.out.println(result[2]);
    }



    public int[] findSquare(int[][] matrix) {
        int[] result = new int[3];

        int length = matrix.length;

        for (int size=length;size>0;size--){
            for (int row=0;row<length;row++){
                for (int col=0;col<length;col++){
                    if(findSquare(matrix,row,col,size)){
                        result[0]=row;
                        result[1]=col;
                        result[2]=size;
                        return result;
                    }
                }
            }
        }

        return new int[0];
    }

    public boolean findSquare(int[][] matrix,int row,int col,int size){
        int maxSize =matrix.length;
        int row_end = row + size ;
        int col_end = col + size ;
        if(row>maxSize ||col >maxSize
           || row_end>maxSize ||col_end >maxSize){
            return false;
        }
        for (int i=row;i<row_end;i++){
            if(matrix[i][col] !=0 || matrix[i][col_end-1] !=0){
                return false;
            }
        }
        for (int i=col;i<col_end;i++){
            if(matrix[row][i]!=0 || matrix[row_end-1][i]!=0){
                return false;
            }
        }
        return true;
    }
}
