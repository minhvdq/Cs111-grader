
public class ChompGame {
	
	private boolean[][] board;
	private int currentPlayer;
	
	public ChompGame(int rows, int cols) {
		board = new boolean[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				board[i][j] = true;
		}
		
		currentPlayer = 1;
	}
	
	public int getRows() {
		return board.length;
	}
	
	public int getCols() {
		return board[0].length;
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean checkIfChomped(int row, int col) {
		return !board[row][col];
	}
	
	public boolean isLegalChomp(int row, int col) {
		boolean inBounds = (row >= 0 && row < getRows() && col >= 0 && col < getCols());
		if (!inBounds) {
			return false;
		}
		
		return board[row][col];
	}
	
	public boolean chompAt(int row, int col) {
		if (!isLegalChomp(row, col)) {
			return false;
		}
		
		for (int r = row; r < getRows(); r++) {
			for (int c = col; c < getCols(); c++)
				board[r][c] = false;
		}
		
		if (currentPlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;
		
		return true;
	}
	

	public boolean isGameOver() {
		return !board[0][0];
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" ");
		for (int c = 0; c < getCols(); c++) {
			sb.append(c);
		}
		
		sb.append("\n");
		
		for (int r = 0; r < getRows(); r++) {
			sb.append(r);
			for (int c = 0; c < getCols(); c++) {
				if (board[r][c]) {
					sb.append("*");
				}
				else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
