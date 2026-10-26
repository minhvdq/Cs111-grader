import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		Random random = new Random();
		Scanner in = new Scanner(System.in);

		int player1Score = 0;
		int player2Score = 0;
		int currentPlayer = 1;

		int userPlayer = random.nextInt(2) + 1;

		System.out.println("You will be player " + userPlayer + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");

		while(player1Score < 100 && player2Score < 100) {

			int score;

			System.out.println("Player 1 score: " + player1Score);
			System.out.println("Player 2 score: " + player2Score);
			System.out.println("It is player " + currentPlayer + "'s turn.");

			if ( currentPlayer == 1) {
				score = player1Score;
			}
			else {
				score = player2Score;
			}
			int roll = 0;
			int turnTotal = 0;
			boolean hold = false;

			while (turnTotal < 20 && roll != 1 && score + turnTotal < 100 && !hold) {
				roll = random.nextInt(6) + 1;
				System.out.println("Roll: " + roll);

				if(roll == 1) {
					turnTotal = 0;
				}
				else {
					turnTotal = turnTotal + roll;

					if(currentPlayer == userPlayer) {
						System.out.println("Turn total: " + turnTotal + "\tRoll/Hold? ");

						String choice = in.nextLine();

						if(choice.length() > 0) {
							hold = true;
						}

					}
				}
			}
			score = score + turnTotal;
			System.out.println("Turn total: " + turnTotal);
			System.out.println("New score: " + score);

			if(currentPlayer == 1) {
				player1Score = score;
			}
			else {
				player2Score = score;
			}
			if(currentPlayer == 1) {
				currentPlayer = 2;
			}
			else {
				currentPlayer = 1;
			}
		}
		in.close();
	}
}
