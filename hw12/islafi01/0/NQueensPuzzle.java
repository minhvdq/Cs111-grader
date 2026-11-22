
import java.util.Scanner;

public class NQueensPuzzle {

    private int numQueens;
    private int[] queenCol;

    public NQueensPuzzle(int numQueens) {
        this.numQueens = numQueens;
        queenCol = new int[numQueens];
        solve(0);
    }

    private boolean solve(int row) {
        if (row == numQueens) {
            return true;
        }

        for (int col = 0; col < numQueens; col++) {
            boolean conflict = false;

            for (int previousRow = 0; previousRow < row; previousRow++) {
                if (queenCol[previousRow] == col ||
                    Math.abs(row - previousRow) ==
                    Math.abs(col - queenCol[previousRow])) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                queenCol[row] = col;

                if (solve(row + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getQueenColumn(int row) {
        return queenCol[row];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < numQueens; row++) {
            for (int col = 0; col < numQueens; col++) {
                sb.append(queenCol[row] == col ? "Q " : "+ ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Queens? ");
        int numQueens = in.nextInt();

        System.out.println(new NQueensPuzzle(numQueens));

        in.close();
    }
}
