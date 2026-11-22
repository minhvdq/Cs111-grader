import java.util.Scanner;

public class DiceHistogram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		
		System.out.print("How many rolls? ");
		int rolls = in.nextInt();
		
		// array size : 11
		int a1[] = new int [13];
		
		//int count = 0;
		for( int i =1; i <= rolls; i++) {
			int die1 =   (int)(Math.random() * (6 - 1 + 1)) + 1;
			int die2 =   (int)(Math.random() * (6 - 1 + 1)) + 1;
			int sum = die1 + die2;
			a1[sum]++;
			
		//	count += sum;
			//System.out.printf("SUM: %d\n",sum);
			//System.out.println(i);

		}
		int max=0;
			for(int i = 0; i < a1.length-1;i++ ) {
				
				if( a1[i] > max) {
					max = a1[i];
				}
			
			}
			System.out.println("Max count: "+ max);
		
			
		for (int i=2; i<a1.length;i++) {			
		    System.out.printf("%2d ", i);
			
				
			
				int ast=  Math.round(  ( (60 * a1[i]) / max));
				
				//System.out.print(ast);
				for (int a=0;a < ast;a++) {
					System.out.print("*");
				
			}
			System.out.println();
			
		}
		
		
		in.close();
	}

}
