import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGameGUI {
    private JFrame frame;
    private JButton[] buttons = new JButton[20];
    private int[] board = new int[20];
    private int currentNumber;
    private JLabel messageLabel;
    private Random random = new Random();
    private JButton startButton;

public NumberGameGUI() {
    frame = new JFrame("Number Slot Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 900);
    frame.setLayout(new BorderLayout());

    JPanel boardPanel = new JPanel(new GridLayout(20, 2, 5, 5));
    boardPanel.setPreferredSize(new Dimension(200, 800));

    JScrollPane scrollPane = new JScrollPane(boardPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    messageLabel = new JLabel("Order the given number's from smallest to greatest. Press 'Start' to begin", SwingConstants.CENTER);

    for (int i = 0; i < 20; i++) {
        JLabel indexLabel = new JLabel((i + 1) + ")", SwingConstants.RIGHT);
        indexLabel.setPreferredSize(new Dimension(30, 30));

        buttons[i] = new JButton("____");
        buttons[i].setPreferredSize(new Dimension(80, 30));
        int index = i;
        buttons[i].addActionListener(e -> handleButtonClick(index));
        buttons[i].setEnabled(false);

        boardPanel.add(indexLabel);
        boardPanel.add(buttons[i]);
    }

    startButton = new JButton("Start");
    startButton.setFocusable(false);
    startButton.setMargin(new Insets(5, 10, 5, 10));
    startButton.addActionListener(e -> startGame());

    frame.add(messageLabel, BorderLayout.NORTH);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(startButton, BorderLayout.SOUTH);

    frame.setVisible(true);
}

    private void startGame() {
        startButton.setEnabled(false);
        for (int i = 0; i < 20; i++) {
            board[i] = 0;
            buttons[i].setText("____");
            buttons[i].setEnabled(true);
        }
        currentNumber = generateNumber();
        messageLabel.setText("Choose a slot for: " + currentNumber);
    }

    private void handleButtonClick(int index) {
        if (board[index] == 0) {
            board[index] = currentNumber;
            buttons[index].setText(String.valueOf(currentNumber));

            if (checkLoss()) {
                board[index]=0;
                buttons[index].setText("X "+String.valueOf(currentNumber));
                messageLabel.setText("You lost with a score of: "+score()+".");
                disableButtons();
                startButton.setEnabled(true);
                return;
            }

            currentNumber = generateNumber();
            messageLabel.setText("Choose a slot for: " + currentNumber);
        }
        if (winCondition()) {
            messageLabel.setText("You won, impressive!");
            disableButtons();
            startButton.setEnabled(true);
        }
    }

    private int score(){
        int output = 0;
        for (int i = 0; i < 20; i++) {
            if (board[i]!=0){
                output++;
            }
        }
        return output;
    }
    private int generateNumber() {
        return random.nextInt(1000) + 1;
    }

    private boolean checkLoss() {
        int min = 0;
        for (int i =0;i<20;i++) {
            if (board[i] != 0 && board[i] < min) {
                return true;
            }else if (board[i]!=0){
                min=board[i];
            }
        }
        return false;
    }
    private boolean winCondition(){
        for (int i = 0; i < 20; i++) {
            if (board[i]==0){
                return false;
            }
        }
        return true;
    }
    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGameGUI::new);
    }
}