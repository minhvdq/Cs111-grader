import java.util.Arrays;

public class ChompGame {

	// fields
	private int rows = 0;
	private int cols = 0;
	private int[][] board = new int[rows][cols];
	public int currentPlayer = 1;
	

	// constructor
	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.board = new int[rows][cols];
	}

	//methods

	public boolean checkIfChomped(int row, int col) {
	if (board[row][col] == 0)
		return false;
	else
		return true;
	
	}
	
	public boolean isLegalChomp(int row, int col) {
		// check if in bounds
		if (row >= rows || row < 0) 
			return false;
		if (col >= cols || col < 0) 
			return false;
			
		
		// check if chomped
		if (!checkIfChomped(row, col)) // if not chomped
			return true;
		else 
			return false;
	}
	
	
	public boolean chompAt(int row, int col) {
		if (!isLegalChomp(row, col))
			return false;
		else {
			
		// chomp all positions from point right and down
			
			
			for (int r = row; r < rows; r++) {
				for (int c = col; c < cols; c++) {
					board[r][c] = 1;
				}
			}
		
		// change current player
		currentPlayer = (currentPlayer == 1) ? 2 : 1;
		// return true
			return true;
		}
	}
	
	
	public boolean isGameOver() {
		if (board[0][0] != 0) { // chomped
			return true;
		}
		else // board[0][0] still unchomped
			return false;
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

	
	

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < cols; i++) // header
			sb.append(i);
		sb.append("\n");
		
		for (int r = 0; r < rows; r++) {
			sb.append(r);
			for (int c = 0; c < cols; c++) {
				sb.append(checkIfChomped(r, c) ? " " : "*");
			}
			sb.append("\n");
		}
		
		String printBoard = sb.toString();
		return printBoard;
	}


	
	
		
		
		

		
		
		

}



