
public class NQueensPuzzle {
	private final int n;
	private final int[] queenColumnByRow;

	public NQueensPuzzle(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be nonnegative");
		}

		this.n = n;
		this.queenColumnByRow = new int[n];
	}

	private boolean solve(int row) {
		if (row == n) {
			return true;
		}

		for (int candidateCol = 0; candidateCol < n; candidateCol++) {
			boolean conflict = false;

			for (int previousRow = 0; previousRow < row; previousRow++) {
				int previousCol = queenColumnByRow[previousRow];

				boolean sameColumn = previousCol == candidateCol;
				boolean sameDiagonal =
						Math.abs(previousRow - row)
						== Math.abs(previousCol - candidateCol);

				if (sameColumn || sameDiagonal) {
					conflict = true;
					break;
				}
			}
			if (!conflict) {
				queenColumnByRow[row] = candidateCol;

				if (solve(row + 1)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean solvePuzzle() {
		return solve(0);
	}

	public void printSolution() {
		if (!solvePuzzle()) {
			System.out.println("No solution.");
			return;
		}

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				System.out.print(queenColumnByRow[row] == col ? "Q " : ". ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NQueensPuzzle puzzle = new NQueensPuzzle(8);
		puzzle.printSolution();
	}
}