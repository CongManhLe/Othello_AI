package logic;

import ai.AIPlayer;
import ai.RandomAI;
import ai.MinimaxAI;
import ai.SuperHardAI;
import utils.Constants;

public class Game {
    private Board board;
    private int currentPlayer;
    private boolean vsAI;
    private int aiLevel;

    private AIPlayer ai; // Giao diện chung cho AI

    public Game(boolean vsAI, int aiLevel) {
        this.vsAI = vsAI;
        this.aiLevel = aiLevel;
        board = new Board();
        currentPlayer = Constants.BLACK;

        if (vsAI) {
            switch (aiLevel) {
                case Constants.AI_EASY:
                    ai = new RandomAI();
                    break;
                case Constants.AI_HARD:
                    ai = new MinimaxAI();
                    break;
                case Constants.AI_SUPER_HARD:
                    ai = new SuperHardAI();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid AI level: " + aiLevel);
            }
        }
    }

    public boolean playMove(int row, int col) {
        if (board.playMove(row, col, currentPlayer)) {
            switchTurn();
            return true;
        }
        return false;
    }

    public void playAIMove() {
        if (!vsAI || ai == null) return;

        Move move = ai.chooseMove(board, currentPlayer);
        if (move != null) {
            board.playMove(move.row, move.col, currentPlayer);
            switchTurn();
        } else {
            // Nếu AI không có nước đi, chuyển lượt cho người chơi nếu họ có nước
            if (!board.getValidMoves(getOpponent(currentPlayer)).isEmpty()) {
                switchTurn();
            }
        }
    }

    private void switchTurn() {
        int opponent = getOpponent(currentPlayer);
        if (!board.getValidMoves(opponent).isEmpty()) {
            currentPlayer = opponent;
        } else if (board.getValidMoves(currentPlayer).isEmpty()) {
            // Nếu cả hai đều không có nước đi thì không làm gì,
            // sẽ được xử lý ở isGameOver()
        }
        // Nếu đối thủ không có nước, thì giữ nguyên lượt
    }

    public boolean isGameOver() {
        return board.getValidMoves(Constants.BLACK).isEmpty() &&
                board.getValidMoves(Constants.WHITE).isEmpty();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    private int getOpponent(int player) {
        return (player == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;
    }
}
