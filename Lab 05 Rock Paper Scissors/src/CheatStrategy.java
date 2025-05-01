public class CheatStrategy implements Strategy {
    public int determineMove(int[] playerMoveCount, int lastMove) {
        return Math.random() < 0.1 ? (lastMove + 1) % 3 : (int) (Math.random() * 3);
    }

    public String getName() {
        return "Cheat";
    }
}
