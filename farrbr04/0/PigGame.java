import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random random = new Random();

		int player1Score = 0;
		int player2Score = 0;
		int currentPlayer = 1;
		int userPlayer = random.nextInt(2) + 1;
		System.out.println("You will be player " + userPlayer + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");

		while (player1Score < 100 && player2Score < 100) {

			int score;

			if (currentPlayer == 1) {
				System.out.println("Player 1 score: " + player1Score);
				System.out.println("Player 2 score: " + player2Score);
				System.out.println("It is player 1's turn.");
				score = player1Score;
			}
			else {
				System.out.println("Player 1 score: " + player1Score);
				System.out.println("Player 2 score: " + player2Score);
				System.out.println("It is player 2's turn.");
				score = player2Score;
			}

			int total = 0;
			boolean pig = false;
			boolean isHuman = (currentPlayer == userPlayer);

			while (!pig) {

				int roll = random.nextInt(6) + 1;
				System.out.println("Roll: " + roll);

				if (roll == 1) {
					total = 0;
					System.out.println("Turn total: " + total);
					pig = true;

				} else {
					total += roll;

					if (isHuman) {
						System.out.print("Turn total: " + total + "\tRoll/Hold? ");
						String input = in.nextLine().trim();

						if (!input.isEmpty()) {
							pig = true;
						}
					} else {
							if (total >= 20 || score + total >= 100) {
								System.out.println("Turn total: " + total);
								pig = true;
							}
						}
				}
			}
					score += total;

					if (currentPlayer == 1) {
						player1Score = score;
					}
					else {
						player2Score = score;
					}

					System.out.println("New score: " + score);

					if (currentPlayer == 1) {
						currentPlayer = 2;
					}
					else {
						currentPlayer = 1;
					}
		}

		in.close();
	}
}

