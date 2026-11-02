import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
		System.out.print("Rows? ");
		int row = in.nextInt();
		System.out.print("Columns? ");
		int col = in.nextInt();
		ChompGame Chomp = new ChompGame(row, col);
		System.out.println();

		do {
			System.out.println(Chomp.toString());
			System.out.print("Player " + Chomp.getCurrentPlayer() + " chomp? ");
			row = in.nextInt();
			col = in.nextInt();
			if (Chomp.isLegalChomp(row, col))
				Chomp.chompAt(row, col);
			else {
				System.out.println("That is not a legal chomp position.");
				System.out.println();
			}
		}
		while(!Chomp.isGameOver());
		System.out.println();
		System.out.println("Player " + Chomp.getCurrentPlayer() + " wins!");
		
		in.close();
	}
}
