public class LastUsedStrategy implements Strategy {
    public int determineMove(int[] playerMoveCount, int lastMove) {
        if (lastMove == -1) return (int) (Math.random() * 3);
        return (lastMove + 1) % 3; // pick the move that beats the last move
    }

    public String getName() {
        return "Last Used";
    }
}
