public class MostUsedStrategy implements Strategy {
    public int determineMove(int[] playerMoveCount, int lastMove) {
        int mostIndex = 0;
        for (int i = 1; i < playerMoveCount.length; i++) {
            if (playerMoveCount[i] > playerMoveCount[mostIndex]) {
                mostIndex = i;
            }
        }
        return (mostIndex + 1) % 3; // choose move that beats the most used
    }

    public String getName() {
        return "Most Used";
    }
}
