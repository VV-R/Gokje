import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Gokje {
    public static void main(String[] args) {
        do {
            Game game = new Game(0, 15, 7);
            game.play();
            System.out.println("Would you like to play again? (y/n)");
        } while(UserInput.readYesOrNo());
        System.out.println("Ok, goodbye!");
    }
}

class UserInput {
    static Scanner scanner = new Scanner(System.in);

    public static boolean readYesOrNo() {
        while(true) {
            String input = scanner.nextLine();

            switch(input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Please enter 'y' or 'n'.");
                    break;
            }
        }
    }

    public static int readNumberFromUser() {
        while(true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (java.lang.NumberFormatException e) {
                System.out.println(String.format(
                    "%s is not a whole number.", input
                ));
            }
        }
    }
}

class Game {
    int minNum;
    int maxNum;
    int maxTries;
    int secret;
    ArrayList<Integer> tries;

    static Random random = new Random();

    Game(int minNum, int maxNum, int maxTries, int secret) {
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.maxTries = maxTries;
        this.secret = secret;
        tries = new ArrayList<Integer>();
    }

    Game(int minNum, int maxNum, int maxTries) {
        this(
            minNum, maxNum, maxTries,
            random.nextInt(maxNum - minNum + 1) + minNum
        );
    }

    private boolean numberWithinBounds(int num) {
        return num >= minNum & num <= maxNum;
    }

    private int getNextGuess() {
        int num;
        do {
            System.out.println(String.format(
                "Please enter a number from %d thru %d.", minNum, maxNum
            ));
            num = UserInput.readNumberFromUser();
        } while(!numberWithinBounds(num));
        return num;
    }


    private void printRun() {
        System.out.println("Here is an overview of your run:");
        for(int gues : tries) {
            System.out.println(gues);
        }
    }

    public void play() {
        while(true) {
            int triesLeft = maxTries - tries.size();
            System.out.println(String.format(
                "You have %d guess%s left.",
                triesLeft, triesLeft != 1 ? "es" : "")
            );
            int guess = getNextGuess();

            if (guess == secret) {
                System.out.println("You guessed it!");
                tries.add(guess);
                printRun();
                return;
            }

            boolean contains = tries.contains(guess);
            tries.add(guess);

            if (contains) {
                System.out.println(String.format(
                    "You have already tried %d!", guess
                ));
            }

            if (tries.size() == maxTries) {
                System.out.println(String.format(
                    "You lost! The secret number was: %d.", secret)
                );
                printRun();
                return;
            }

            if (!contains) {
                System.out.println("That's not it!");
            }
        }
    }
}
