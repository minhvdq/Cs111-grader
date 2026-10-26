import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int min = 1;
		int max = 6;
		int numValues= max - min + 1;
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


			int turnTotal = 0;
			int roll;
			
			int currentScore;
			
			if (currentPlayer == 1) {
				currentScore = player1Score;
			} else {
				currentScore = player2Score;
			}
		
		do {
			roll = (int)(Math.random() * numValues) + min;
			System.out.println("Roll: " + roll);
			
			if (roll == 1) {
				turnTotal = 0;
			}
			else {
				turnTotal += roll;
			}
				if (currentPlayer == userPlayer) {
					System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
					String choice = in.nextLine();
				if (!choice.isEmpty()) {
					break;
				}
			}
		}
		while (roll != 1 && turnTotal < 20 && currentScore + turnTotal < 100);
		System.out.println("Turn total: " + turnTotal);
		
		if (currentPlayer == 1) {
			player1Score += turnTotal;
			System.out.println("New score: " + player1Score);
		} else {
			player2Score += turnTotal;
			System.out.println("New score: " + player2Score);
		}
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
		
	
		}
		in.close();
		
	

	}

}