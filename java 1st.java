import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");

        boolean playAgain = true;
        int totalAttempts = 0;
        int roundsWon = 0;

        while (playAgain) {
            
            int secretNumber = random.nextInt(100) + 1;

            
            int maxAttempts = 10;
            int attempts = 0;

            System.out.println("\nRound " + (roundsWon + 1));

            while (attempts < maxAttempts) {
            

                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();

             
                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the number " + secretNumber + " correctly in " + (attempts + 1) + " attempts.");
                    roundsWon++;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attempts++;
            }

            
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + secretNumber + ".");
            }

            totalAttempts += attempts;

           
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }

     
        System.out.println("\nGame Over! You won " + roundsWon + " round(s) with an average of " + (totalAttempts / (roundsWon + 1)) + " attempts per round.");

        
    }
}
