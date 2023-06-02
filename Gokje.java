import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Gokje {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("hello gokje");

        Game(0, 15, 7);
    }

    private static int _readNumberFromUser() {
        while(true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (java.lang.NumberFormatException e) {
                System.out.println(String.format("%s is not a whole number.", input));
            }
        }
    }

    public static void Game(int low, int high, int maxTries) {
        int secret = random.nextInt(high - low + 1) + low;
        int guess;
        ArrayList<Integer> guesses = new ArrayList<Integer>();
        while(true) {
            System.out.println(String.format(
                "Please guess a number between %d and %d", low, high
            ));

            guess = _readNumberFromUser();
            guesses.add(guess);

            if (guess == secret) {
                System.out.println("You guessed it!");
                return;
            }
            if (guesses.size() == maxTries) {
                System.out.println(String.format("You lost! The secret number was: %d.", secret));
                return;
            }
        }

    }
}
