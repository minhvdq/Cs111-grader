public class ChompGame {

	private boolean[][] board;
	private int rows;
	private int cols;
	private int currentPlayer;


	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		// Default boolean in Java is false; false = unchomped, true = chomped
		this.board = new boolean[rows][cols];
		this.currentPlayer = 1;
	}


	public boolean checkIfChomped(int row, int col) {
		return board[row][col];
	}


	public boolean chompAt(int row, int col) {
		if (!isLegalChomp(row, col)) {
			return false;
		}
		if (currentPlayer == 1 ) {
			currentPlayer++ ;
		}
		else {
			currentPlayer-- ;
		}

		for (int r = row; r < rows; r++) {
			for (int c = col; c < cols; c++) {
				board[r][c] = true;
			}
		}
		

		return true;
	}


	public int getCols() {
		return cols;
	}


	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getRows() {
		return rows;
	}


	public boolean isGameOver() {
		return board[0][0];
	}


	public boolean isLegalChomp(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return false;
		}
		return !board[row][col];
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int c = 0; c < cols; c++) {
			sb.append(c);
		}
		sb.append("\n");

		for (int r = 0; r < rows; r++) {
			sb.append(r);
			for (int c = 0; c < cols; c++) {
				if (board[r][c]) {
					sb.append(" ");
				} else {
					sb.append("*");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}