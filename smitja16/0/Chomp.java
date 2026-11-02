import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		
		// opening prompts and text
		Scanner in = new Scanner(System.in);
		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
		System.out.print("Rows? ");
		int rows = in.nextInt();
		System.out.print("Columns? ");
		int cols = in.nextInt();
		ChompGame board = new ChompGame(rows, cols);
		
		// game loop
		while (!board.isGameOver()) {
			System.out.println("\n" + board);
			System.out.printf("Player %d chomp? ", board.getCurrentPlayer());
			if (!board.chompAt(in.nextInt(), in.nextInt())) {
				System.out.println("That is not a legal chomp position.");
			}
		}
		System.out.printf("\nPlayer %d wins!", board.getCurrentPlayer());
		
		in.close();
	}

}
