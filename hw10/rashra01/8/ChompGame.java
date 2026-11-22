
public class ChompGame {
	
	private boolean[][] board;
	private int rows;
	private int cols;
	private int currentPlayer;
	
	public ChompGame(int rows,int cols){
		
		this.rows=rows;
		this.cols=cols;
		this.board=new boolean[rows][cols];
		this.currentPlayer=1;
		
		for(int r=0; r<rows;r++) {
			for(int c=0; c<cols;c++) {
				board[r][c] =true;
			
			}
			//System.out.println();
		}
		this.currentPlayer =1;
	}

	public int getRows() {
		return rows;
	}
	
	
	public int getCols() {
		return cols;
	}

    public boolean checkIfChomped(int row, int col) {
	return !board[row][col];
    }
    
	
	public boolean[][] getBoard() {
		return board;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	
	public boolean isGameOver() {
		
		return  !board[0][0] ;
		
	}
	public boolean isLegalChomp(int row,int col) {		
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return false;
			
		}
		return board[row][col];  
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < cols; i++) {
            sb.append(i);
        }
        sb.append("\n");

        for (int r = 0; r < rows; r++) {
            sb.append(r);
            for (int c = 0; c < cols; c++) {
                if (board[r][c]) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

	public boolean chompAt(int row, int col) {
        if (!isLegalChomp(row, col)) {
            return false;
        }

        for (int r = row; r < rows; r++) {
            for (int c = col; c < cols; c++) {
                board[r][c] = false;
            }
        }

        // Switch turns if the game is still going
        //if (!isGameOver()) {
             if (currentPlayer == 1) {
            	
            	 currentPlayer = 2;
            	 
             }else {
            	 currentPlayer = 1;
            	 }

	     //}

        return true;
    }
	
}
