import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {
	public static int rollFinal() {
		Random random = new Random();
		int roll1 = random.nextInt(6) + 1;
		int roll2 = random.nextInt(6) + 1;

		return roll1 + roll2 ;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		int timesrolled =0 ;
		int[] count =new int[12];
		int max = count[0];
		while(timesrolled < rolls) {
			int roll = rollFinal();
			if (roll == 1) count[0]++;
			if (roll == 2) count[1]++;
			if (roll == 3) count[2]++;
			if (roll == 4) count[3]++;
			if (roll == 5) count[4]++;
			if (roll == 6) count[5]++;
			if (roll == 7) count[6]++;
			if (roll == 8) count[7]++;
			if (roll == 9) count[8]++;
			if (roll == 10) count[9]++;
			if (roll == 11) count[10]++;
			if (roll == 12) count[11]++;
			timesrolled++ ;
		}
		for(int i = 0 ; i <12; i++) {
			if(max < count[i]) {
				max = count[i];
				
			}
		}
		System.out.println("Max count: " + max);
		int numberOfAsterix = 0 ; 
		for (int i = 1 ; i <12; i++) {
			int z = i +1 ;
			System.out.printf("%2d ",z );
			numberOfAsterix = (count[i] * 60) / max;
			for (int j = 0 ; j < numberOfAsterix ; j++) {
				System.out.print("*");	
			}
			System.out.println();
		}
		in.close();
	}

}
