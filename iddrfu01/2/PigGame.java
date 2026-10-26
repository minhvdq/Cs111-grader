import java.util.Random;
import java.util.Scanner;

public class PigGame {
		public static void main(String[] args) {
			Random random = new Random();
			Scanner in = new Scanner(System.in);
			int userPlayer = random.nextInt(2) + 1;

		int player1Score = 0;
		int player2Score = 0;
		int currentPlayer = 1;

		System.out.println("You will be player " + userPlayer + ".");
		System.out.println("Enter nothing to roll; entrer anything to hold.");

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

			while (turnTotal < 20 && score + turnTotal < 100)  {
				int roll = random.nextInt(6) + 1;
				System.out.println("Roll: " + roll);

				if (roll == 1) {
					turnTotal = 0;
					break;
				}

					turnTotal += roll;
				if (currentPlayer == userPlayer) {
		                    System.out.print( "Turn total: " + turnTotal + "\tRoll/Hold? ");
		                    String myChoice = in.nextLine();
		                    if (myChoice.length() > 0) {
		                    	break;
		                    }

		                    if (score + turnTotal >= 100) {
		                    	break;
		                    }
						}

		                    else {
		                   	if (turnTotal >= 20) {
		                    		break;
		                    	}
		                   	
		                    	if (score + turnTotal >= 100) {
		                    		break;
		                    	}
		                    }

			score += turnTotal;
			if (currentPlayer == 1) {
				player1Score = score;
			}
			else {
				player2Score = score;
			}

			 System.out.println("Turn total: " + turnTotal);
			 System.out.println("New score: " + score);

			if (currentPlayer == 1) {
				currentPlayer = 2;
			} else {
				currentPlayer = 1;
			}

			}

		}

		}

	}
