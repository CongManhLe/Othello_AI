package logic;

import utils.Constants;
import java.util.*;

public class Board {
    private int[][] board;

    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},          {0, 1},
            {1, -1},  {1, 0}, {1, 1}
    };

    // Constructor mặc định
    public Board() {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        board[3][3] = Constants.WHITE;
        board[3][4] = Constants.BLACK;
        board[4][3] = Constants.BLACK;
        board[4][4] = Constants.WHITE;
    }

    // Constructor sao chép - cần cho Minimax AI
    public Board(Board other) {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            System.arraycopy(other.board[i], 0, this.board[i], 0, Constants.BOARD_SIZE);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean isValidMove(int row, int col, int player) {
        if (!inBounds(row, col) || board[row][col] != Constants.EMPTY) return false;

        int opponent = Constants.getOpponent(player);

        for (int[] d : DIRECTIONS) {
            int r = row + d[0], c = col + d[1];
            boolean hasOpponentBetween = false;

            while (inBounds(r, c) && board[r][c] == opponent) {
                r += d[0];
                c += d[1];
                hasOpponentBetween = true;
            }

            if (hasOpponentBetween && inBounds(r, c) && board[r][c] == player)
                return true;
        }

        return false;
    }

    public boolean playMove(int row, int col, int player) {
        if (!isValidMove(row, col, player)) return false;

        board[row][col] = player;
        int opponent = Constants.getOpponent(player);

        for (int[] d : DIRECTIONS) {
            int r = row + d[0], c = col + d[1];
            List<int[]> toFlip = new ArrayList<>();

            while (inBounds(r, c) && board[r][c] == opponent) {
                toFlip.add(new int[]{r, c});
                r += d[0];
                c += d[1];
            }

            if (inBounds(r, c) && board[r][c] == player) {
                for (int[] pos : toFlip) {
                    board[pos[0]][pos[1]] = player;
                }
            }
        }
        return true;
    }

    public List<Move> getValidMoves(int player) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (isValidMove(i, j, player))
                    moves.add(new Move(i, j));
            }
        }
        return moves;
    }

    public int countPieces(int player) {
        int count = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == player)
                    count++;
            }
        }
        return count;
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < Constants.BOARD_SIZE && col >= 0 && col < Constants.BOARD_SIZE;
    }
}
