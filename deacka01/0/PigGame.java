import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		// You will be player # - randomly selected
		// "Enter nothing to roll; enter anything to hold/"
		Scanner in = new Scanner(System.in);
		Random random = new Random();
		int humanPlayer = random.nextInt(2); // random either 0 or 1
		System.out.printf("You will be player %d.\n", humanPlayer == 0 ? 1 : 2);
		System.out.println("Enter nothing to roll; enter anything to hold.");
		
		// PigComputerGame code 
		int score = 0;
		int currentPlayer = 0;
		int score1 = 0;
		int score2 = 0;
		score = score1;
		
		// If Players turn
//		System.out.print("Roll/Hold? ");
//		String turnChoice = in.nextLine();
//		if (turnChoice.contentEquals("\n")) { // hold
//			
//		}
//		else { // roll
//			
//		}
		
		
	

		// loop turn


		while (score <= 100) {	// loop until 100 points reached
			// Pig turn 1 code to get rolls and turn total	
			int turnTotal = 0;
			System.out.printf("Player 1 score: %d\n", score1);
			System.out.printf("Player 2 score: %d\n", score2);
			System.out.printf("It is player %d's turn.\n", (currentPlayer == 0 ? 1 : 2));
			score = currentPlayer == 0 ? score1 : score2;
			
			if (currentPlayer == humanPlayer) {
				String turnChoice = "\n";
				do {
					int roll = random.nextInt(6) + 1; // generate roll
					System.out.printf("Roll: %d\n", roll);
					if (roll > 1) { // if pig roll
						turnTotal += roll;
						System.out.print("Roll/Hold? ");				
						turnChoice = in.nextLine();
					}	
					else {
						turnTotal = 0;
						break;
					}	
				} while (turnChoice.contentEquals(""));
			}
			else { // computer turn
				while (turnTotal < 20) { // when to keep rolling - until >= 20
					int roll = random.nextInt(6) + 1; // generate roll
					System.out.printf("Roll: %d\n", roll);
					if (roll > 1) { // if pig roll
						turnTotal += roll;
						if ((turnTotal + score) >= 100) {
							break;
						}
					}
					else {
						turnTotal = 0;
						break;
					}
				}
			}
					
			
			score += turnTotal;
			System.out.printf("Turn total: %d\n", turnTotal); // print score

			
			// New score with new score added to turn total
			System.out.printf("New score: %d\n", score);
			
			if (currentPlayer == 0) {
				score1 = score;
				score = score2;
			}	
			else { 
				score2 = score;
				score = score1;
			}
		
			currentPlayer = currentPlayer == 0 ? 1 : 0;
			
			if (score1 >= 100 || score2 >= 100)
				break;
		
		}
		
		
		
		
		
		in.close();
		
	}
	
}
