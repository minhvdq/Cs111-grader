import java.util.Random;
import java.util.Scanner;

public class PigGame {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        int player1Total = 0;
        int player2Total = 0;
        int currentPlayerTurn = 1;
        int humanPlayer = rand.nextInt(2) + 1;
        System.out.printf("You will be player %d.\n", humanPlayer);
        System.out.println("Enter nothing to roll; enter anything to hold.");
        while (player1Total < 100 && player2Total < 100) {
            System.out.printf("Player 1 score: %d\n", player1Total);
            System.out.printf("Player 2 score: %d\n", player2Total);
            System.out.printf("It is player %d's turn.\n", currentPlayerTurn);
            int roll;
            int playerTurnTotal = 0;
            int currentPlayerTotal =
                currentPlayerTurn == 1 ? player1Total : player2Total;
            while (true) {
                roll = rand.nextInt(6) + 1;
                System.out.printf("Roll: %d\n", roll);
                if (roll == 1) {
                    playerTurnTotal = 0;
                    break;
                }
                playerTurnTotal += roll;
                if (playerTurnTotal >= 20 && currentPlayerTurn != humanPlayer) {
                    break;
                } else if (currentPlayerTotal + playerTurnTotal >= 100 && currentPlayerTurn != humanPlayer) {
                    break;
                }
                if (currentPlayerTurn == humanPlayer) {
                    System.out.printf("Turn total: %d\tRoll/Hold? ", playerTurnTotal);
                    if (in.nextLine().length() != 0) {
                        break;
                    }
                }
            }
            System.out.printf("Turn total: %d\n", playerTurnTotal);
            if (currentPlayerTurn == 1) {
                player1Total += playerTurnTotal;
                System.out.printf("New score: %d\n", player1Total);
            } else {
                player2Total += playerTurnTotal;
                System.out.printf("New score: %d\n", player2Total);
            }
            currentPlayerTurn = currentPlayerTurn == 1 ? 2 : 1;
        }
        in.close();
    }
}
