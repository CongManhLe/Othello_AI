package utils;

public class Constants {
    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int BOARD_SIZE = 8;

    public static final int AI_EASY = 0;
    public static final int AI_HARD = 1;
    public static final int AI_SUPER_HARD = 2;

    public static int getOpponent(int player) {
        return (player == BLACK) ? WHITE : BLACK;
    }
}
