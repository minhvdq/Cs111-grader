import java.util.Scanner;

public class Chomp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		
		System.out.println("CHOMP");
		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
		System.out.println("The player chomping the last bite (0,0) loses.");
		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
		
		System.out.print("Rows? ");
		int row = in.nextInt();
		System.out.print("Columns? \n");
		int col = in.nextInt();

		ChompGame game =new ChompGame(row,col);

		
		while (!game.isGameOver()) {
			System.out.println(game.toString());
			
			System.out.print("Player 1 chomp? \n");
			row = in.nextInt();
			col = in.nextInt();
			
			
			boolean valid = game.chompAt(row, col);

			if (!valid) {
                System.out.println("That is not a legal chomp position.");
            }
			
			
			
			
			
		}
			
			

		
		
		
		
		in.close();
	}

}
