import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("CHOMP\nIn the game of Chomp, players bite a rectangular cookie in turn.\nThe player chomping the last bite (0,0) loses.\nEach chomp, a row and column, bites off all below and to the right.");

		System.out.print("Rows? ");
		int rows = in.nextInt();
		System.out.print("Columns? ");
		int cols = in.nextInt();

		System.out.println();

		ChompGame bang = new ChompGame(rows, cols);
		boolean gameOn = true;
		System.out.println(bang.toString());
		System.out.println();

		while (!bang.isGameOver()) {

			System.out.print("Player " + bang.getCurrentPlayer() + " chomp? ");
			int row = in.nextInt();
			int col = in.nextInt();

			System.out.println();

			if (bang.isLegalChomp(row, col)) {
				bang.chompAt(row, col);
				if (bang.isGameOver() != true)
					System.out.println(bang);
			} else {
				System.out.println("That is not a legal chomp position.");
			}

			System.out.println();
		}

		System.out.println("Player " + bang.getCurrentPlayer() + " wins!");
	}
}