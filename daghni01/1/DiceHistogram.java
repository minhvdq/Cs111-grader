import java.util.Scanner;
import java.util.Random;


public class DiceHistogram {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		System.out.print("How many rolls? ");
		int rolls = input.nextInt();
		int[] counts = new int[13];
		for (int i = 0; i < rolls; i++) {
			int die1 = rand.nextInt(6) + 1;
			int die2 = rand.nextInt(6) + 1;
			int sum = die1 + die2;
			counts[sum]++;
		}
		int maxCount = 0;
		for (int sum = 2; sum <= 12; sum++) {
			if (counts[sum] > maxCount) {
				maxCount = counts[sum];
			}
		}
		System.out.println("Max count: " + maxCount);
		for (int sum = 2; sum <= 12; sum++) {
			int stars = (int) Math.round(60.0 * counts[sum] / maxCount);
			System.out.printf("%2d ", sum);
			for (int i = 0; i < stars; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		input.close();
	}

}