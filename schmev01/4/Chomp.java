import java.util.Scanner;

/**
 * Chomp
 */
public class Chomp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print(
            // Long instructions
            "CHOMP\nIn the game of Chomp, players bite a rectangular cookie in turn.\nThe player chomping the last bite (0,0) loses.\nEach chomp, a row and column, bites off all below and to the right.\n"
        );
        System.out.print("Rows? ");
        int rowCount = in.nextInt();
        System.out.print("Columns? ");
        int columnCount = in.nextInt();
        ChompGame chompGame = new ChompGame(rowCount, columnCount);
        int chompRow;
        int chompColumn;
        boolean validChomp = false;
        while (!chompGame.isGameOver()) {
            while (!validChomp) {
                System.out.printf("\n%s\n", chompGame);
                System.out.printf(
                    "Player %d chomp? ",
                    chompGame.getCurrentPlayer()
                );
                chompRow = in.nextInt();
                chompColumn = in.nextInt();
                validChomp = chompGame.chompAt(chompRow, chompColumn);
                if (validChomp) break;
                System.out.println("That is not a legal chomp position.");
            }
            validChomp = false;
        }
        in.close();
        System.out.printf("\nPlayer %d wins!\n", chompGame.getCurrentPlayer());
    }
}
