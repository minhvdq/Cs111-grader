
import java.util.Scanner;

public class NQueensPuzzle {

	private int numQueens; // number of queens
	private int[] queenCol; // column of a queen indexed by row
	
	 
	// create solved n-queens puzzle with numQueens 
	public NQueensPuzzle(int numQueens) {
		this.numQueens = numQueens;
		queenCol = new int[numQueens];
		solve(0);
	}



	
	private boolean solve(int currentRow) {
		boolean[] call = new boolean[numQueens];
		boolean answer = false;
	

		if (currentRow >= numQueens)  { // have placed all queens
			
			answer = true;
			return true;
		}


//		else {
//			for (int colCandidate = 0; colCandidate < numQueens; colCandidate++) {
//				for (int pastRow = 0; pastRow <= currentRow; pastRow++) {
//					if (currentRow == 0)
//						call[colCandidate] = true;
//					else if (pastRow == currentRow)
//						call[colCandidate] = true;
//					else if (queenCol[pastRow] == colCandidate) 
//						call[colCandidate] = false;
//					else if (Math.abs(pastRow - currentRow) == 
//							Math.abs(getQueenColumn(pastRow) - colCandidate))
//						call[colCandidate] = false;
//					else 
//						call[colCandidate] = true;
//				}
//
//				if (call[colCandidate] == false)
//					answer = false;
//				else {
//					queenCol[currentRow] = colCandidate;
//					if (solve(currentRow + 1))
//						answer = true;
//				}
//			}
//		}
//		return answer;
//	}

		else {

			for (int colCandidate = 0; colCandidate < numQueens; colCandidate++) {
				call[colCandidate] = true;	
				if (currentRow > 0)  {
					for (int pastRow = 0; pastRow <= currentRow; pastRow++) {
						if (pastRow == currentRow) { 
							call[colCandidate] = true;
							break;
						}
						else if (queenCol[pastRow] == colCandidate) {  // same column?
							call[colCandidate] = false;
							break;
						}
						else if (Math.abs(pastRow - currentRow) == 
								Math.abs(getQueenColumn(pastRow) - colCandidate)) { // same diagonal
							call[colCandidate] = false;
							break;
						}
						else
							call[colCandidate] = true; // no conflict
					}
				}
			

				if (call[colCandidate] == false) { // queen conflict
					answer = false;

				}

				else { // no conflict

					queenCol[currentRow] = colCandidate;
					if (solve(currentRow + 1) == true) {
						answer = true;
						return true;
					}
					else {
						answer = false;
						return false;
					}
				}
			}
		}

		return answer;
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
	}


}