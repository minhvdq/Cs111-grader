
import java.util.Scanner;

public class PigGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int player1 = 0;
        int player2 = 0;
        int currentPlayer = 1;

        int userPlayer = (int)(Math.random() * 2) + 1;

        System.out.println("You will be player " + userPlayer + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");

        while (player1 < 100 && player2 < 100) {

            System.out.println("Player 1 score: " + player1);
            System.out.println("Player 2 score: " + player2);
            System.out.println("It is player " + currentPlayer + "'s turn.");

            int score;

            if (currentPlayer == 1) {
                score = player1;
            } else {
                score = player2;
            }

            int turnTotal = 0;
            boolean turnOver = false;

        while (!turnOver) {

            int roll = (int)(Math.random() * 6) + 1;

                System.out.println("Roll: " + roll);

          if (roll == 1) {
               turnTotal = 0;
                    break;
                }

                turnTotal += roll;

         if (currentPlayer == userPlayer) {

        System.out.print("Turn total: " + turnTotal
                    + "\tRoll/Hold? ");

         String answer = input.nextLine();

        if (answer.length() > 0) {
            turnOver = true;
                    }

                } else {

        if (turnTotal >= 20 || score + turnTotal >= 100) {
              turnOver = true;
                    }
                }
            }

            score += turnTotal;

       System.out.println("Turn total: " + turnTotal);
        System.out.println("New score: " + score);

      if (currentPlayer == 1) {
        player1 = score;
        currentPlayer = 2;
     } else {
        player2 = score;
        currentPlayer = 1;
           }
       }

        input.close();
    }
}
