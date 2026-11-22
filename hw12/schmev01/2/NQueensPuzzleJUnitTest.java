import static org.junit.Assert.*;

import org.junit.Test;

public class NQueensPuzzleJUnitTest {
	private int numQueens; // number of queens
	private int[] queenCol; // column of a queen indexed by row

	private void computeSolution(int n) {
		numQueens = n;
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
		 * -- If there is no conflict, place this row's queen in this column and recursively seek to solve a placement for the next rows.
		 * -- If that solution attempt is successful (i.e. the recursive solve call returns true), return true.
		 * - If no column placement led to a successful solution, return false.
		 * 
		 * Hint: Since we're placing only one queen per row, the only possible conflicts are 
		 * (1) two queens sharing the same column, and
		 * (2) two queens sharing a same diagonal (i.e. the absolute value of their row difference is equal to the absolute value of their column difference).
		 */
		
		// TODO - implement algorithm above
		
		if (row == numQueens) // no more queens to place --> solution!
			return true;
		
		// for each column in the given row, ...
		for (int col = 0; col < numQueens; col++) {
			// check each queen placed in previous rows for a conflict.
			boolean colHasConflict = false;
			for (int row2 = 0; !colHasConflict && row2 < row; row2++)
				colHasConflict = queenCol[row2] == col || Math.abs(queenCol[row2] - col) == row - row2;
			
			// If there is no conflict, place the queen and
			// recursively solve the rest starting with the next row.
			if (!colHasConflict) {
				queenCol[row] = col;
				if (solve(row + 1))// If there was a solution, return true.
					return true;
			}
		}
		// Reset the queen to column 0 and return false.
//		queenCol[row] = 0; // not necessary
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

	
	@Test
	public void test() {
		for (int n = 4; n <= 12; n++) {
			System.out.println("Testing " + n + " queens...");
			computeSolution(n);
			NQueensPuzzle nqp = new NQueensPuzzle(n);
			for (int r = 0; r < n; r++)
				if (getQueenColumn(r) != nqp.getQueenColumn(r))
					fail(String.format("For %d queens, a correctly implemented algorithm would have the queen in row %d have column %d.  However, yours was placed at column %d.", 
							n, r, getQueenColumn(r), nqp.getQueenColumn(r)));
		}
	}

}
