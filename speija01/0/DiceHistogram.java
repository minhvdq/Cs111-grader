import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		int [] counts = new int[13];
		
		for(int i = 0; i < rolls; i++) {
			int d1 = (int) (Math.random() * 6) + 1;
			int d2 = (int) (Math.random() * 6) + 1;
			int sum = d1 + d2;
			counts[sum]++;
		}
		
		int maxCount = 0;
		for (int sum = 2; sum<= 12; sum++) {
			if (counts[sum] > maxCount) {
				maxCount = counts[sum];
			}
		}
		System.out.println("Max count: " + maxCount);
		
		for (int sum = 2; sum <= 12; sum++) {
			int stars = (int) (60.0 * counts[sum] / maxCount);
			
			System.out.print(sum + " ");
			
			for (int i = 0; i < stars; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
		in.close();
	}

}
