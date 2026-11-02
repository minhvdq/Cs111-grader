public class ChompGame {
    private boolean[][] board;
    private int rows;
    private int cols;
    private int currentPlayer;
    private boolean gameOver;
    
    public ChompGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = true;
            }
        }
        this.currentPlayer = 1;
        this.gameOver = false;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isLegalMove(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return false;
        }
        return board[r][c];
    }

    public void makeMove(int r, int c) {
        if (!isLegalMove(r, c)) {
            return;
        }

        for (int row = r; row < rows; row++) {
            for (int col = c; col < cols; col++) {
                board[row][col] = false;
            }
        }

   
        if (!board[0][0]) {
            gameOver = true;
        } else {
    
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
 
        sb.append(" ");
        for (int c = 0; c < cols; c++) {
            sb.append(c);
        }
        sb.append("\n");

        for (int r = 0; r < rows; r++) {
            sb.append(r);
            for (int c = 0; c < cols; c++) {
                if (board[r][c]) {
                    sb.append("*");
                }
            }
            if (r < rows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}