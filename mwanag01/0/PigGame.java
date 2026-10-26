import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		int player1 = 0;
		int player2 = 0;
		int currentPlayer = 1;
		int playerPosition = random.nextInt(2) + 1;
		System.out.printf("You will be player %d\n", playerPosition);
		System.out.println("Enter nothing to roll; enter anything to hold.");
		while (player1 < 100 && player2 < 100) {
			System.out.printf("Player 1 score: %d%n", player1);
		    System.out.printf("Player 2 score: %d%n", player2);
		    System.out.printf("It is player %d's turn.%n", currentPlayer);
		    int newScore = 0;
		    if (currentPlayer == 1) {
		    	newScore = player1;
		    } else {
		    	newScore = player2;
		    }
			int roll = 0;
			int turnTotal = 0;
			boolean holding = false;
			while (roll != 1 && !holding) {
				roll = random.nextInt(6) + 1;
				System.out.printf("Roll: %d\n", roll);
				if (roll != 1) {
					turnTotal = turnTotal + roll;
					if (currentPlayer == playerPosition) {
						System.out.printf("Turn total: %d\tRoll/Hold? ", turnTotal);
						String choice = in.nextLine();
						if (choice.length() > 0) {
							holding = true;
						}
					} else {
						if (turnTotal >= 20 || newScore + turnTotal >= 100) {
							holding = true;
						}
					}
				} else {
					turnTotal = 0;
				}
			}
			newScore = newScore + turnTotal;
			System.out.printf("Turn total: %d\n", turnTotal);
			System.out.printf("New score: %d\n", newScore);
			if (currentPlayer == 1) {
				player1 = newScore;
				currentPlayer = 2;
			} else {
		    player2 = newScore;
		    currentPlayer = 1;
		    }
		}
		in.close();
	}

}
