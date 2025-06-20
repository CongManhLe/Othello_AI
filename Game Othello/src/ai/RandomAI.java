package ai;

import logic.Board;
import logic.Move;

import java.util.List;
import java.util.Random;

public class RandomAI implements AIPlayer {
    private final Random random = new Random();

    @Override
    public Move chooseMove(Board board, int player) {
        List<Move> moves = board.getValidMoves(player);
        if (moves.isEmpty()) return null;
        return moves.get(random.nextInt(moves.size()));
    }
}
