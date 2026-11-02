import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		System.out.printf("CHOMP");
		System.out.println("In the game of Chomps, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
		System.out.println();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Rows? ");
		int rows = in.nextInt();
		System.out.print("Columns? ");
		int cols = in.nextInt();
		
		ChompGame game = new ChompGame(rows, cols);
		
		while (!game.isGameOver()) {
			System.out.println();
			System.out.println(game.toString());
			
			System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");
			int r = in.nextInt();
			int c = in.nextInt();
			
			if (game.isLegalChomp(r, c))
				game.chompAt(r, c);
			else
				System.out.println("That is not a legal chomp position.");
		}
		
		System.out.println();
		System.out.println("Player " + game.getCurrentPlayer() + " wins!");
		
		in.close();
	}

}
