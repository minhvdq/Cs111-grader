import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		Random random = new Random();
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
            int stars = max == 0 ? 0 :
                (int) Math.round(60.0 * counts[i] / max);
            System.out.printf("%2d ", i);
            for (int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        in.close();
	}

}
