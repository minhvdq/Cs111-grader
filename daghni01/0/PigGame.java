import java.util.Random;
import java.util.Scanner;

public class PigGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int score1 = 0;
        int score2 = 0;
        int currentPlayer = 1;
        
        System.out.println("Welcome to the Game of Pig!");
        
        while (score1 < 100 && score2 < 100) {
            int score = (currentPlayer == 1) ? score1 : score2;
            int turnTotal = 0;
            
            System.out.println("\n--- Player " + currentPlayer + "'s Turn ---");
            System.out.println("Scores -> Player 1: " + score1 + " | Player 2 (Computer): " + score2);
            
            if (currentPlayer == 1) {
            
                boolean rolling = true;
                while (rolling && score + turnTotal < 100) {
                    System.out.print("Roll (r) or Hold (h)? ");
                    String choice = in.nextLine().trim().toLowerCase();
                    
                    if (choice.startsWith("r")) {
                        int roll = random.nextInt(6) + 1;
                        System.out.println("Rolled: " + roll);
                        if (roll == 1) {
                            System.out.println("Pig! You rolled a 1. Turn total lost.");
                            turnTotal = 0;
                            rolling = false;
                        } else {
                            turnTotal += roll;
                            System.out.println("Turn total: " + turnTotal);
                        }
                    } else if (choice.startsWith("h")) {
                        rolling = false;
                    }
                }
            } else {
             
                while (turnTotal < 20 && score + turnTotal < 100) {
                    int roll = random.nextInt(6) + 1;
                    System.out.println("Computer rolled: " + roll);
                    if (roll == 1) {
                        System.out.println("Computer rolled a 1! Turn total lost.");
                        turnTotal = 0;
                        break;
                    }
                    turnTotal += roll;
                    System.out.println("Computer turn total: " + turnTotal);
                }
            }
            
            score += turnTotal;
            System.out.println("Player " + currentPlayer + " new score: " + score);
            
            if (currentPlayer == 1) {
                score1 = score;
            } else {
                score2 = score;
            }
            
            currentPlayer = 3 - currentPlayer;
        }
        
        if (score1 >= 100) {
            System.out.println("\nCongratulations! Player 1 wins!");
        } else {
            System.out.println("\nGame over! Player 2 (Computer) wins!");
        }
    }
}