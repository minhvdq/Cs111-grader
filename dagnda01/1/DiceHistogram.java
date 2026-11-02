import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many rolls? ");
        int numRolls = scanner.nextInt();

        Random random = new Random();
        int[] counts = new int[13]; // indices 2 through 12

        for (int i = 0; i < numRolls; i++) {
            int die1 = random.nextInt(6) + 1;
            int die2 = random.nextInt(6) + 1;
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
            int numAsterisks = (int) Math.round(60.0 * counts[sum] / maxCount);
            System.out.printf("%2d ", sum);
            for (int k = 0; k < numAsterisks; k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}