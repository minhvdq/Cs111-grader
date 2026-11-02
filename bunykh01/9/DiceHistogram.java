import java.util.Random;
import java.util.Scanner;

public class DiceHistogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int[] overall = new int[11];
        System.out.print("How many rolls? ");
        int rolls = scanner.nextInt();
        
        
        for (int i =0; i<rolls; i++) {
            int sum = 0;
            int dice1= random.nextInt(6)+1;
            int dice2= random.nextInt(6)+1;
            sum+=dice1;
            sum+=dice2;
            
            overall[sum-2]++;

        }
        int max = overall[0];
        for (int j=0; j<overall.length; j++){
            
            if (overall[j]>max){
                max = overall[j];
            }
        }
        System.out.println("Max count: " + max);
        
        for (int f=0; f<overall.length; f++){
            int stars = (int) Math.round(60.0 * overall[f] / max);
           
            System.out.printf("%2d ", f + 2);
            
            for (int k=0; k<stars; k++){
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }

    
            
}



