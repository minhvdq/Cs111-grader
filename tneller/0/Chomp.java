import java.util.Scanner;

/**
 * Chomp - A class for text-based play of the game of <a href="https://en.wikipedia.org/wiki/Chomp">Chomp</a> as specified through the given game transcript.  Use <code>ChompGame</code> for modeling the game state itself.  All input/output should take place in the main method of this class.  
 * First, print instructions with the following lines: {"CHOMP", "In the game of Chomp, players bite a rectangular cookie in turn.", "The player chomping the last bite (0,0) loses.", "Each chomp, a row and column, bites off all below and to the right."}.
 * Then prompt the user for the number of rows and columns with prompts "Rows? " and "Columns? ".  Read these in as integers presumed to be in the range 1-10.
 * Create a <code>ChompGame</code> object using this information.
 * Then, while the game is not over, do the following:
 * Print the game board produced by the object toString() and preceded and followed with blank lines.
 * Prompt the user for a row and column for chomping with "Player # chomp? ", where "#" is the games current player number.
 * Attempt the chomp move, making the move and changing the player if the chomp move is legal, and just printing the message "That is not a legal chomp position." otherwise.
 * After the game is over, print a blank line and the message "Player # wins!", where "#" is the winning player.
 * No input/output should take place in class <code>Chomp</code>.  Whenever possible, use the computation of the Chomp class to perform the modeling (i.e. play, logic) of the game.
 */
public class Chomp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Print instructions
		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");

		// Get dimensions and create board
		System.out.print("Rows? ");
		int rows = in.nextInt();
		System.out.print("Columns? ");
		int cols = in.nextInt();
		ChompGame game = new ChompGame(rows, cols);
		while (!game.isGameOver()) { // while game isn't over
			System.out.println();
			System.out.println(game);
			// Input play
			System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");
			int chompRow = in.nextInt();
			int chompCol = in.nextInt();
			// Attempt play.  If illegal, print error message.
			if (!game.chompAt(chompRow, chompCol))
				System.out.println("That is not a legal chomp position.");
		}	
		// Print winner
		System.out.println("\nPlayer " + game.getCurrentPlayer() + " wins!");
		in.close();
	}
}
