import java.util.Random;
import java.util.Scanner;

public class PigGame {
	public static int playerTurn(int score) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int rollTotal = 0;
		int roll = 0;
		
			roll = 0;
			rollTotal = 0;
		while (roll != 1) {
			roll = rand.nextInt(6)+1;
			System.out.println("Roll: "+roll);
			rollTotal += roll;
			
			if (roll == 1) {
				break;
			}
			System.out.print("Turn total: "+rollTotal);
			System.out.print("\tRoll/Hold? (Enter) ");
			String playerC = in.nextLine();
			
			if (playerC != "") {
				break;
			}
		}
		if (roll == 1) {
				rollTotal = 0; } else {}
		System.out.println("Turn total: "+rollTotal);
		score = rollTotal+score;
		System.out.println("New score: "+(score));
		
		return score;
		
		
		
		
		
	}
	
	
	
	public static int pigTurn(int score) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int rollTotal = 0;
		int roll = 0;
		
			roll = 0;
			rollTotal = 0;
		while (roll != 1 && rollTotal < 20 && (score+rollTotal) < 100) {
			roll = rand.nextInt(6)+1;
			System.out.println("Roll: "+roll);
			rollTotal += roll;
		}
		if (roll == 1) {
				rollTotal = 0; } else {}
		
		System.out.println("Turn total: "+rollTotal);
		score = rollTotal+score;
		System.out.println("New score: "+(score));
		
		return score;
	}
	public static void main(String[] args) {
		Random rand = new Random();
		int p1s = 0;
		int p2s = 0;
		boolean p1turn = true;
		int playerNumb = rand.nextInt(1)+1;
		System.out.println("You will be player "+playerNumb+".");
		System.out.println("Enter nothing to roll; enter anything to hold.");
		
		while (p1s < 100 && p2s < 100) {
			System.out.println("Player 1 score: "+p1s);
			System.out.println("Player 2 score: "+p2s);
			
			if (p1turn == true) {
				System.out.println("It is player 1's turn.");
				
				if (playerNumb == 2) {
				p1s = pigTurn(p1s);
				} else {
					playerTurn(p1s);
				}
				
				
				p1turn = false;
			} else {
				System.out.println("It is player 2's turn.");
				
				if (playerNumb == 1) {
					p1s = pigTurn(p2s);
					} else {
						playerTurn(p2s);
					}
				
				p1turn = true;
			}
			
			
		}

	}

}
