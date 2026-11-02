import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");

		System.out.print("Rows? ");
		int rows = input.nextInt();

		System.out.print("Columns? ");
		int cols = input.nextInt();

		ChompGame game = new ChompGame(rows, cols);

		while (!game.isGameOver()) {
			System.out.println();
			System.out.print(game.toString());
			System.out.println();

			System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");

			int row = input.nextInt();
			int col = input.nextInt();

			if (!game.chompAt(row, col)) {
				System.out.println("That is not a legal chomp position.");
			}
		}

		System.out.println();
		System.out.println("Player " + game.getCurrentPlayer() + " wins!");

		input.close();
	}
}