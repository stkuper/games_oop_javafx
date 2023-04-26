package ru.job4j.puzzle;

public class Win {
    public static boolean check(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 1 && (monoHorizontal(board, i) || monoVertical(board, i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean monoVertical(int[][] board, int column) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][column] != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean monoHorizontal(int[][] board, int row) {
        for (int column = 0; column < board[row].length; column++) {
            if (board[row][column] != 1) {
                return false;
            }
        }
        return true;
    }
}
