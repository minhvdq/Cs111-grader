
import java.util.Random;
import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        int player1Score = 0;
        int player2Score = 0;
        int currentPlayer = 1;

        // Randomly choose which player the user will be.
        int userPlayer = random.nextInt(2) + 1;

        System.out.println("You will be player " + userPlayer + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");

        while (player1Score < 100 && player2Score < 100) {
            System.out.println("Player 1 score: " + player1Score);
            System.out.println("Player 2 score: " + player2Score);
            System.out.println("It is player " + currentPlayer + "'s turn.");

            int score;
            if (currentPlayer == 1) {
                score = player1Score;
            } else {
                score = player2Score;
            }

            int turnTotal = 0;

            while (true) {
                // Computer automatically holds at 20 or when it can win.
                if (currentPlayer != userPlayer
                        && (turnTotal >= 20 || score + turnTotal >= 100)) {
                    break;
                }

                // Ask the user whether to roll or hold.
                if (currentPlayer == userPlayer && turnTotal > 0) {
                    System.out.print("Turn total: " + turnTotal
                            + "\tRoll/Hold? ");

                    String choice = input.nextLine();

                    if (!choice.isEmpty()) {
                        break;
                    }
                }

                int roll = random.nextInt(6) + 1;
                System.out.println("Roll: " + roll);

                if (roll == 1) {
                    turnTotal = 0;
                    break;
                }

                turnTotal += roll;

                // Computer stops after reaching 20 or the goal.
                if (currentPlayer != userPlayer
                        && (turnTotal >= 20 || score + turnTotal >= 100)) {
                    break;
                }
            }

            System.out.println("Turn total: " + turnTotal);

            score += turnTotal;
            System.out.println("New score: " + score);

            if (currentPlayer == 1) {
                player1Score = score;
                currentPlayer = 2;
            } else {
                player2Score = score;
                currentPlayer = 1;
            }
        }

        input.close();
    }
}
