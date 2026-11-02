import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {
	// print 
	public static void print(int num) {
		for (int a = 0; a < num; a++)
			System.out.print("*");
		System.out.println();
	}

	public static int hash(int num, int max) {
		double hash = num * 60.0 / max;
		hash = Math.round(hash);
		return (int) hash;
	}

	public static void main(String[] args) {
		// get numrolls
		Scanner in = new Scanner(System.in);
		Random random = new Random();

		System.out.print("How many rolls? ");
		int numrolls = in.nextInt();
		// Initialize variables	
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		int seven = 0;
		int eight = 0;
		int nine = 0;
		int ten = 0;
		int eleven = 0;
		int twelve = 0;

		int roll1 = 0;
		int roll2 = 0;
		int sum = 0;
		// roll	
		for (int i = 0; i < numrolls; i++) {
			roll1 = 1 + random.nextInt(6);
			roll2 = 1 + random.nextInt(6);

			sum = roll1 + roll2;
			// increment for maxcount		
			if (sum == 2)
				two++;
			if (sum == 3)
				three++;
			if (sum == 4)
				four++;
			if (sum == 5)
				five++;
			if (sum == 6)
				six++;
			if (sum == 7)
				seven++;
			if (sum == 8)
				eight++;
			if (sum == 9)
				nine++;
			if (sum == 10)
				ten++;
			if (sum == 11)
				eleven++;
			if (sum == 12)
				twelve++;
		}
		// maxcount
		int max = two;
		int[] findmax = {two, three, four, five, six, seven, eight, nine, ten, eleven, twelve};
		for (int i = 0; i < 11; i++)
			if (findmax[i] > max)
				max = findmax[i];

		System.out.println("Max count: " + max);
		// get percentages	
		two = hash(two, max);
		three = hash(three, max);
		four = hash(four, max);
		five = hash(five, max);
		six = hash(six, max);
		seven = hash(seven, max);
		eight = hash(eight, max);
		nine = hash(nine, max);
		ten = hash(ten, max);
		eleven = hash(eleven, max);
		twelve = hash(twelve, max);




		// print	
		System.out.print(" 2 ");
		print(two);
		System.out.print(" 3 ");
		print(three);
		System.out.print(" 4 ");
		print(four);
		System.out.print(" 5 ");
		print(five);
		System.out.print(" 6 ");
		print(six);
		System.out.print(" 7 ");
		print(seven);
		System.out.print(" 8 ");
		print(eight);
		System.out.print(" 9 ");
		print(nine);
		System.out.print("10 ");
		print(ten);
		System.out.print("11 ");
		print(eleven);
		System.out.print("12 ");
		print(twelve);


		in.close();
	}
}
