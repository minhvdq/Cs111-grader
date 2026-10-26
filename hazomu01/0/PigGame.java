
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int playr1 = 0;
		int playr2 = 0;
		int curPlayr = 1;
		int userPlayr = (int)(Math.random() * 2) + 1;

		System.out.println("You will be player " + userPlayr + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");

		while (playr1 < 100 && playr2 < 100) {

			System.out.println("Player 1 score: " + playr1);
			System.out.println("Player 2 score: " + playr2);
			System.out.println("It is player " + curPlayr + "'s turn.");
			int score;

			if (curPlayr == 1) {
				score = playr1;
			} else {
				score = playr2;
			}

			int tot = 0;
			boolean over = false;

			while (!over) {

				int roll = (int)(Math.random() * 6) + 1;

				System.out.println("Roll: " + roll);

				if (roll == 1) {
					tot = 0;
					break;
				}

				tot += roll;

				if (curPlayr == userPlayr) {

					System.out.print("Turn total: " + tot + "\tRoll/Hold? ");

					String ans = input.nextLine();

					if (ans.length() > 0) {
						over = true;
					}

				} else {

					if (tot >= 20 || score + tot >= 100) {
						over = true;
					}
				}
			}

			score += tot;

			System.out.println("Turn total: " + tot);
			System.out.println("New score: " + score);

			if (curPlayr == 1) {
				playr1 = score;
				curPlayr = 2;
			} else {
				playr2 = score;
				curPlayr = 1;
			}
		}

		input.close();
	}
}
