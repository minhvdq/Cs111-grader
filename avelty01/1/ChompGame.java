public class ChompGame {
	
        private final int rows;
        private final int cols;
        private final boolean[][] chomped; 
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
        return row >= 0 && row < rows
        && col >= 0 && col < cols
        && !chomped[row][col];
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
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
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
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            for (int c = 0; c < cols; c++) {
                sb.append(c);
            }
            sb.append("\n");

            for (int r = 0; r < rows; r++) {
                sb.append(r);
                for (int c = 0; c < cols; c++) {
                    if (!chomped[r][c]) {
                        sb.append('*');
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        }