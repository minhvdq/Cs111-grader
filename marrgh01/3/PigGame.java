import java.util.Random;
import java.util.Scanner;

public class PigGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("You will be player 2.\n"
				+ "Enter nothing to roll; enter anything to hold.");
		Random random = new Random();
		int Score1 = 0;
		int Score2 = 0 ;
		int i = 0;
		int Score =0;
			while ( Score1 < 100 && Score2<100) {
				int TurnTotal = 0;
				Score =0;
				System.out.println("Player 1 score: "+ Score1);
				System.out.println("Player 2 score: "+ Score2);
				if (i==0) {
					Score = Score1;
					System.out.println("It is player 1's turn.");
					while ( TurnTotal < 20 ) {
						int roll = random.nextInt(6) + 1;
						TurnTotal += roll ;
						System.out.println("Roll: " + roll);
						if (roll == 1) {
							TurnTotal = 0;	
							break;
						} 
						if ((Score + TurnTotal)>=100) {
							break;
						}
						
				}
					i++;
					Score1 +=TurnTotal;
					
				}
				else {
					Score = Score2;
					System.out.println("It is player 2's turn.");
					while ( TurnTotal < 20 ) {
						int roll = random.nextInt(6) + 1;
						TurnTotal += roll ;
						System.out.println("Roll: " + roll);
						if (roll == 1) {
							TurnTotal = 0;	
							break;
						} 
						System.out.print( "Turn total: "+TurnTotal);

						if ((Score + TurnTotal)>=100) {
							break;
						}
						System.out.print(" Roll/Hold? ");
						String play = in.nextLine();
						if (!play.equals("")) {
							
							break;
						}

					}
					i--;
					Score2 +=TurnTotal;
				}


				Score +=TurnTotal;
			System.out.println( "Turn total: "+ TurnTotal);
			System.out.println( "New score: "+ Score);


			}
in.close();
			
	}
}


