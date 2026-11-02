

public class ChompGame {
	
	private boolean[][] chompedBoard;
	private int rows;
	private int cols;
	private int currentPlayer;
	
	public ChompGame(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.chompedBoard = new boolean[rows][cols];
		this.currentPlayer = 1;
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
	
	public boolean checkIfChomped(int row, int col) {
		return chompedBoard[row][col];
	}
	
	public boolean isGameOver() {
		return chompedBoard[0][0];
	}
	
	public boolean isLegalChomp(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return false;
		}
		return !chompedBoard[row][col];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int c = 0; c < cols; c++) {
			sb.append(c % 10);
		}
		sb.append("\n");
		
		for (int r = 0; r < rows; r++) {
			sb.append(r % 10);
			for (int c = 0; c < cols; c++) {
				if (chompedBoard[r][c]) {
					sb.append(" ");
				} 
				else {
						sb.append("*");
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
				chompedBoard[r][c] = true;
			}
		}
		currentPlayer = (currentPlayer == 1) ? 2 : 1;
		return true;
	}
}

//FOR problem #3 on HW, makes sense to attempt to complete together and then submit rather than do one at a time. 
// method implementation setup and the main method work

//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		
//		System.out.print("Rows? ");
//		int rows = in.nextInt();
//		
//		System.out.print("Columns? ");
//		int cols = in.nextInt();
//		
//		ChompGame game = new ChompGame(rows, cols);
//		
//		System.out.println("CHOMP");
//		System.out.println("In the game of Chomp, players bite a rectangular cookie in turn.");
//		System.out.println("The Player champing the last bite (0,0) loses.");
//		System.out.println("Each chomp, a row and column, bites off all below and to the right.");
//		
//		while (!game.isGameOver()) {
//			
//			System.out.println();
//			System.out.print(game.toString());
//			System.out.println();
//			
//			System.out.print("Player " + game.getCurrentPlayer() + "chomp? ");
//			int row = in.nextInt();
//			int col = in.nextInt();
//			
//			boolean success = game.chompAt(row, col);
//			if (!success) {
//				System.out.println("That is not a legal chomp posistion.");
//			}
//		}
//		System.out.println();
//		System.out.print("Player " + game.getCurrentPlayer() + "wins! ");
//		
//		in.close();
//	}
//
//}
