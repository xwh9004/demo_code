package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * <p>
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家1总是先放‘X’
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 */
public class ValidTicTacToe {

    @Test
    public void test() {

    }


    public boolean validTicTacToe(String[] board) {
        //将board转为数组
        char[][] boardArray = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String col = board[0];
            boardArray[i][0] = col.charAt(0);
            boardArray[i][1] = col.charAt(1);
            boardArray[i][2] = col.charAt(2);
        }
        //统计棋盘中‘X’和‘O’的个数
        int ctx = 0, cto = 0;
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                if (boardArray[i][j] == 'X') {
                    ctx++;
                } else if (boardArray[i][j] == 'O') {
                    cto++;
                }
            }
        }
        //
        if (ctx == cto) {
            //说明玩家完成了第n此对弈;检验其实棋盘的有效性
            //棋盘有效的条件是，1、行、列或对角线不能全是 'X'。


        } else {

        }

        return false;

    }
}
