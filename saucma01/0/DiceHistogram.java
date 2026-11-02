import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		
		int[] counts = new int[13];
		
		Random random = new Random();
		for (int i = 0; i < rolls; i++) {
			
			int dice1 = random.nextInt(6) + 1;
			int dice2 = random.nextInt(6) + 1;
			int sum = dice1 + dice2;
			
			counts[sum]++;	
		}
		
		int maxCount = 0;
		for (int i = 2; i <= 12; i++) {
			if (counts[i] > maxCount) {
				maxCount = counts[i];
			}
		}
		
		System.out.println("Max count: " + maxCount);
		
		for (int i = 2; i <= 12; i++) {
			int asterisks = (int) Math.round(60.0 * counts[i] / maxCount);
			
			System.out.print(i + " ");
			for (int j = 0; j < asterisks; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		in.close();
	}
}
