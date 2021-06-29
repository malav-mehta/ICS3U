// Scanner class allows computer to get data from the IO stream
import java.util.Scanner;

public class Main {
    /*
   Create a simple game of Rock,Paper,Scissors
   -Accept  - R, P or S for user input
   -use random to generate a computer R, P or S
   - R beats S, S beats P, P beats R
   -check the user input against the computer response and determine a winner or tie
   -allow the game to run 3 times and determine an overall winner
   -Problem solve to ensure reasonable outcomes  - it will not pass the tests, as I want
   to read your code


   Check for:
   - use of loops and loop control variables
   - keeps a cumulative Score
   - valid error checks
   - easy to follow user interface

   */

    public static void main(String[] args) {
        /*
         * DECLARING AND INITIALIZING VARIABLES
         * This is done at the beginning of the program for organization.
         */

        // Setting up a scanner to get user input
        Scanner userInput = new Scanner(System.in);

        // Used to determine input
        // Input is stored as an integer for easy checking, and so that computer Input does not have to be generated as an integer and then reconverted into a string
        int playerInput = -1, computerInput;

        // Holds number of total rounds to play
        int rounds = -1;

        // This string actually holds the raw input from the user
        String tempPlayerInput;

        // Holding the player name
        String playerName;

        // Drawing ROCK, PAPER and SCISSORS
        String rock = " XXXXXXX\nXX     XX\nX       X\nXX     XX\n XXXXXXX";
        String paper = "+-----+\n|     |\n|     |\n|     |\n+-----+";
        String scissors = "+---+\n+---+XXXXXX\n          XXXXXXX\n+---+XXXXXX\n+---+";

        // Used to determine the validity of playerInput
        boolean validInput;
        boolean quitGame;

        // This integer hold the cumulative score for each player (user and computer)
        // These integers will modified as needed, when either the player or the computer wins a game
        int playerScore = 0;
        int computerScore = 0;

        /*
         * PROCESSING and GETTING INPUT
         * Since input is received through a loop, it is processed when it is received
         * As such, the processing and the receiving of input are done closely together
         */

        // Printing the rules of the game at the start of the game
        System.out.println("Hi! Welcome to the ROCK-PAPER-SCISSORS game. Here are the rules\n- The game is player best out of the number of rounds you input\n- Enter and 'R' for ROCK, 'S' for SCISSORS and 'P' for PAPER\n- After you input, the computer will play its turn.\nGOOD LUCK!" );

        // Asking for name (as a UI feature: the name is irrelevant)
        System.out.print("Enter your name: ");
        playerName = userInput.nextLine();

        // DO-WHILE LOOP used to ensure that the game is played at least once. Playing the game more than once is allowed at the end of the program.
        do {
            // First getting the number of rounds the user wants this game, with TRY-CATCH for error checking
            System.out.print("Number of rounds (1 to 15): ");
            try {
                rounds = userInput.nextInt();
            } catch (Exception e) {
                // If there is an error, only three rounds will be played
                rounds = 3;
                System.out.println("Your input was not valid. 3 rounds will be played");
            } finally {
                // Or if the inputted round amount does not make sense, then rounds will be set to 3
                if (rounds <= 0 || rounds > 15) {
                    rounds = 3;
                    System.out.println("Your input was not in the range. 3 rounds will be played");
                }
            }

            System.out.println(rounds + " rounds will be played.");

            // This is done since to ensure the next nextLine function works properly.
            userInput.nextLine();

            // FOR-LOOP to get the user input and then process it as necessary
            for (int round = 1; round <= 3; round++) {
                System.out.println("\nStarting ROUND " + round + ". Get READY!");

                // PROMPTING PLAYER FOR INPUT WHILE THE INPUT IS NOT VALID (USE OF DO-WHILE LOOP)
                do {
                    System.out.println(playerName + " it's your turn. What do you want to play? (R/S/P, read rules for help)");
                    System.out.print("> ");

                    // Getting the user input
                    tempPlayerInput = userInput.nextLine();
                    System.out.println();
                    validInput = true;

                    // INPUT VALIDATION (input must be R, S or P regardless of the case);
                    // R = 0, S = 1 and P = 2 (easier to compare integers than Strings, both efficiency wise and programming wise
                    if (tempPlayerInput.equalsIgnoreCase("r")) {
                        System.out.println(playerName + " played ROCK.\n" + rock);
                        playerInput = 0;
                    }
                    else if (tempPlayerInput.equalsIgnoreCase("s")) {
                        System.out.println(playerName + " played SCISSORS.\n" + scissors);
                        playerInput = 1;
                    }
                    else if (tempPlayerInput.equalsIgnoreCase("p")) {
                        System.out.println(playerName + " played PAPER.\n" + paper);
                        playerInput = 2;
                    }

                    // OTHERWISE INPUT IS INVALID
                    // Since the input is invalid, VALID INPUT is set to false, and the user is allowed to enter a valid input (until a valid input is entered)
                    else {
                        System.out.println("INVALID INPUT. Please try again!");
                        validInput = false;
                    }
                } while (!validInput);

                // GENERATE COMPUTER INPUT using Math.random(): this input must be between 0 and 2 (inclusive)
                computerInput = (int) Math.round(Math.random() * 2);

                // Spacing for formatting
                System.out.println();

                // Letting the user know what the computer picked
                if (computerInput == 0) System.out.println("Computer played ROCK.\n" + rock);
                else if (computerInput == 1) System.out.println("Computer played SCISSORS.\n" + scissors);
                else System.out.println("Computer played PAPER.\n" + paper);

                // Letting user know that the winner is being determined now
                System.out.println("\nDETERMINING WINNER...");

                // DETERMINING WINNER OR TIE

                // Tie when the two integer inputs are equivalent
                if (playerInput == computerInput) System.out.println("TIE. Nobody wins this round.");
                else {
                    // Player wins based on ROCK-PAPER-SCISSORS logic and assigned integer values
                    if ((playerInput == 0 && computerInput == 1) || (playerInput == 1 && computerInput == 2) || (playerInput == 2 && computerInput == 0)){
                        System.out.println(playerName + " won this round. Good job.");
                        playerScore++;
                    }

                    // If player doesn't win, then computer must win since input validation was already checked for
                    else {
                        System.out.println("Computer won this round. Better luck next time.");
                        computerScore++;
                    }

                    // NOTE: player and computer scores are updated accordingly
                }

                // This is to check if additional rounds are necessary: say there are 3 rounds, if one player has won 2, the third round doesn't matter
                // Similarly, if there are 8 rounds, and one player has won 5, then additional 3 rounds are not necessary to be run
                // By setting round to ROUNDS + 1, the FOR-LOOP is not run again
                if (rounds % 2 != 0 && (playerScore == (rounds + 1) / 2 || computerScore == (rounds + 1) / 2)) round = rounds + 1;
                else if (playerScore == (rounds + 1) / 2 + 1 || computerScore == (rounds + 1) / 2 + 1) round = rounds + 1;
            }

            // At the end of the for loop, we determine the overall winner
            System.out.println("\n\nAll rounds are over.");
            System.out.print("The final result: ");

            // Simple selection to determine who had the highest score, or if there was a tie
            if (playerScore == computerScore) System.out.print(playerName + " ties with the Computer.");
            else if (playerScore > computerScore) System.out.print(playerName + " beats the Computer.");
            else System.out.print("Computer beats " + playerName + ".");

            // As previously mentioned, the game allows for multiple games
            // This segment determines whether an additional game will be played

            quitGame = true;
            System.out.print("\n\nWould you like to player another game? (y/N): ");

            // ONLY if the input is Y (or y) do we run the game again: otherwise, we quit the do-while loop for the game itself
            if (userInput.nextLine().equalsIgnoreCase("y")) {
                quitGame = false;
                System.out.println("Clearing all scores...");
                System.out.println("Starting new game...\n\n");
                playerScore = 0;
                computerScore = 0;
            }
        } while (!quitGame);

        // Once the player is outside the main loop, a goodbye message is shown
        System.out.println("Thank you for playing! Have a great day!");
    }
}
