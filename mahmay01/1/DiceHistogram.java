import java.util.Scanner;
import java.util.Random;
public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		Random rand = new Random();
		
		int counting []= new int [13];

		for (int i = 1; i<= rolls; i++) {
			int die1 = rand.nextInt(6)+1;
			int die2 = rand.nextInt(6)+1;
			
			int sum = die1+die2;
			counting [sum] ++;
		}
		
		int max=0;
		for (int i =2; i<= 12; i++) {
			if (counting[i] > max) {
				 max = counting[i];
			} 
		}
		System.out.printf("Max count: %d\n", max);

        
		for (int i = 2; i <= 12; i++) {
            int star = (int) Math.round(60.0 * counting[i] / max);

            System.out.printf("%2d ", i);
            for (int j = 0; j < star; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

		
		in.close();
		
		
	}

}
