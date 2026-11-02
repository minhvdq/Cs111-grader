import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void boundsTest() {
		final int TRIALS = 100000, ROLLS = 10000;
		int minMax = Integer.MAX_VALUE, maxMax = Integer.MIN_VALUE;
		int[] minStars = new int[13], maxStars = new int[13];
		for (int i = 2; i <= 12; i++) {
			minStars[i] = Integer.MAX_VALUE;
			maxStars[i] = Integer.MIN_VALUE;
		}
		
		for (int t = 0; t < TRIALS; t++) {
			Random random = new Random();
			int[] counts = new int[13];
			for (int r = 0; r < ROLLS; r++) 
				counts[random.nextInt(6) +  random.nextInt(6) + 2]++;
			int maxCount = Integer.MIN_VALUE;
			for (int i = 2; i <= 12; i++)
				if (counts[i] > maxCount)
					maxCount = counts[i];
			minMax = Math.min(minMax, maxCount);
			maxMax = Math.max(maxMax, maxCount);
			for (int i = 2; i <= 12; i++) {
				int stars = 60 * counts[i] / maxCount;
				minStars[i] = Math.min(minStars[i], stars);
				maxStars[i] = Math.max(maxStars[i], stars);
			}
		}
		System.out.printf("maxCount range: [%d, %d]\n", minMax, maxMax);
		System.out.printf("minStars: %s\n", Arrays.toString(minStars));
		System.out.printf("maxStars: %s\n", Arrays.toString(maxStars));
	}
	/*
	 * 		final int TRIALS = 100000, ROLLS = 10000;

maxCount range: [1489, 1836]
minStars: [0, 0, 7, 15, 24, 33, 42, 60, 42, 34, 24, 16, 7]
maxStars: [0, 0, 12, 25, 35, 46, 58, 60, 57, 47, 35, 24, 12]

maxCount range: [1513, 1834]
minStars: [0, 0, 7, 15, 24, 33, 42, 60, 42, 33, 24, 16, 6]
maxStars: [0, 0, 12, 25, 36, 46, 58, 60, 57, 47, 35, 25, 12]

Note: For 10000 rolls, the histogram is very unlikely to be perfectly symmetric, so test for asymmetry.
			*/

	public static void main(String[] args) {
//		boundsTest();
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		int[] counts = new int[13];
		for (int r = 0; r < rolls; r++) 
			counts[random.nextInt(6) +  random.nextInt(6) + 2]++;
		int maxCount = Integer.MIN_VALUE;
		for (int i = 2; i <= 12; i++)
			if (counts[i] > maxCount)
				maxCount = counts[i];
		System.out.printf("Max count: %d\n", maxCount);
		for (int i = 2; i <= 12; i++) {
			System.out.printf("%2d ", i);
			for (int s = 0; s < 60 * counts[i] / maxCount; s++)
				System.out.print("*");
			System.out.println();
		}
		in.close();
	}

}
