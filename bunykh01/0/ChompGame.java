public class ChompGame {
    private boolean[][] board;
    private int rows;
    private int cols;
    private int currentPlayer;

    public ChompGame(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        board = new boolean[rows][cols];
        currentPlayer = 1;
    }

    public boolean checkIfChomped(int row, int col) {
        return board[row][col];
    }

    public boolean isLegalChomp(int row, int col) {
        if((row >= 0 && row < rows) && (col >= 0 && col < cols) ){
            if (!checkIfChomped(row, col)){
                return true;
            }
        }
        return false;    

    }

    public boolean chompAt(int row, int col) {
        if(isLegalChomp(row, col)){
            for (int i = row; i<rows; i++){
                for (int c = col; c<cols; c++){

                   board[i][c] = true; 
                }
            }
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            return true;
        }
        return false;

    }
    public boolean isGameOver() {
        return checkIfChomped(0, 0);
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

    for (int i = 0; i < cols; i++) {
        result += i;
    }
    result += "\n";

    for (int j = 0; j < rows; j++) {
        result += j;

        for (int i = 0; i < cols; i++) {
            if (checkIfChomped(j, i)) {
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
