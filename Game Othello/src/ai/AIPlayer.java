
package ai;

import logic.Board;
import logic.Move;

public interface AIPlayer {
    Move chooseMove(Board board, int player);
}
