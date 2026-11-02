import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random random = new Random();
		
		System.out.print("How many rolls? ");
		int numRolls = in.nextInt();
		
		int[] counts = new int[13];
		int maxCount = 0;
		
		for (int i = 0; i < numRolls; i++) {
			int die1 = random.nextInt(6) + 1;
			int die2 = random.nextInt(6) + 1;
			int sum = die1 + die2;
			counts[sum]++;
			
			if (counts[sum] > maxCount) {
				maxCount = counts[sum];
			}
		}
	 System.out.println("Max count: " + maxCount);
	 
	 for (int sum = 2; sum <= 12; sum++) {
		 if(sum < 10) {
			 System.out.print(sum + "  ");
		 }
		 else {
			 System.out.print(sum + " ");
		 }
		 int numAsterisks = 0;
		 if (maxCount > 0) {
			 numAsterisks = (int) Math.round(60.0 * counts[sum] / maxCount);
		 }
		 for (int j = 0; j< numAsterisks; j++) {
			 System.out.print("*");
		 }
		 System.out.println();
	 }
	 in.close();
	 
	 
	}

}
