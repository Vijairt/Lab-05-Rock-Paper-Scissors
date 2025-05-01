import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random rand = new Random();

    public int determineMove(int[] playerMoveCount, int lastMove) {
        return rand.nextInt(3);
    }

    public String getName() {
        return "Random";
    }
}
