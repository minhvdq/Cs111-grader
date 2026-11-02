/**
 * ChompGame
 */
public class ChompGame {

    private int rows;
    private int cols;
    private boolean[][] gameBoard; // false for unchomped, true for chomped
    private int currentPlayer;

    public ChompGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.gameBoard = new boolean[rows][cols];
        this.currentPlayer = 1;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public boolean checkIfChomped(int row, int col) {
        return this.gameBoard[row][col];
    }

    public boolean isGameOver() {
        return this.gameBoard[0][0];
    }

    public boolean isLegalChomp(int row, int col) {
        return (
            row < this.rows &&
            col < this.cols &&
            !isGameOver() &&
            !checkIfChomped(row, col)
        );
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean chompAt(int row, int col) {
        if (!isLegalChomp(row, col)) return false;
        for (int rowIndex = row; rowIndex < this.rows; rowIndex++) {
            for (int colIndex = col; colIndex < this.cols; colIndex++) {
                gameBoard[rowIndex][colIndex] = true;
            }
        }

        this.currentPlayer = (this.currentPlayer % 2) + 1;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append(" ");
        for (
            int colIndex = 0;
            colIndex < this.cols;
            colIndex++
        ) boardString.append(colIndex);
        for (int rowIndex = 0; rowIndex < this.rows; rowIndex++) {
            boardString.append("\n");
            boardString.append(rowIndex);
            for (int colIndex = 0; colIndex < this.cols; colIndex++) {
                boardString.append(
                    this.gameBoard[rowIndex][colIndex] ? " " : "*"
                );
            }
        }
        return boardString.toString();
    }
}
