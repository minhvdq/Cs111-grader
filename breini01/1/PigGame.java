import java.util.Random;
import java.util.Scanner;

public class PigGame {
	public static int Gambling(int score) {
		Random on = new Random();
		int turn = 0;
		int roll = 0;

		for (turn = 0; turn < 20; turn += roll) {
			if (turn + score >= 100) {
				break;
			}
			roll = 1 + on.nextInt(6);
			System.out.println("Roll: " + roll);
			if (roll == 1) {
				turn = 0;
				break;
			}
		}
		System.out.println("Turn total: " + turn);
		score += turn;
		System.out.println("New score: " + score);
		return score;
	}
	public static int player(int score) {
		Random on = new Random();
		Scanner in = new Scanner(System.in);
		int turn = 0;
		int roll = 0;
		String holly = "";
		for (turn = 0; holly.isEmpty(); turn += roll) {
			roll = 1 + on.nextInt(6);
			System.out.println("Roll: " + roll);
			if (roll == 1) {
				turn = 0;
				System.out.println("Turn total: " + (turn + roll));
				break;
			}
			System.out.print("Turn total: " + (turn + roll) + "\tRoll/Hold?");
			holly = in.nextLine();
			}
		score += turn;
		if (roll != 1)
		System.out.println("Turn total: " + turn);
		System.out.println("New score: " + score);
		return score;

	}


	public static void main(String[] args) {
		Random random = new Random();
		int playerturn = 1;
		int score1 = 0;
		int score2 = 0;
		int pturn = 1 + random.nextInt(2);
		System.out.println("You will be player " + pturn);
		System.out.println("Enter nothing to roll; enter anything to hold.");
		do {
			System.out.println("Player 1 score: " + score1);
			System.out.println("Player 2 score: " + score2);


			if (playerturn == 1) {
				System.out.println("It is player 1's turn.");
				if (pturn == 1)
					score1 = player(score1);
				else
				score1 = Gambling(score1);
				playerturn = 2;
			}
			else {
				System.out.println("It is player 2's turn.");
				if (pturn == 2)
					score2 = player(score2);
				else
				score2 = Gambling(score2);
				playerturn = 1;
			}
		}
		while (score1 < 100 & score2 < 100);
		if (score1 >= 100) {
			System.out.println("New score: " + score1);
			System.out.println("Player 1 wins!");
		}
		else {
			System.out.println("New score: " + score2);
			System.out.println("Player 2 wins!");
		}
	}
}
