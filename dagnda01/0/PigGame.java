import java.util.Random;
import java.util.Scanner;

public class PigGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int humanPlayer = random.nextInt(2) + 1;
        System.out.println("You will be player " + humanPlayer + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");

        int player1Score = 0;
        int player2Score = 0;
        int currentPlayer = 1;

        while (player1Score < 100 && player2Score < 100) {
            System.out.println("Player 1 score: " + player1Score);
            System.out.println("Player 2 score: " + player2Score);
            System.out.println("It is player " + currentPlayer + "'s turn.");

            int currentScore = (currentPlayer == 1) ? player1Score : player2Score;
            int turnTotal = 0;
            boolean turnOver = false;

            while (!turnOver) {
                int roll = random.nextInt(6) + 1;
                System.out.println("Roll: " + roll);

                if (roll == 1) {
                    turnTotal = 0;
                    turnOver = true;
                } else {
                    turnTotal += roll;

                    if (currentPlayer == humanPlayer) {
                        System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
                        String input = scanner.nextLine();
                        if (input.length() > 0) {
                            currentScore += turnTotal;
                            turnOver = true;
                        }
                    } else {
                        if (turnTotal >= 20 || (currentScore + turnTotal) >= 100) {
                            currentScore += turnTotal;
                            turnOver = true;
                        }
                    }
                }
            }

            System.out.println("Turn total: " + turnTotal);
            System.out.println("New score: " + currentScore);

            if (currentPlayer == 1) {
                player1Score = currentScore;
            } else {
                player2Score = currentScore;
            }

            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }

        scanner.close();
    }
}