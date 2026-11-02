import java.util.Random;
import java.util.Scanner;

/**
 * DiceHistogram
 */
public class DiceHistogram {

    public static int[] getRolls(int numRolls) {
        Random rand = new Random();
        int[] rolls = new int[13];
        int roll;
        for (int i = 0; i < numRolls; i++) {
            roll = rand.nextInt(6) + 1;
            roll += rand.nextInt(6) + 1;
            rolls[roll]++;
        }
        return rolls;
    }

    public static void printRolls(int[] rolls) {
        int maxCount = Integer.MIN_VALUE;
        for (int rollCount : rolls) maxCount = Math.max(rollCount, maxCount);
        System.out.println("Max count: " + maxCount);
        int asteriskFactor = maxCount / 60;
        for (int rollIndex = 2; rollIndex < rolls.length; rollIndex++) {
            System.out.printf("%2d ", rollIndex);
            for (int i = 0; i < rolls[rollIndex] / asteriskFactor; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many rolls? ");
        int simulatedRollCount = in.nextInt();
        in.close();
        int[] rolls = getRolls(simulatedRollCount);
        printRolls(rolls);
    }
}
