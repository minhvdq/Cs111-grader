import java.util.Scanner;

public class PigGame {

	// generates a dice roll (1-6)
	public static int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
	
	// runs through a full turn of pig, stopping at turnTotal 20 or at goal
	// returns an int to be usable for multiple turns
	public static int pigTurn(int startScore, boolean isComputer, Scanner in) {
		boolean isTurn = true;
		int turnTotal = 0;
		if (isComputer) {
			while (isTurn) {
				if (turnTotal < 20 && (turnTotal + startScore < 100)) {
					int roll = randomInt(1, 6);
					System.out.println("Roll: " + roll);
					if (roll == 1) {
						turnTotal = 0;
						break;
					} else {
						turnTotal += roll;
					}
				} else {
					isTurn = false;
				}
			}
		} else {
			while (isTurn) {
				int roll = randomInt(1, 6);
				System.out.println("Roll: " + roll);
				if (roll == 1) {
					turnTotal = 0;
					break;
				} else {
					turnTotal += roll;
					System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
					String playerResponse = in.nextLine();
					if (playerResponse.length() != 0) {
						isTurn = false;
					}
				}
			}
		}
		System.out.println("Turn total: " + turnTotal);
		System.out.println("New score: " + (turnTotal + startScore));
		return turnTotal + startScore;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int score1 = 0;
		int score2 = 0;
		int turn = 1;
		boolean isOneComputer = Math.round(Math.random()) == 0;
		System.out.println("You will be player " + (isOneComputer ? "2." : "1."));
		System.out.println("Enter nothing to roll; enter anything to hold.");
		// pigTurn is returning an int, allowing score to be updated
		while (score1 < 100 && score2 < 100) {
			System.out.println("Player 1 score: " + score1);
			System.out.println("Player 2 score: " + score2);
			System.out.println("It is player " + (turn % 2 == 0 ? 2 : 1) + "'s turn.");
			if (turn % 2 == 1) {
				score1 = pigTurn(score1, isOneComputer, in);
			} else {
				score2 = pigTurn(score2, !isOneComputer, in);
			}
			turn++;
		}
		in.close();
	}
}
