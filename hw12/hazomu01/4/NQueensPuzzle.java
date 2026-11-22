
import java.util.Scanner;

public class NQueensPuzzle {

	private int numQueens;
	private int[] queenCol;

	public NQueensPuzzle(int numQueens) {
		this.numQueens = numQueens;
		queenCol = new int[numQueens];
		solve(0);
	}

	private boolean solve(int currentRow) {
		if (currentRow == numQueens) {
			return true;
		}

		for (int candidateCol = 0; candidateCol < numQueens; candidateCol++) {
			boolean conflictFound = false;

			for (int earlierRow = 0; earlierRow < currentRow; earlierRow++) {
				if (queenCol[earlierRow] == candidateCol ||
						Math.abs(currentRow - earlierRow) ==
						Math.abs(candidateCol - queenCol[earlierRow])) {

					conflictFound = true;
					break;
				}
			}

			if (!conflictFound) {
				queenCol[currentRow] = candidateCol;

				if (solve(currentRow + 1)) {
					return true;
				}
			}
		}

		return false;
	}

	public int getQueenColumn(int row) {
		return queenCol[row];
	}

	public String toString() {
		StringBuilder output = new StringBuilder();

		for (int rowIndex = 0; rowIndex < numQueens; rowIndex++) {
			for (int colIndex = 0; colIndex < numQueens; colIndex++) {
				output.append(
						queenCol[rowIndex] == colIndex ? "Q " : "+ "
						);
			}

			output.append("\n");
		}

		return output.toString();
	}

	public static void main(String[] arguments) {
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Queens? ");
		int boardSize = keyboard.nextInt();

		System.out.println(new NQueensPuzzle(boardSize));

		keyboard.close();
	}
}
