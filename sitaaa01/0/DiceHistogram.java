import java.util.Scanner;
import java.util.Random;

public class DiceHistogram {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		System.out.print("How many rolls? ");
		int rolls = input.nextInt();

		int[] counts = new int[13];

		for (int i = 0; i < rolls; i++) {
			int die1 = random.nextInt(6) + 1;
			int die2 = random.nextInt(6) + 1;
			int sum = die1 + die2;

			counts[sum]++;
		}

		int max = 0;

		for (int i = 2; i <= 12; i++) {
			if (counts[i] > max) {
				max = counts[i];
			}
		}

		System.out.println("Max count: " + max);

		for (int i = 2; i <= 12; i++) {
			int stars = (int) Math.round(60.0 * counts[i] / max);

			System.out.printf("%2d ", i);

			for (int j = 0; j < stars; j++) {
				System.out.print("*");
			}

			System.out.println();
		}

		input.close();
	}
}