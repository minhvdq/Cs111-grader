import java.util.Scanner;

public class Chomp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CHOMP");
        System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
        System.out.println("The player chomping the last bite (0,0) loses.");
        System.out.println("Each chomp, a row and column, bites off all below and to the right.");

        System.out.print("Rows? ");
        int rows = scanner.nextInt();
        System.out.print("Columns? ");
        int cols = scanner.nextInt();

        ChompGame game = new ChompGame(rows, cols);

        while (!game.isGameOver()) {
            System.out.println("\n" + game.toString());
            System.out.print("Player " + game.getCurrentPlayer() + " chomp? ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            boolean success = game.chompAt(row, col);
            if (!success) {
                System.out.println("That is not a legal chomp position.");
            }
        }

        System.out.println();
        System.out.println("Player " + game.getCurrentPlayer() + " wins!");

        scanner.close();
    }
}