import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int score1 = 0;
		int score2 =0;
		int currentPlayer = 1;
		int roll2 = random.nextInt(2)+1;
		System.out.println("You will be player " + roll2 + ".");
		System.out.println("Enter nothing to roll; enter anything to hold" + ".");
		
		while (score1 < 100 && score2 < 100) {
			int turnTotal = 0;
			System.out.println("Player 1 score: " + score1);
			System.out.println("Player 2 score: " + score2);
			System.out.println("It is player " + currentPlayer + "'s turn.");
			int score;
			if (currentPlayer == 1) {
			    score = score1;
			} else {
			    score = score2;
			}
			
			while ((currentPlayer == roll2) || (turnTotal < 20 && score + turnTotal < 100)) {
				int roll = random.nextInt(6) + 1;
			    System.out.println("Roll: " + roll);
			    if (roll == 1) {
			        turnTotal = 0;
			        break;
			    }
			    turnTotal = turnTotal + roll;
				 			    
				    
				
			    if (currentPlayer == roll2){
				    System.out.print("Turn total: " + turnTotal + "\tRoll/Hold? ");
				    String answeruser = input.nextLine();

				    if (!answeruser.isEmpty()) {
				        break;
				    }
				}
				
			}	
			score += turnTotal;
			
			if (currentPlayer == 1) {
			    score1 = score;
			} else {
			    score2 = score;
			}
			System.out.println("Turn total: " + turnTotal);
			System.out.println("New score: " + score);

			if (currentPlayer == 1) {
			    currentPlayer = 2;
			} else {
			    currentPlayer = 1;
			}
		}

	}
}	