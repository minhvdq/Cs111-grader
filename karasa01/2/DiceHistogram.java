
import java.util.Scanner;

public class DiceHistogram {
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	System.out.print("How many rolls? ");
        int rolls = input.nextInt();
        int[] counts = new int[13];
        for (int i = 0; i < rolls; i++) {
        	int die1 = (int)(Math.random() * 6) + 1;
            int die2 = (int)(Math.random() * 6) + 1;
            int sum = die1 + die2;
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
        	int stars = (int)Math.round(60.0 * counts[i] / maxCount);
        	System.out.printf("%2d ", i);
        	for (int j = 0; j < stars; j++) {
                System.out.print("*");
            }
        	System.out.println();
        }
        input.close();
    }
}
