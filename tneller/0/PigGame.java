import java.util.Random;
import java.util.Scanner;


public class PigGame {
	public static void main(String[] args) {
		final int GOAL = 100;
		final int HOLD_AT = 20;
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		int[] scores = new int[3];
		int player = 1;
		int humanPlayer = random.nextInt(2) + 1;
		System.out.println("You will be player " + humanPlayer + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");
		while (scores[1] < GOAL && scores[2] < GOAL) {
			System.out.println("Player 1 score: " + scores[1]);
			System.out.println("Player 2 score: " + scores[2]);
			System.out.println("It is player " + player + "'s turn.");
			int turnTotal = 0;
			boolean pigRolled = false;
			boolean hold = false;
			while (!pigRolled && !hold) {
				int roll = random.nextInt(6) + 1;
				System.out.println("Roll: " + roll);
				pigRolled = (roll == 1);
				if (pigRolled)
					turnTotal = 0;
				else {
					turnTotal += roll;
					if (player == humanPlayer) {
						System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
						hold = !in.nextLine().equals("");
					}
					else
						hold = !(turnTotal < HOLD_AT 
								&& scores[player] + turnTotal < GOAL);
				}
			}
			scores[player] += turnTotal;
			System.out.println("Turn total: " + turnTotal);
			System.out.println("New score: " + scores[player]);
			player = (player == 1) ? 2 : 1;
		}
		in.close();
	}
}
