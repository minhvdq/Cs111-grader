import java.util.Random;
import java.util.Scanner;

public class PigGame {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        int score1 = 0;
        int score2 = 0;
        int player = 1;

        int user = rand.nextInt(2) + 1;

        System.out.println("You will be player " + user + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");

        while (score1 < 100 && score2 < 100) {

            System.out.println("Player 1 score: " + score1);
            System.out.println("Player 2 score: " + score2);
            System.out.println("It is player " + player + "'s turn.");

            int score = 0;

            if (player == 1) {
                score = score1;
            } else {
                score = score2;
            }

            int turnTotal = 0;
            boolean hold = false;

            while (!hold) {
                int roll = rand.nextInt(6) + 1;
                System.out.println("Roll: " + roll);

                if (roll == 1) {
                    turnTotal = 0;
                    break;
                }

                turnTotal = turnTotal + roll;

                if (player == user) {
                    System.out.print("Turn total: " + turnTotal
                            + "\tRoll/Hold? ");

                    String answer = input.nextLine();

                    if (!answer.equals("")) {
                        hold = true;
                    }

                } else {
                    if (turnTotal >= 20 ||
                            score + turnTotal >= 100) {
                        hold = true;
                    }
                }
            }

            score = turnTotal + score;

            System.out.println("Turn total: " + turnTotal);
            System.out.println("New score: " + score);

            if (player == 1) {
                score1 = score;
            } else {
                score2 = score;
            }

            if (score >= 100) {
                System.out.println("Player " + player + " wins!");
            }

            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }

        input.close();
    }
}
