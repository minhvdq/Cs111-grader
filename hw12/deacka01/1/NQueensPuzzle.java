
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
		
		
		if (currentRow >= numQueens - 1)  { // have placed all queens
			answer = true;
			System.out.println("yay");
		}
		

		
		else {
			
			for (int colCandidate = 0; colCandidate < numQueens; colCandidate++) {
				call[colCandidate] = false;
				if (currentRow == 0)
					call[colCandidate] = true;
					else {	
						for (int pastRow = 0; pastRow <= currentRow; pastRow++) {
							if (queenCol[pastRow] == colCandidate) {
								call[colCandidate] = false;
								break;
							}
							else
								call[colCandidate] = true;
						}

						if (call[colCandidate] == true) {
							for (int pastRow = 0; pastRow <= currentRow; pastRow++) {
								if (Math.abs(pastRow - currentRow) == 
										Math.abs(getQueenColumn(pastRow) - colCandidate)) {
									call[colCandidate] = false;
									break;
								}
								else
									call[colCandidate] = true;
							}
						}

					}
				if (call[colCandidate] == true) {
					queenCol[currentRow] = colCandidate;
					if (solve(currentRow + 1))
						answer = true;
					else 
						answer = false;
				}
				else if (call[colCandidate] == false) {
					answer = false;
				}
			}
		}

		return answer;
	}

		
//		if (currentRow > numQueens) // have placed all queens 
//			answer = true;
		
//		else {
//			for (int colCandidate = 1; colCandidate <= numCol; colCandidate++) {
//				for (int pastRow = 1; pastRow <= currentRow; pastRow++) {
//					if (pastRow == currentRow);
//					else { // check for other queen conflict
//						if (getQueenColumn(pastRow) == colCandidate) // col the same as another?
//							call[colCandidate] = false;
//						else 
//							call[colCandidate] = true;
//						if (Math.abs(pastRow - currentRow) == 
//								Math.abs(getQueenColumn(pastRow) - colCandidate)) // diagonal same as another?
//							call[colCandidate] = false;
//						else 
//							call[colCandidate] = true;
//					}
//				}
//				
//				if (call[colCandidate]) {
//					queenCol[currentRow] = colCandidate;
//					if (solve(currentRow + 1))
//						answer = true;
//					else 
//						answer = false;
//				}
//				else
//					answer = false;
//			}
//		}
//		
		
		



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