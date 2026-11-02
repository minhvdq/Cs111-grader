
public class ChompGame {

	boolean[][] chompBoard;
	int player;
	
	public ChompGame(int rows, int cols) {
		chompBoard = new boolean[rows][cols];
		player = 1;
	}
	
	boolean checkIfChomped(int row, int col) {
		return chompBoard[row][col];
	}
	
	int getRows() {
		return chompBoard.length;
	}
	
	int getCols() {
		return chompBoard[0].length;
	}
	
	int getCurrentPlayer() {
		return player;
	}
	
	boolean isGameOver() {
		return chompBoard[0][0];
	}
	
	boolean isLegalChomp(int row, int col) {
		if (row >= 0 && row < chompBoard.length && col >= 0 && col < chompBoard[0].length) {
			return !chompBoard[row][col];
		} else {
			return false;
		}
	}
	
	boolean chompAt(int row, int col) {
		if (isLegalChomp(row, col)) {
			player = player == 1 ? 2 : 1;
			for (int i = row; i < chompBoard.length; i++) {
				for (int j = col; j < chompBoard[row].length; j++) {
					chompBoard[i][j] = true;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(' ');
		for (int i = 0; i < chompBoard[0].length; i++) {
			sb.append(i);
		}
		sb.append("\n");
		for (int i = 0; i < chompBoard.length; i++) {
			sb.append(i);
			for (int j = 0; j < chompBoard[i].length; j++) {
				if (chompBoard[i][j]) {
					sb.append(' ');
				} else {
					sb.append('*');
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
