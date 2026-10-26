import java.util.Random;
import java.util.Scanner;

public class PigGame {
	static Scanner in = new Scanner(System.in);
	static Random rand = new Random();

	public static int playerTurn(int score, boolean isPlayer) {
		int rollTotal = 0;
		int roll = 0;
		while (true) {
			roll = rand.nextInt(6) + 1;
			System.out.println("Roll: " + roll);
			if (roll == 1) {
				rollTotal = 0;
				break;
			}
			rollTotal += roll;
			if (isPlayer) {
				System.out.print("Turn total: " + rollTotal + "\tRoll/Hold? ");
				String playerC = in.nextLine();
				if (!playerC.isEmpty()) {
					break;
				}
			} else if (rollTotal >= 20 || score + rollTotal >= 100) {
				break;
			}
		}
		System.out.println("Turn total: " + rollTotal);
		score += rollTotal;
		System.out.println("New score: " + score);
		return score;
	}

	public static void main(String[] args) {
		int p1s = 0;
		int p2s = 0;
		boolean p1turn = true;
		int playerNumb = rand.nextInt(2) + 1;
		System.out.println("You will be player " + playerNumb + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");

		while (p1s < 100 && p2s < 100) {
			System.out.println("Player 1 score: " + p1s);
			System.out.println("Player 2 score: " + p2s);

			if (p1turn) {
				System.out.println("It is player 1's turn.");
				p1s = playerTurn(p1s, playerNumb == 1);
				p1turn = false;
			} else {
				System.out.println("It is player 2's turn.");
				p2s = playerTurn(p2s, playerNumb == 2);
				p1turn = true;
			}
		}
	}
}