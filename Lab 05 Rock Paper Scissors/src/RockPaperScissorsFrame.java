import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private final JTextField playerWinsField = new JTextField("0", 5);
    private final JTextField computerWinsField = new JTextField("0", 5);
    private final JTextField tiesField = new JTextField("0", 5);
    private final JTextArea resultArea = new JTextArea(10, 30);

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private final Random random = new Random();

    private final Strategy[] strategies = {
            new RandomStrategy(),
            new LeastUsedStrategy(),
            new MostUsedStrategy(),
            new LastUsedStrategy(),
            new CheatStrategy()
    };
    private Strategy currentStrategy;
    private final int[] playerMoveCount = new int[3]; // Rock=0, Paper=1, Scissors=2
    private int lastPlayerMove = -1;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createButtonPanel(), BorderLayout.NORTH);
        add(createStatsPanel(), BorderLayout.CENTER);
        add(createResultPanel(), BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));

        JButton rockBtn = new JButton("Rock");
        JButton paperBtn = new JButton("Paper");
        JButton scissorsBtn = new JButton("Scissors");
        JButton quitBtn = new JButton("Quit");

        rockBtn.addActionListener(e -> play(0));
        paperBtn.addActionListener(e -> play(1));
        scissorsBtn.addActionListener(e -> play(2));
        quitBtn.addActionListener((ActionEvent e) -> System.exit(0));

        panel.add(rockBtn);
        panel.add(paperBtn);
        panel.add(scissorsBtn);
        panel.add(quitBtn);

        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Stats"));

        playerWinsField.setEditable(false);
        computerWinsField.setEditable(false);
        tiesField.setEditable(false);

        panel.add(new JLabel("Player Wins:"));
        panel.add(playerWinsField);
        panel.add(new JLabel("Computer Wins:"));
        panel.add(computerWinsField);
        panel.add(new JLabel("Ties:"));
        panel.add(tiesField);

        return panel;
    }

    private JScrollPane createResultPanel() {
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game Results"));
        return scrollPane;
    }

    private void play(int playerMove) {
        currentStrategy = strategies[random.nextInt(strategies.length)];
        int computerMove = currentStrategy.determineMove(playerMoveCount, lastPlayerMove);

        playerMoveCount[playerMove]++;
        lastPlayerMove = playerMove;

        String result;
        String[] moves = {"Rock", "Paper", "Scissors"};

        if (playerMove == computerMove) {
            ties++;
            result = "Tie: both chose " + moves[playerMove];
        } else if ((playerMove == 0 && computerMove == 2) ||
                (playerMove == 1 && computerMove == 0) ||
                (playerMove == 2 && computerMove == 1)) {
            playerWins++;
            result = moves[playerMove] + " beats " + moves[computerMove] + " (Player wins)";
        } else {
            computerWins++;
            result = moves[computerMove] + " beats " + moves[playerMove] + " (Computer wins)";
        }

        result += " [" + currentStrategy.getName() + "]";
        resultArea.append(result + "\n");

        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
    }
}
