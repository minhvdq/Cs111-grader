import java.util.Scanner;

public class Chomp {
	
	//i implemented problems 2 and 3 in problem 2, worked on both at the same time or tried too.
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The Player champing the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
		
		System.out.print("Rows? ");
		int rows = in.nextInt();

		System.out.print("Columns? ");
		int cols = in.nextInt();

		ChompGame game = new ChompGame(rows, cols);

		while (!game.isGameOver()) {

			System.out.println();
			System.out.print(game.toString());
			System.out.println();

			System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");
			int row = in.nextInt();
			int col = in.nextInt();

			boolean success = game.chompAt(row, col);
			if (!success) {
				System.out.println("That is not a legal chomp posistion.");
			}
		}
		System.out.println();
		System.out.print("Player " + game.getCurrentPlayer() + " wins! ");

		in.close();
	}

}
