import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		// get numRolls
		Scanner in = new Scanner(System.in);
		Random random = new Random();
		System.out.print("How many rolls? ");
		int numRolls = in.nextInt();


		// get rolls and input frequency into array
		int[] rollFreq = new int[11];
		for (int i = 0; i < numRolls; i++) {
			int roll1 = random.nextInt(6) + 1;
			int roll2 = random.nextInt(6) + 1;
			int dieSum = roll1 + roll2;
			rollFreq[dieSum -2] += 1; // increase freq by one
		}


		// finding max
		int maxCount = rollFreq[0];
		for (int j = 1; j < rollFreq.length; j++) {
			if (rollFreq[j] > maxCount)
				maxCount = rollFreq[j];
		}	
		// print max count
		System.out.println("Max count: " +  maxCount);


		// printing histogram
		for (int i = 2; i <= 12; i++) { // for each possible die sum
			System.out.printf("%2d ", i);
			if (rollFreq[i - 2] == 0) {
				System.out.println();
			}
			else {
				for(int k = 0; k < (60.0 * rollFreq[i - 2] / maxCount); k++) {
					System.out.print("*");
				}
				System.out.println();		
			}
		}


		in.close();
	}

}
