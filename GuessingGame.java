import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        int score = 0;

        do {
            playGame(scanner);
            score++;
            System.out.println("Would you like to play again? (yes/no)");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Thank you for playing! Your score: " + score + " rounds won.");
    }

    private static void playGame(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("I have generated a number between 1 and 100. Try to guess it!");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                hasGuessedCorrectly = true;
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The number was " + numberToGuess + ".");
        }
    }
}
