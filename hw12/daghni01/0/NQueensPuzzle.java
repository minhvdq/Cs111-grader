public class NQueensPuzzle {
	private int n;
	private int[] queens;
	public NQueensPuzzle(int n) {
		this.n = n;
		this.queens = new int[n];
	}
	public boolean solve() {
		return solve(0);
	}
	private boolean solve(int currentRow) {
		if (currentRow == n) {
			return true;
		}

		for (int candidateCol = 0; candidateCol < n; candidateCol++) {
			boolean hasConflict = false;

			for (int previousRow = 0; previousRow < currentRow; previousRow++) {
				int previousCol = queens[previousRow];
				if (previousCol == candidateCol || Math.abs(currentRow - previousRow) == Math.abs(candidateCol - previousCol)) {
					hasConflict = true;
					break;
				}
			}
			if (!hasConflict) {
				queens[currentRow] = candidateCol;
				if (solve(currentRow + 1)) {
					return true;
				}
			}
		}
		return false;
	}
}