import java.util.Arrays;

public class ChompGame {
	int playerTurn = 1;
	int row;
	int col;
	int[][] board;
	
	public ChompGame(int row, int col) {
		this.row = row;
		this.col = col;
		this.board = new int[row][col];
	}
//good
	public boolean checkIfChomped(int row, int col) {
		return board[row][col] == 1;
	}
//good
	public boolean chompAt(int row, int col) {
		if (isLegalChomp(row, col)) {
			for (int r = row; r < board.length; r++) {
				for (int c = board[r].length - 1; c >= col; c--) {
					board[r][c] = 1;
				}
			}
			playerTurn = (playerTurn == 1) ? 2 : 1;
			return true;
		}
		return false;
	}
//good
	public int getCols() {
		return this.col;
	}
// good
	public int getCurrentPlayer() {
		return playerTurn;
	}
//good
	public int getRows() {
		return this.row;
	}
// good
	public boolean isGameOver() {
		return board[0][0] == 1;
	}

	public boolean isLegalChomp(int row, int col) {
		if (row < 0 || row >= this.row || 
				col < 0 || col >= this.col) {
			return false;
		}
			return !checkIfChomped(row, col);
	}
//good
	@Override
	public String toString() {
		String boa = " ";
		for (int i = 0; i < board[0].length; i++)
			boa = boa.concat(String.valueOf(i));
		boa = boa.concat("\n");
		for (int r = 0; r < board.length; r++) {
			boa = boa.concat(String.valueOf(r));
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 0)
					boa = boa.concat("*");
				else
					boa = boa.concat(" ");
			}
			boa = boa.concat("\n");
		}
		return boa;
	}






}
