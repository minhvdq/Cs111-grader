
import java.util.Scanner;
import java.util.Arrays;

public class NQueensPuzzle {

	private int numQueens; // number of queens
	private int[] queenCol; // column of a queen indexed by row
	
	 
	// create solved n-queens puzzle with numQueens 
	public NQueensPuzzle(int numQueens) {
		this.numQueens = numQueens;
		queenCol = new int[numQueens];
		solve(0);
	}

	/**
	 * Attempt to find a solution to the n-queens puzzle from the given row onward, returning whether or not the solution search was successful.
	 * @param currentRow current row for placing a queen,
	 * @return whether or not the solution search was successful
	 */

//		 * - For each column in the given row in increasing order:
//		 * -- Check each preceding row queen placement to see if that queen would conflict with (i.e. attack) this row and column.
//		 * -- If there is no conflict, place this row's queen in this column and recursively seek to solve a placement for the next row.
//		 * -- If that solution attempt is successful (i.e. the recursive solve call returns true), return true.
//		 * - If no column placement led to a successful solution, return false.
//		 * 
//		 * Hint: Since we're placing only one queen per row, the only possible conflicts are 
//		 * (1) two queens sharing the same column, and
//		 * (2) two queens sharing a same diagonal (i.e. the absolute value of their row difference is equal to the absolute value of their column difference).
//		 */
		
		//  - implement algorithm above
	
	private boolean solve(int currentRow) {
		int numCol = numQueens;
		boolean[] call = new boolean[numCol];
		boolean answer = false;
		
		if (currentRow == numQueens) // have placed all queens
			answer = true;
		
		else {
			for (int colCandidate = 1; colCandidate <= numCol; colCandidate++) {
				for (int otherRow = 1; otherRow <= numQueens; otherRow++) {
					if(getQueenColumn(otherRow) == colCandidate) // queen in same column?
						call[colCandidate] = false;
					else
						call[colCandidate] = true;
					if (Math.abs(currentRow - otherRow) == 
							Math.abs(colCandidate - getQueenColumn(otherRow))) // queen in same diagonal?
						call[colCandidate] = false;
					else
						call[colCandidate] = true;
				}
			if (call[colCandidate] == true) { // no conflicts 
				queenCol[currentRow] = colCandidate;
				if (solve(currentRow + 1))
					answer = true;
			}
			else 
				answer = false;
			}		
		}
		
		return answer;
	}


	public int getQueenColumn(int row) {
		return queenCol[row];
	}



	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < numQueens; row++) {
			for (int col = 0; col < numQueens; col++)
				sb.append(queenCol[row] == col ? "Q " : "+ ");
			sb.append("\n");
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Queens? ");
		int numQueens = in.nextInt();
		System.out.println(new NQueensPuzzle(numQueens));	
	}


}