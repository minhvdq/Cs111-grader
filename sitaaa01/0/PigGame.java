
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int player1Score = 0;
	int player2Score = 0;
	int currentPlayer = 1;
int userPlayer = (int) (Math.random() * 2) + 1;
System.out.println("You will be player " + userPlayer + ".");
System.out.println("Enter nothing to roll; enter anything to hold.");

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
	boolean turnOver = false;

		while (!turnOver) {

				int roll = (int) (Math.random() * 6) + 1;
		System.out.println("Roll: " + roll);

		if (roll == 1) {
					turnTotal = 0;
			turnOver = true;
				} else {
					turnTotal += roll;

		if (currentPlayer == userPlayer) {
						System.out.print("Turn total: " + turnTotal
								+ "\tRoll/Hold? ");

						String choice = input.nextLine();

		if (!choice.isEmpty()) {
							turnOver = true;
						}
					} else if (turnTotal >= 20
							|| score + turnTotal >= 100) {
						turnOver = true;
					}
			}
			}

		System.out.println("Turn total: " + turnTotal);

			score += turnTotal;

		System.out.println("New score: " + score);

		if (currentPlayer == 1) {
				player1Score = score;
			} else {
				player2Score = score;
			}

	if (currentPlayer == 1) {
				currentPlayer = 2;
			} else {
				currentPlayer = 1;
			}
		}

		input.close();
	}
}
