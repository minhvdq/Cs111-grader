import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {

		int chosenPlayer = choosePlayer();
		System.out.println("You will be player " + chosenPlayer + ". ");
		System.out.println("Enter nothing to roll; enter anything to hold. ");

		int player = chosenPlayer, p1score = 0, p2score = 0;

		Scanner in = new Scanner(System.in);

		while (p1score < 100 && p2score < 100) {
			System.out.println("Player 1 score: " + p1score);
			System.out.println("Player 2 score: " + p2score);
			System.out.println("It is player " + player + "'s turn. ");

			int turnScore = 0;

			if (player != chosenPlayer) {
				turnScore = computerPlays();

			}
			else {
				while (true) {
					System.out.print("Turn total: " + turnScore + "	Roll/Hold? (Enter) ");
					String choice = in.nextLine();

					if (choice.isEmpty()) {
						int roll = rolling();

						if (roll == 1) {
							turnScore = 0;
							break;
						}

						turnScore = turnScore + roll;
					}
					else {
						break;
					}
				}
			}

			if (player == 1) {
				p1score = p1score + turnScore;
			}
			else {
				p2score = p2score + turnScore;
			}

			System.out.println("Turn total: " + turnScore);
			System.out.println("New score: " + (player == 1 ? p1score : p2score));

			player = (player == 1) ? 2 : 1;

		}

		System.out.println("Player 1 score: " + p1score);
		System.out.println("Player 2 score: " + p2score);

		if (p1score >= 100) {
			System.out.println("Player 1 wins!");
		} 
		else {
			System.out.println("Player 2 wins!");
		}
	}
	
	private static int computerPlays() {
		int turnScore = 0;
		while (true) {
			int roll = rolling();

			if (roll == 1) {
				turnScore = 0;
				break;
			}

			turnScore = turnScore + roll;
			System.out.println("Turn score: " + turnScore);

			if (turnScore >= 20) {
				break;
			}
		}
		return turnScore;
	}

	public static int rolling() {

		int roll = 0, turnScore = 0;
		Random random = new Random();

		roll = random.nextInt(6) + 1;
		System.out.println("Roll: " + roll);
		return turnScore = turnScore + roll;
	}

	public static int choosePlayer() {
		Random random = new Random();
		int player = random.nextInt(2) + 1;
		return player;
	}

}
