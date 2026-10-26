import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        Random random = new Random();
		        Scanner input = new Scanner(System.in);

		        int player1Score = 0;
		        int player2Score = 0;
		        int userPlayer = random.nextInt(2) + 1;
		        int currentPlayer = 1;

		        System.out.println("You will be player " + userPlayer + ".");
		        System.out.println("Enter nothing to roll; enter anything to hold.");

		        while (player1Score < 100 && player2Score < 100) {
		            System.out.println("Player 1 score: " + player1Score);
		            System.out.println("Player 2 score: " + player2Score);
		            System.out.println("It is player " + currentPlayer + "'s turn.");

		            int turnTotal = 0;
		            int currentScore;

		            if (currentPlayer == 1) {
		                currentScore = player1Score;
		            } else {
		                currentScore = player2Score;
		            }

		            while (true) {
		                int roll = random.nextInt(6) + 1;
		                System.out.println("Roll: " + roll);

		                if (roll == 1) {
		                    turnTotal = 0;
		                    break;
		                }

		                turnTotal += roll;

		                if (currentPlayer == userPlayer) {
		                    System.out.print("Turn total: " + turnTotal
		                            + "\tRoll/Hold? ");
		                    String choice = input.nextLine();

		                    if (!choice.isEmpty()) {
		                        break;
		                    }
		                } else {
		                    if (turnTotal >= 20
		                            || currentScore + turnTotal >= 100) {
		                        break;
		                    }
		                }
		            }

		            System.out.println("Turn total: " + turnTotal);

		            int newScore = currentScore + turnTotal;

		            if (currentPlayer == 1) {
		                player1Score = newScore;
		            } else {
		                player2Score = newScore;
		            }

		            System.out.println("New score: " + newScore);

		            currentPlayer = 3 - currentPlayer;
		        }

		        input.close();
		    }
		}



