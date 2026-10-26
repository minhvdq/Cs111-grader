import java.util.Random;
import java.util.Scanner;

public class PigGame {

	private static final Random random = new Random();
	private static final Scanner in = new Scanner(System.in);
	
	public static int playerTurn(int score, boolean isUser) {
		int total = 0;
		boolean turnOver = false;
		
		while (!turnOver) {
			int roll = random.nextInt(6) + 1;
			System.out.println("Roll: " + roll);
			
			if (roll == 1) {
				total = 0;
				turnOver = true;
			}
			
			else {
				total += roll;
				if (isUser) { // If the user is playing...
					System.out.print("Turn total: " + total + "\tRoll/Hold? "); // Ask if they want to roll or hold.
					String input = in.nextLine();
					if (!input.isEmpty()) {
						turnOver = true;
					}
				}
				
				else { // If it isn't the user...
					if (total >= 20 || score + total >= 100)
					turnOver = true;
				}
			}
		}
		
		System.out.println("Turn total: " + total);
		System.out.println("New score: " + (score + total));
		return total;
	}

	public static void main(String[] args) {
		int[] scores = new int[2];
		int currentPlayer = 0;
		
		// Randomly choose if user is Player 1 (0) or PLayer 2 (0)
		int userPlayerIndex = random.nextInt(2);
		System.out.println("You will be player " + (userPlayerIndex + 1) + ".");
		System.out.println("Enter nothing to roll; enter anything to hold.");
		
		// score[0] is player 1, score[1] is player 2
		while (scores[0] < 100 && scores[1] < 100) { // while both scores are below 100
			System.out.println("Player 1 score: " + scores[0]);
			System.out.println("Player 2 score: " + scores[1]);
			System.out.println("It is player " + (currentPlayer + 1) + "'s turn.");
			
			boolean isUser = (currentPlayer == userPlayerIndex);
			scores[currentPlayer] += playerTurn(scores[currentPlayer], isUser);
			currentPlayer = 1 - currentPlayer;
		}

	}

}
