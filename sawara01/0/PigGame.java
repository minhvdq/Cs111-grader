import java.util.Random;
import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int[] scores = {0, 0};
        int userPlayer = rand.nextInt(2) + 1;
        int currentPlayer = 1;

        System.out.println("You will be player " + userPlayer + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");

        while (scores[0] < 100 && scores[1] < 100) {
            System.out.println("Player 1 score: " + scores[0]);
            System.out.println("Player 2 score: " + scores[1]);
            System.out.println("It is player " + currentPlayer + "'s turn.");

            int turnTotal = 0;
            boolean turnOver = false;
            boolean isUser = (currentPlayer == userPlayer);

            while (!turnOver) {
                int roll = rand.nextInt(6) + 1;
                System.out.println("Roll: " + roll);

                if (roll == 1) {
                    turnTotal = 0;
                    turnOver = true;
                } else {
                    turnTotal += roll;

                    if (isUser) {
                        System.out.print(
                            "Turn total: " + turnTotal + "\tRoll/Hold? "
                        );

                        String choice = input.nextLine();

                        if (!choice.isEmpty()) {
                            turnOver = true;
                        }
                    } else {
                        if (turnTotal >= 20 ||
                            scores[currentPlayer - 1] + turnTotal >= 100) {
                            turnOver = true;
                        }
                    }
                }
            }

            System.out.println("Turn total: " + turnTotal);

            scores[currentPlayer - 1] += turnTotal;

            System.out.println(
                "New score: " + scores[currentPlayer - 1]
            );

            currentPlayer = 3 - currentPlayer;
        }

        input.close();
    }
}
