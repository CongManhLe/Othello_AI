package ai;

import logic.Board;
import logic.Move;
import utils.Constants;

import java.util.*;

public class MinimaxAI implements AIPlayer {
    private static final int DEFAULT_DEPTH = 4;

    @Override
    public Move chooseMove(Board board, int player) {
        return chooseMove(board, player, DEFAULT_DEPTH);
    }

    public static Move chooseMove(Board board, int player, int depth) {
        return minimax(board, player, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true).move;
    }

    private static Result minimax(Board board, int player, int depth, int alpha, int beta, boolean maximizing) {
        List<Move> moves = board.getValidMoves(player);
        if (depth == 0 || moves.isEmpty()) {
            int score = evaluate(board, player);
            return new Result(null, score);
        }

        Move bestMove = null;
        int bestScore = maximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move move : moves) {
            Board copy = copyBoard(board);
            copy.playMove(move.row, move.col, player);
            int score = minimax(copy, Constants.getOpponent(player), depth - 1, alpha, beta, !maximizing).score;

            if (maximizing) {
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
                alpha = Math.max(alpha, bestScore);
            } else {
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
                beta = Math.min(beta, bestScore);
            }

            if (beta <= alpha) break; // pruning
        }

        return new Result(bestMove, bestScore);
    }

    private static int evaluate(Board board, int player) {
        int opponent = Constants.getOpponent(player);
        int score = 0;

        // 1. Mobility
        int myMoves = board.getValidMoves(player).size();
        int oppMoves = board.getValidMoves(opponent).size();
        score += 100 * (myMoves - oppMoves);

        // 2. Piece difference
        int myPieces = board.countPieces(player);
        int oppPieces = board.countPieces(opponent);
        score += 10 * (myPieces - oppPieces);

        // 3. Corners
        int[][] b = board.getBoard();
        int myCorners = 0, oppCorners = 0;
        int[][] corners = {{0, 0}, {0, 7}, {7, 0}, {7, 7}};
        for (int[] c : corners) {
            if (b[c[0]][c[1]] == player) myCorners++;
            else if (b[c[0]][c[1]] == opponent) oppCorners++;
        }
        score += 1000 * (myCorners - oppCorners);

        // 4. Danger zones (near corner)
        int[][] dangerZones = {
                {0, 1}, {1, 0}, {1, 1},
                {0, 6}, {1, 6}, {1, 7},
                {6, 0}, {6, 1}, {7, 1},
                {6, 6}, {6, 7}, {7, 6}
        };
        for (int[] d : dangerZones) {
            if (b[d[0]][d[1]] == player) score -= 200;
            else if (b[d[0]][d[1]] == opponent) score += 200;
        }

        return score;
    }

    private static Board copyBoard(Board original) {
        Board copy = new Board();
        int[][] orig = original.getBoard();
        int[][] dest = copy.getBoard();
        for (int i = 0; i < 8; i++)
            System.arraycopy(orig[i], 0, dest[i], 0, 8);
        return copy;
    }

    private static class Result {
        Move move;
        int score;

        Result(Move move, int score) {
            this.move = move;
            this.score = score;
        }
    }
}
