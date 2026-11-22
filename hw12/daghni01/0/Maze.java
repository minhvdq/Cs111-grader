public class Maze {

	private static final char OPEN = ' ';
	private static final char WALL = 'W';
	private static final char SOLUTION_PATH = '.';
	private static final char REACHED = 'x';

	private char[][] grid; 

	public void solveMaze() {

		solveMaze(0, 0);
	}
	private boolean solveMaze(int row, int col) {
		grid[row][col] = SOLUTION_PATH;

		if (row == grid.length - 1 && col == grid[0].length - 1) {
			return true;
		}

		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		for (int i = 0; i < 4; i++) {
			int row2 = row + dRow[i];
			int col2 = col + dCol[i];


			if (isValid(row2, col2) && grid[row2][col2] == OPEN) {

				if (solveMaze(row2, col2)) {
					return true;
				}
			}
		}


		grid[row][col] = REACHED;
		return false;
	}

	private boolean isValid(int r, int c) {
		return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
	}
}