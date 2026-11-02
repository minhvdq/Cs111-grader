
/**
 * ChompGame - A class for modeling the game of <a href="https://en.wikipedia.org/wiki/Chomp">Chomp</a>.  Use <code>Chomp</code> for a text-based interface to this class.  
 * Note: While the toString() method builds a String, there should be no printing and no input/output in general in this implementation. 
 */
public class ChompGame {
	private int rows, cols;
	private boolean[][] chomped;
	private int player = 1;

	/**
	 * Create an initial board with the given number of rows and columns with all positions unchomped.
	 * @param rows the number of rows in the board
	 * @param cols the number of columns in the board
	 */
	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		chomped = new boolean[rows][cols];
	}

	/**
	 * Return whether or not the board has been chomped at 0-based index position [row][col].
	 * @param row 0-based row index
	 * @param col 0-based column index
	 * @return whether or not the board has been chomped at 0-based index position [row][col]
	 */
	public boolean checkIfChomped(int row, int col) {
		return chomped[row][col];
	}


	/**
	 * Return whether or not a chomp at [row][col] would be a legal chomp, that is, would be both in bounds and not previously chomped.
	 * @param row hypothetical chomp row
	 * @param col hypothetical chomp column
	 * @return whether or not a chomp at [row][col] would be a legal chomp, that is, would be both in bounds and not previously chomped
	 */
	public boolean isLegalChomp(int row, int col) {
		return row >= 0 && row < rows && col >= 0 && col < cols && !checkIfChomped(row, col);
	}

	/**
	 * If a chomp at [row][col] is legal, then (1) chomp all positions in the rectangle of positions with [row][col] being the upper-left-most and [rows-1][cols-1] being the lower-right-most, (2) change the current player from 1 to 2 or vice versa, and (3) return true.
	 * If a chomp at [row][col] is not legal, then make no change and return false.
	 * @param row row of attempted chomp
	 * @param col column of attempted chomp
	 * @return whether or not the chomp move was legal
	 */
	public boolean chompAt(int row, int col) {
		if (isLegalChomp(row, col)) {
			for (int r = row; r < rows; r++)
				for (int c = col; c < cols; c++)
					chomped[r][c] = true;
			player = (player == 1) ? 2 : 1;
			return true;
		}
		return false;
	}

	/**
	 * Return whether or not the game is over, that is, whether or not position [0][0] has been chomped.
	 * @return whether or not the game is over, that is, whether or not position [0][0] has been chomped
	 */
	public boolean isGameOver() {
		return checkIfChomped(0, 0);
	}

	/**
	 * Get the number of rows in the board.
	 * @return the number of rows in the board
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Get the number of columns in the board.
	 * @return the number of columns in the board
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Return the current player number, initially 1 and alternating between 1 and 2 with each successive legal play.  This alternation takes place only in the <code>chompAt(int row, int col)</code> method when a legal chomp move is made.
	 * @return the current player number
	 */
	public int getCurrentPlayer() {
		return player;
	}


	/** 
	 * Return the String representation of the board, such that each line is terminated with a newline, the first line consists of a space and the digits 0 through (cols-1) 
	 * and following lines consist of the row index digit followed by asterisks/spaces representing unchomped/chomped positions, respectively.
	 * @return the String representation of the board
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(" ");
		for (int col = 0; col < cols; col++) 
			sb.append(col);
		sb.append("\n");
		for (int row = 0; row < rows; row++) {
			sb.append(row);
			for (int col = 0; col < cols; col++)
				sb.append(chomped[row][col] ? " " : "*");
			sb.append("\n");
		}
		return sb.toString();
	}

}
