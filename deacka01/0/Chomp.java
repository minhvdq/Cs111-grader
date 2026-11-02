import java.util.Scanner;

import javax.sql.RowSet;

public class Chomp {

	public static void main(String[] args) {
		System.out.println("CHOMP\n"
				+ "In the game of Chomp, players bite a rectangular cookie in turn.\n"
				+ "The player chomping the last bite (0,0) loses.\n"
				+ "Each chomp, a row and column, bites off all below and to the right.");

		// make board
		Scanner in = new Scanner(System.in);
		System.out.print("Rows? ");
		int rows = in.nextInt();
		System.out.print("Columns? ");
		int cols = in.nextInt();

		ChompGame board = new ChompGame(rows, cols); // make board

		System.out.println();
		System.out.println(board.toString());

		// - prompt for row and column
		System.out.printf("Player %d chomp? ", board.getCurrentPlayer());
		in.nextLine();
		String move1 = in.nextLine();

		if (move1.contains("-")) {
			System.out.println("That is not a legal chomp position.");
		}
		else {
		
		int rowMove1 = Integer.parseInt(move1.substring(0, 1));
		int colMove1 = Integer.parseInt(move1.substring(2));
		

		// check if legal, print message if not
		if (board.isLegalChomp(rowMove1, colMove1)) {
			board.chompAt(rowMove1, colMove1);
		}
		else 
			System.out.println("That is not a legal chomp position.");

		}
		
		

		while (!board.isGameOver()) {// While game not over
			// - print game board w/ toString proceeded/followed by syso
			System.out.println();
			System.out.println(board.toString());

			// - prompt for row and column
			System.out.printf("Player %d chomp? ", board.getCurrentPlayer());
			String move = in.nextLine();
			
			if (move.contains("-")) {
				System.out.println("That is not a legal chomp position.");
			}
			else {
			int rowMove = Integer.parseInt(move.substring(0, 1));
			int colMove = Integer.parseInt(move.substring(2));

			// check if legal, print message if not
			if (board.isLegalChomp(rowMove, colMove)) {
				board.chompAt(rowMove, colMove);
			}
			else 
				System.out.println("That is not a legal chomp position.");
			}
	
		}
	

				
		//		 When game over
		//		 - blank line
		//		 - message	
		System.out.println();
		System.out.printf("Player %d wins!", board.currentPlayer);
		
		in.close();
	}
}
	

