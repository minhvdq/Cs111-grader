import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		int v1 = 0;
		int v2 = 0;
		int[] rollsPer = new int[11];
		for (int i = 0; i < rolls; i++) {
			v1 = rand.nextInt(6)+1;
			v2 = rand.nextInt(6)+1;
			
			rollsPer[v1 + v2 - 2]++;
			
		}
		int max = rollsPer[0];
		for (int i = 0; i < rollsPer.length; i++) {
			if (rollsPer[i] > max) {
				max = rollsPer[i];
			}
		}
		int ast = 0;
			System.out.println("Max count: "+ max);
			for (int i = 0; i < 11; i++) {
				System.out.print(i + 2 + " ");
				ast = (int) Math.round(60.0 * rollsPer[i] / max); 
				for (int j = 0; j < ast; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			
		
	}

}
