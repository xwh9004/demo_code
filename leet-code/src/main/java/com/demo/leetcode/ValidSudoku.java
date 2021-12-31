package com.demo.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * <p>
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用'.'表示
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidSudoku {

    @Test
    public void test() {
        char[][] board = {
                  {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        ValidSudoku validSudoku = new ValidSudoku();
//        System.out.println(validSudoku.isValidSudoku(board));
        validSudoku.solveSudoku(board);

    }


    public boolean isValidSudoku(char[][] board) {
        //行记录
        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        //列记录
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        //3x3方正记录
        Map<Integer, Set<Integer>> sqrMap = new HashMap<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                int value = board[r][c] - '0';
                if (!putMap(rowMap, r, value)
                        || !putMap(colMap, c, value)
                        || !putMap(sqrMap, getSqrNum(r, c), value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean putMap(Map<Integer, Set<Integer>> map, int i, int value) {
        Set<Integer> set = null;
        if (map.containsKey(i)) {
            set = map.get(i);
        } else {
            set = new HashSet<>();
            map.put(i, set);
        }
        return set.add(value);

    }

    /**
     * 计算坐标属于的方格号
     *
     * @param i
     * @param j
     * @return
     */
    public int getSqrNum(int i, int j) {
        int r = (i) / 3; //0-2:0,3-5:1,6-8:2
        int c = (j) / 3;

        return r * 3 + c;
    }

    public void solveSudoku(char[][] board) {
        List<int[]> spaces = new ArrayList<int[]>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char value = board[i][j];
                if (value == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        boolean valid = dfs(board, spaces, 0);
        StringBuilder sb = new StringBuilder();
        if(valid){
            //输出结果
            for (int i = 0; i < board.length; i++) {

                for (int j = 0; j < board[i].length; j++) {
                    sb.append(board[i][j]+",");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }else {
            //
            System.out.println("数独无解");
        }

    }

    private boolean dfs(char[][] board, List<int[]> spaces, int pos) {
        boolean valid = false;
        if (pos == spaces.size()) {
            valid =true;
            return true;
        }
        int[] coordinate = spaces.get(pos);
        int r = coordinate[0];
        int c = coordinate[1];
        for (int i = 1; i <= 9 && !valid; i++) {
            board[r][c] = (char) (i + '0');
            if (isValidSudoku(board)) {
                 valid = dfs(board, spaces, pos + 1);
                if(!valid){
                    board[r][c] = '.';
                }
            } else {
                board[r][c] = '.';
            }
        }
        return valid;
    }
}
