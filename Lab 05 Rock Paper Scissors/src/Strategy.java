public interface Strategy {
    int determineMove(int[] playerMoveCount, int lastMove);
    String getName();
}
