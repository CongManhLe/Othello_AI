package ai;

import logic.Board;
import logic.Move;
import utils.Constants;

import java.util.List;

public class SuperHardAI implements AIPlayer {
    private static final int MAX_DEPTH = 6;

    @Override
    public Move chooseMove(Board board, int player) {
        return minimax(board, player, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true).move;
    }

    private static class ScoredMove {
        Move move;
        int score;

        ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }
    }

    private ScoredMove minimax(Board board, int player, int depth, int alpha, int beta, boolean maximizingPlayer) {
        List<Move> validMoves = board.getValidMoves(player);

        if (depth == 0 || validMoves.isEmpty()) {
            return new ScoredMove(null, evaluate(board, player));
        }

        Move bestMove = null;

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : validMoves) {
                Board clonedBoard = new Board(board);
                clonedBoard.playMove(move.row, move.col, player);
                int eval = minimax(clonedBoard, Constants.getOpponent(player), depth - 1, alpha, beta, false).score;
                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = move;
                }
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break; // Beta cut-off
            }
            return new ScoredMove(bestMove, maxEval);
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : validMoves) {
                Board clonedBoard = new Board(board);
                clonedBoard.playMove(move.row, move.col, player);
                int eval = minimax(clonedBoard, Constants.getOpponent(player), depth - 1, alpha, beta, true).score;
                if (eval < minEval) {
                    minEval = eval;
                    bestMove = move;
                }
                beta = Math.min(beta, eval);
                if (beta <= alpha) break; // Alpha cut-off
            }
            return new ScoredMove(bestMove, minEval);
        }
    }

    private int evaluate(Board board, int player) {
        int[][] weights = {
                {120, -20,  20,  5,  5, 20, -20, 120},
                {-20, -40, -5, -5, -5, -5, -40, -20},
                {20,  -5,  15,  3,  3, 15,  -5,  20},
                {5,   -5,   3,  3,  3,  3,  -5,   5},
                {5,   -5,   3,  3,  3,  3,  -5,   5},
                {20,  -5,  15,  3,  3, 15,  -5,  20},
                {-20, -40, -5, -5, -5, -5, -40, -20},
                {120, -20, 20,  5,  5, 20, -20, 120}
        };

        int score = 0;
        int[][] boardData = board.getBoard();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (boardData[i][j] == player) {
                    score += weights[i][j];
                } else if (boardData[i][j] == Constants.getOpponent(player)) {
                    score -= weights[i][j];
                }
            }
        }

        // Thêm sđiểm dựa trên số quân
        int playerCount = board.countPieces(player);
        int opponentCount = board.countPieces(Constants.getOpponent(player));
        score += (playerCount - opponentCount) * 10;

        return score;
    }
}
