import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int player1Score = 0;
        int player2Score = 0;
        
        int userPlayerNumber = (int) (Math.random() * 2) + 1;
        System.out.println("You will be player " + userPlayerNumber + ".");
        System.out.println("Enter nothing to roll; enter anything to hold.");
        int currentPlayer = 1;

        while (player1Score < 100 && player2Score < 100) {
            System.out.println("Player 1 score: " + player1Score);
            System.out.println("Player 2 score: " + player2Score);
            System.out.println("It is player " + currentPlayer + "'s turn.");

            int currentScore = (currentPlayer == 1) ? player1Score : player2Score;
            int turnTotal = 0;
            boolean isUserTurn = (currentPlayer == userPlayerNumber);
            boolean userHeld = false; 

            while (true) {
                int roll = (int) (Math.random() * 6) + 1;
                System.out.println("Roll: " + roll);

                if (roll == 1) {
                    turnTotal = 0;
                    break;
                } else {
                    turnTotal += roll;

                    if (isUserTurn) {
                        System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
                        String input = in.nextLine();

                        if (input.length() > 0 || (currentScore + turnTotal) >= 100) {
                            System.out.println("Turn total: " + turnTotal);
                            userHeld = true; 
                            break;
                        }
                    } else {
                        if ((currentScore + turnTotal) >= 100) {
                            break;
                        }
                        if (turnTotal >= 20) {
                            break;
                        }
                    }
                }
            }
            if (currentPlayer == 1) {
                player1Score += turnTotal;
                if (!userHeld) {
                    System.out.println("Turn total: " + turnTotal);
                }
                System.out.println("New score: " + player1Score);
                currentPlayer = 2;

            } else {
                player2Score += turnTotal;
                if (!userHeld) {
                    System.out.println("Turn total: " + turnTotal);
                }
                System.out.println("New score: " + player2Score);
                currentPlayer = 1;
            }
        }
        in.close();
    }
}
