import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Random random = new Random();
		
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		
		int[] counts = new int[13];
		
		for(int i = 0; i < rolls; i++) {
			int die1 = random.nextInt(6) + 1;
			int die2 = random.nextInt(6) + 1;
			int sum = die1 + die2;
			
			counts[sum]++;
		}
		
		int maximum = counts[2];
		for(int sum = 3; sum <= 12; sum++) {
			if (counts[sum] > maximum) {
				maximum = counts[sum];
			}
		}
		System.out.println("Max count: " + maximum);
		
		for(int sum = 2; sum <= 12; sum++) {
			int stars = (int) Math.round(60.0 * counts[sum] / maximum);
			
			System.out.printf("%2d", sum);
			
			for(int i = 0; i < stars; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		in.close();
	}

}
