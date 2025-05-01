public class LeastUsedStrategy implements Strategy {
    public int determineMove(int[] playerMoveCount, int lastMove) {
        int leastIndex = 0;
        for (int i = 1; i < playerMoveCount.length; i++) {
            if (playerMoveCount[i] < playerMoveCount[leastIndex]) {
                leastIndex = i;
            }
        }
        return (leastIndex + 1) % 3; // choose move that beats the least used
    }

    public String getName() {
        return "Least Used";
    }
}
