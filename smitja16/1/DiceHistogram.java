import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random random = new Random();
		int[] rollCounts = new int[11];
		// simulate rolls
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		for (int i = 0; i < rolls; i++) {
			int roll = (random.nextInt(6)) + (random.nextInt(6)); // two separate dice rolls, sum is 0 - 10
			rollCounts[roll]++;
		}
		// determine max
		int max = 0;
		for (int count : rollCounts) {
			if (count > max) {
				max = count;
			}
		}
		System.out.println("Max count: " + max);
		// print results
		for (int i = 0; i <= 10; i++) {
			System.out.printf("%2d ", i + 2);
			int numAsterisks = (int) Math.round((double) rollCounts[i] / max * 60);
			for (int j = 0; j < numAsterisks; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		in.close();
	}

}
