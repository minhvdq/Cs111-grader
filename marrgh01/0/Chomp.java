import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("CHOMP\n"
				+ "In the game of Chomp, players bite a rectangular cookie in turn.\n"
				+ "The player chomping the last bite (0,0) loses.\n"
				+ "Each chomp, a row and column, bites off all below and to the right.");
		System.out.print("Rows? ");
		int Row = in.nextInt();
		System.out.print("Columns? ");
		int Col = in.nextInt();
		ChompGame game = new ChompGame(Row, Col);
		System.out.println();
		System.out.println(game);
		 while (true) {

			 System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");
			 int RowPos =in.nextInt();
			 int ColPos = in.nextInt();

			 if (game.isLegalChomp(RowPos, ColPos) == true) {
				 game.chompAt(RowPos, ColPos);
				 if(game.isGameOver()!= true) {
					 System.out.println(game); 
				 }
				 
			 }
			 else {
				 System.out.println("That is not a legal chomp position.");
				 System.out.println();
				 System.out.println(game);
			 }
			 if (game.isGameOver()== true) {
				 System.out.println();
				 System.out.println("Player " + game.getCurrentPlayer() + " wins!");
				 break;
			 }
		 }

		 in.close();
	}

}
