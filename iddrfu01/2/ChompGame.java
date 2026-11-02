
public class ChompGame {

	/* module it by designing a object 
	 * Two dimentional array - use this 
	 * Use boolean - Remember 
	 * isChomp - use this
	 * (int row)
	 * Int Col
	 * CAt - is a legal move, so...........
	 * Player - create a method for this
	 */

	private final boolean[][] cookie; // true = cookie still present
	private final int rows;
	private final int cols;
	private int currentPlayer;
	private boolean gameOver;

	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.cookie = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
			cookie[r][c] = true;
			}
		}
		this.currentPlayer = 1;
		this.gameOver = false;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int getWinner() {
		return gameOver ? currentPlayer : 0;
	}

	public boolean isLegalChomp(int row, int col) {
		return !gameOver
				&& row >= 0 && row < rows
				&& col >= 0 && col < cols
				&& cookie[row][col];
	}

	public boolean chomp(int row, int col) {
		if (!isLegalChomp(row, col)) {
			return false;
		}
		for (int r = row; r < rows; r++) {
			for (int c = col; c < cols; c++) {
				cookie[r][c] = false;
			}
		}
		if (row == 0 && col == 0) {
			gameOver = true;
		}
		currentPlayer = (currentPlayer == 1) ? 2 : 1;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" ");
		for (int c = 0; c < cols; c++) {
			sb.append(c % 10);
		}
		for (int r = 0; r < rows; r++) {
			sb.append("\n").append(r % 10);
			for (int c = 0; c < cols; c++) {
				sb.append(cookie[r][c] ? '*' : ' ');
			}
		}
		return sb.toString();
	}
}





