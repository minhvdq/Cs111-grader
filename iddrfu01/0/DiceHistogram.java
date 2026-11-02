import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {

		// Expect a tri-dis
		// to roll 2 dice of a 12. you roll 3 of a 12
		// Sum of 2 dice
		// Don't do Random object - int (11)(2) - It wil not work
		// calc the max
		// max/#of times
		// max * (something)/#of Times - similar to the average

		Scanner in = new Scanner(System.in);
		Random rand = new Random();

		System.out.print("How many rolls? ");
		int rolls = in.nextInt();

		int[] counts = new int[13];
		for (int i = 0; i < rolls; i++) {
			int sum = (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
			counts[sum]++;
		}

		int max = 0;
		for (int sum = 2; sum <= 12; sum++) {
			if (counts[sum] > max) {
				max = counts[sum];
			}
		}
		System.out.println("Max count: " + max);

		for (int sum = 2; sum <= 12; sum++) {
			int stars = (max == 0) ? 0 : (int) Math.round(60.0 * counts[sum] / max);
			System.out.printf("%2d ", sum);
			for (int i = 0; i < stars; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		in.close();
	}

}
