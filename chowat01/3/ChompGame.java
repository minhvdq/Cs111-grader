
public class ChompGame {
	
	private int rows;
	private int cols;
	private boolean[][]chomped;
	private int currentPlayer;
	
	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.chomped = new boolean[rows][cols];
		this.currentPlayer = 1;
	}
	
	public boolean checkIfChomped(int row, int col) {
		return chomped[row][col];
	}
	
	public boolean isLegalChomp(int row, int col) {
		return row >= 0 && row < rows && col >= 0 && col < cols && !chomped[row][col];
	}
	
	public boolean chompAt(int row, int col) {
		if(!isLegalChomp(row, col)) {
			return false;
		}
		for(int r = row; r < rows; r++) {
			for(int c = col; c < cols; c++) {
				chomped[r][c] = true;
			}
		}
		
		if(currentPlayer == 1) {
			currentPlayer = 2;
		}
		else {
			currentPlayer = 1;
		}
		return true;
	}
	
	public boolean isGameOver(){
		return chomped[0][0];
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
		String result = " ";
		for (int col = 0; col < cols; col++) {
			result += col;
		}
		result += "\n";
		
		for (int row = 0; row < rows; row++) {
			result += row;
			
			for(int col = 0; col < cols; col++) {
				if(chomped[row][col]) {
					result += " ";
				}
				else {
					result += "*";
				}
			}
			result += "\n";
		}
		return result;
	}
}
