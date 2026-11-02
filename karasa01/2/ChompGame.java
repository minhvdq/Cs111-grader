public class ChompGame {

    private boolean[][] chomped;
    private int rows;
    private int cols;
    private int currentPlayer;
    public ChompGame(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        chomped = new boolean[rows][cols];
        currentPlayer = 1;
    }

    public boolean checkIfChomped(int row, int col) {
    	return chomped[row][col];
    }

    public boolean isLegalChomp(int row, int col) {
    	return row >= 0 && row < rows &&
               col >= 0 && col < cols &&
               !chomped[row][col];
    }

    public boolean chompAt(int row, int col) {
    	if (!isLegalChomp(row, col)) {
            return false;
        }

        for (int r = row; r < rows; r++) {
        	for (int c = col; c < cols; c++) {
        		chomped[r][c] = true;
            }
        }

        if (currentPlayer == 1) {
            currentPlayer = 2;
        }
        else {
            currentPlayer = 1;
        }
        return true;
    }

    public boolean isGameOver() {
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

        for (int c = 0; c < cols; c++) {
            result += c;
        }

        result += "\n";

        for (int r = 0; r < rows; r++) {
        	result += r;
        	for (int c = 0; c < cols; c++) {
        		if (chomped[r][c]) {
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