import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		int user =0;
		int comp =0;
		int turn = 1;
		int score =0;
		
		Random ran = new Random();
		int playNum = ran.nextInt(2)+1;
		
		System.out.printf("You will be player %d.\n", playNum);
		System.out.println("Enter nothing to roll; enter anything to hold.");
		
		Random rand = new Random();
		Scanner in = new Scanner(System.in);

		
		while (user < 100 && comp < 100 ) {
			System.out.printf("Player %d score: %d\n", playNum, user);
			if (playNum == 1) {
				System.out.printf("Player 2 score: %d\n", comp);
			}else {
				System.out.printf("Player 1 score: %d\n", comp);
			}
			

			if (turn == playNum) {
				
				System.out.printf("It is player %d's turn.\n", turn);
				score = user;
					int total=0;
					int roll = 0;
					String play="";
					
					while (roll != 1 && play.equals("") && (total + score) < 100 ) {
						roll = rand.nextInt(6)+1;
						System.out.printf("Roll: %d\n", roll);
						
						if (roll == 1) {
							total =0;
							}
						else {
							total += roll;
							System.out.printf("Turn total: %d Roll/Hold? ", total);
							play = in.nextLine();
						}

					}
					if (turn == 1) {
					    turn = 2;
					} else {
					    turn = 1;
					}
					if (roll != 1) {
					    System.out.printf("Turn total: %d\n", total);
					}

					
					score +=total;
					user = score;
					System.out.printf("New score: %d\n", user);
			} 
			else {
				score =comp;
				System.out.printf("It is player %d's turn.\n", turn);
			
					int total=0;
					int roll = 0;
					
					while (roll != 1 && total < 20 && (total+score) < 100) {
						roll = rand.nextInt(6)+1;
						System.out.printf("Roll: %d\n", roll);
						if (roll == 1) {
							total =0;
						}else {
							total += roll;
						}
					} 
					if (turn == 1) {
					    turn = 2;
					} else {
					    turn = 1;
					}
					
					System.out.printf("Turn total: %d\n", total);
					score +=total;
					comp =score;
					System.out.printf("New score: %d\n", comp);
				}
				
			} 

	}

}
