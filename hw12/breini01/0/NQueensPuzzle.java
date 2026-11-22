import java.util.Scanner;

public class NQueensPuzzle {

	private int numQueens; // number of queens
	private int[] queenCol; // column of a queen indexed by row



	public NQueensPuzzle(int numQueens) {
		this.numQueens = numQueens;
		queenCol = new int[numQueens];
		solve(0);
	}


	private boolean solve(int row) {
		if (row == numQueens)
			return true;
		else {
			for (int col = 0; col < numQueens; col++) {
				if (HATK(row, col) && DATK(row, col)) {
					queenCol[row] = col;
					if (solve(row + 1))
						return true;
				}
			}
			return false;
		}
	}

	public boolean HATK(int row, int col) {
		//right check
		for (int i = 0; i < row; i++) {
			if (queenCol[i] == col)
				return false;
		}
		return true;
	}

	public boolean DATK(int row, int col) {
		//diagonal up
		for (int i = 0; i < row; i++) {
			if (Math.abs(row - i) == Math.abs(col - queenCol[i]))
				return false;
			}
		return true;
	}

	public int getQueenColumn(int row) {
		return queenCol[row];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < numQueens; row++) {
			for (int col = 0; col < numQueens; col++)
				sb.append(queenCol[row] == col ? "Q " : "+ ");
			sb.append("\n");
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Queens? ");
		int numQueens = in.nextInt();
		System.out.println(new NQueensPuzzle(numQueens));	
		in.close();
	}
}