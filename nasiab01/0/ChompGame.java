
public class ChompGame {

    private int rows;
    private int cols;
    private boolean[][] board;
    private int currentPlayer;

    public ChompGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new boolean[rows][cols];
        currentPlayer = 1;
    }

    public boolean checkIfChomped(int row, int col) {
        return board[row][col];
    }

    public boolean isLegalChomp(int row, int col) {
        return row >= 0 && row < rows
                && col >= 0 && col < cols
                && !board[row][col];
    }

    public boolean chompAt(int row, int col) {
        if (!isLegalChomp(row, col)) {
            return false;
        }

        for (int i = row; i < rows; i++) {
            for (int j = col; j < cols; j++) {
                board[i][j] = true;
            }
        }

        currentPlayer = 3 - currentPlayer;

        return true;
    }

    public boolean isGameOver() {
        return board[0][0];
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

            for (int col = 0; col < cols; col++) {
                if (board[row][col]) {
                    result += " ";
                } else {
                    result += "*";
                }
            }

            result += "\n";
        }

        return result;
    }
}
