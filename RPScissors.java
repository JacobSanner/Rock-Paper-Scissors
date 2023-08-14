import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RPScissors extends JFrame {
    private int userScore = 0;
    private int computerScore = 0;

    private JLabel scoreLabel;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton resetButton;

    public RPScissors() {
        setTitle("Rock Scissors Paper Game"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setSize(1200, 800); // Set the size of the window to 1200x800
        setLayout(new FlowLayout()); // Set the layout manager for the components

        scoreLabel = new JLabel("User: 0 | Computer: 0"); // Create a label to display the score
        rockButton = new JButton("Rock"); // button for Rock
        paperButton = new JButton("Paper"); // button for Paper
        scissorsButton = new JButton("Scissors"); // button for Scissors
        resetButton = new JButton("Reset"); // button to reset the game

        rockButton.setBackground(Color.RED); // Setting the background color of the Rock button to red
        paperButton.setBackground(Color.GREEN); // Setting the background color of the Paper button to green
        scissorsButton.setBackground(Color.BLUE); // Setting the background color of the Scissors button to blue

        rockButton.addActionListener(new ButtonListener("Rock")); // Add an ActionListener to the Rock button
        paperButton.addActionListener(new ButtonListener("Paper")); // Add an ActionListener to the Paper button
        scissorsButton.addActionListener(new ButtonListener("Scissors")); // Add an ActionListener to the Scissors
                                                                          // button
        resetButton.addActionListener(new ResetButtonListener()); // Add an ActionListener to the Reset button

        add(scoreLabel); // Add the score label to the window
        add(rockButton); // Add the Rock button to the window
        add(paperButton); // Add the Paper button to the window
        add(scissorsButton); // Add the Scissors button to the window
        add(resetButton); // Add the Reset button to the window

        setVisible(true); // Make the window visible
    }

    private class ButtonListener implements ActionListener {
        private String userChoice;

        public ButtonListener(String choice) {
            userChoice = choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] choices = { "Rock", "Paper", "Scissors" }; // Array of possible choices
            String computerChoice = choices[new Random().nextInt(choices.length)]; // Generate a random computer choice

            String result = determineWinner(userChoice, computerChoice); // Determine the winner
            updateScore(result); // Update the score label

            JOptionPane.showMessageDialog(null, "Computer chose: " + computerChoice + "\n" + result); // Show the result
        }
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            userScore++;
            return "You win!";
        } else {
            computerScore++;
            return "Computer wins!";
        }
    }

    private void updateScore(String result) {
        scoreLabel.setText("User: " + userScore + " | Computer: " + computerScore); // Update the score label text
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userScore = 0;
            computerScore = 0;
            updateScore(""); // Reset the scores and update the score label
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RPScissors()); // Create and run the game within the Swing thread
    }
}
