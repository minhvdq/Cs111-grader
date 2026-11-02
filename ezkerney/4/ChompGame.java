import java.util.Arrays;

public class ChompGame {

	private int rows;
	private int cols;
	private boolean[][] game;
	private int player = 1;
	
	public ChompGame(int rows, int cols) {
	this.rows = rows;
	this.cols = cols;
	this.game = new boolean[rows][cols];
	
}
	public boolean checkIfChomped(int row, int col) {
		boolean pinkyFinger = (game[row][col] == true);
		return pinkyFinger;
	}
	
	public boolean isLegalChomp(int row, int col) {
	    if (row < 0 || row >= rows || col < 0 || col >= cols) {
	        return false;
	    }

	    return game[row][col] == false;
	}
	public boolean chompAt(int row, int col) {
		if (isLegalChomp(row, col) == true) {
			for (int i = row; i < rows; i++) {
				for (int j = col; j < cols; j++) {
					game[i][j] = true;
				}
			}
			player = (player == 1 ? 2 : 1);
		}
		return isLegalChomp(row, col);
	}
	public boolean isGameOver() {
		boolean hamAndCheeseSandwich = (checkIfChomped(0, 0));
		return hamAndCheeseSandwich;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public int getCurrentPlayer() {
		return player;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" ");
		
		for (int i = 0; i < cols; i++) {
			sb.append(i);
		}
		
		
		for (int i = 0; i < rows; i++) {
			sb.append("\n");
			sb.append(i);
			for (int j = 0; j < cols; j++) {
				
				sb.append((checkIfChomped(i, j) == true ? " " : "*"));
			}
		}
		sb.append("\n");
		return sb.toString();
	}
	
}
