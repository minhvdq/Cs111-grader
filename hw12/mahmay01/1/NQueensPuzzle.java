import java.util.Scanner;

public class NQueensPuzzle {

	private int numQueens; // number of queens
	private int[] queenCol; // column of a queen indexed by row
	
	/**
	 * Create a solved n-queens puzzle given n.
	 * @param numQueens the number of queens
	 */
	public NQueensPuzzle(int numQueens) {
		this.numQueens = numQueens;
		queenCol = new int[numQueens];
		solve(0);
	}

	/**
	 * Attempt to find a solution to the n-queens puzzle from the given row onward, returning whether or not the solution search was successful.
	 * @param row current row for placing a queen,
	 * @return whether or not the solution search was successful
	 */
	
	private boolean solve(int row) {

    if (row == queenCol.length)
        return true;

    for (int candidateCol = 0; candidateCol < queenCol.length; candidateCol++) {

        boolean conflict = false;

        for (int previousRow = 0; previousRow < row; previousRow++) {
            if (queenCol[previousRow] == candidateCol ||
                Math.abs(row - previousRow) ==
                Math.abs(candidateCol - queenCol[previousRow])) {
                conflict = true;
                break;
            }
        }

        if (!conflict) {
            queenCol[row] = candidateCol;

            if (solve(row + 1))
                return true;
        }
    }

    return false;
}

		

	/**
	 * Return the column of the queen for a given row.
	 * @param row the given row
	 * @return the column of the queen for a given row
	 */
	public int getQueenColumn(int row) {
		return queenCol[row];
	}

	/**
	 * Return a String representation of the solution.
	 * @return a String representation of the solution
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < numQueens; row++) {
			for (int col = 0; col < numQueens; col++)
				sb.append(queenCol[row] == col ? "Q " : "+ ");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Prompt the user for a number of queens and print a corresponding n-queens puzzle solution.
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Queens? ");
		int numQueens = in.nextInt();
		System.out.println(new NQueensPuzzle(numQueens));	
		in.close();
	}


}
