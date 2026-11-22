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
		/* Algorithm:
		 * - If the row is equal to the number of queens, we've placed all queens and found a solution, so return true.
		 * - For each column in the given row in increasing order:
		 * -- Check each preceding row queen placement to see if that queen would conflict with (i.e. attack) this row and column.
		 * -- If there is no conflict, place this row's queen in this column and recursively seek to solve a placement for the next row.
		 * -- If that solution attempt is successful (i.e. the recursive solve call returns true), return true.
		 * - If no column placement led to a successful solution, return false.
		 * 
		 * Hint: Since we're placing only one queen per row, the only possible conflicts are 
		 * (1) two queens sharing the same column, and
		 * (2) two queens sharing a same diagonal (i.e. the absolute value of their row difference is equal to the absolute value of their column difference).
		 */
		
		if (row == numQueens) {
			return true;
		}
		for (int colCheck = 0; colCheck < numQueens; colCheck++) {
			boolean isValid = true;
			for (int otherRow = 0; otherRow < row; otherRow++) {
				if (colCheck != getQueenColumn(otherRow) && (Math.abs(row - otherRow) != Math.abs(colCheck - getQueenColumn(otherRow)))) {
					continue;
				} else {
					isValid = false;
				}
			}
			if (isValid) {
				queenCol[row] = colCheck;
				if (solve(row + 1)) {
					return true;
				}
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