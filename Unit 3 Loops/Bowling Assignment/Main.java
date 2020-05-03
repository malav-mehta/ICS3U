/**
 * Bowling Tracker (in JAVA)
 * This program tracks a bowling game and calculates the score
 * Loop Assignment for ICS3U
 *
 * @author: Malav Mehta
 * @teacher: Mr. Benjamin Hudson
 * @course: ICS3U-03
 * @date: Saturday, December 7, 2019
 */

// Scanner class will be used for accessing user input from the IO stream
import java.util.Scanner;

// Arrays class will be used to sort the final scores array (for the leader board)
import java.util.Arrays;

// Math class will be used for generating random numbers (for random scores for the 4+ level)
import java.lang.Math;

public class Main {
    /*
     * DECLARING AND INITIALIZING VARIABLES
     * Note: Variables are declared outside of the 'main' method
     *       Rather, variables are declared class-wide so that they are accessible from all methods
     * Variables are private (they do not need to be accessible outside this class) and static (they are accessed from static methods)
     */

    // Class-scoped array containing all possible error messages.
    private static String[] errorMessages = {
            "INVALID INPUT.",
            "ERROR.",
            "Restart program to try again.",
            "Try inputting something else below.",
            "You knocked down more than 10 pins.",
    };

    // Setting up a Scanner for user input.
    private static Scanner userInput = new Scanner(System.in);

    // Player count is used to initialize other variables, as well as for FOR-LOOPS
    private static int playerCount;

    /*
     * Amount of frames for the game is a VARIABLE so that it can be changed
     * Amount of frames also determine the size of some ARRAYS
     */
    private static int frames = 10;

    // The offset determines which values, after a strike or a spare, are used for the final score
    private static int finalScore, scoreIndex;

    // WINNING PLAYER holds the score of the winning player
    private static int winningPlayer;

    // BALL POST SPARE is a temporary type variable used for ACCURATE NUMBER GENERATION
    private static int ballPostSpare = 0;

    // The balls array contains the pin-value for each thrown ball, that is used for some parts of PROCESSING
    private static int[] balls = new int[3];

    // The scores array is updated after each frame to generate the scoreboard
    private static StringBuilder gameResult = new StringBuilder();
    private static String[] scores = new String[3];

    // The SCOREBOARD and LEADER-BOARD are stored in mutable arrays, edited after each frame
    private static String[] scoreBoard, leaderBoard;
    private static String prevBoard, prevScore;

    // The PLAYER NAMES and POINTS and SCORES arrays hold the information and scores for each player
    private static String[] playerNames;
    private static int[][] playerPoints;
    private static int[] finalScores, winners, sortedScores, playerAverages;

    // Boolean RANDOM and ACCURATE INPUT are used to determine which input method will be used for the game
    private static boolean randomInput = false;
    private static boolean accurateInput = false;

    /**
     * This method is used to get INTEGER input, by validating input
     * (Created since this process is repeated multiple times)
     *
     * @param prompt: the prompt presented to the user to ask for and retrieve the desired input
     * @param minValue: the minimum integer value (for validation)
     * @param maxValue: the maximum integer value (for validation)
     * @return returns the validated integer input
     */
    private static int getIntegerInput(String prompt, int minValue, int maxValue) {
        // Integer input that will be returned at the end of the method
        int integerInput = -1;

        // Boolean determining whether the input was valid
        boolean validInput = false;

        /*
         * TRY-CATCH allows for error checking beyond the value itself
         * It accounts for invalid input (i.e. STRING/LONG instead of INT)
         */
        try {
            // WHILE-LOOP prompts for input until a valid VALUE is inputted
            while (!validInput) {
                System.out.print(prompt);
                integerInput = userInput.nextInt();

                // The inputted MIN-MAX value are used to determine valid VALUES
                if (integerInput >= minValue && integerInput <= maxValue){
                    validInput = true;
                } else {
                    System.err.printf("%s %s\n\n", errorMessages[0], errorMessages[3]);
                }
            }
        } catch (Exception error) {
            /*
             * In the case there is a TYPE error, or another unexpected error, the CATCH block is called
             * CATCH displays the corresponding error message, and continues the program
             */
            System.err.printf("%s %s\n\n", errorMessages[1], errorMessages[3]);
            userInput.nextLine();

            // The input is continuously prompted, recursively, until valid input is entered
            integerInput = getIntegerInput(prompt, minValue, maxValue);
        }
        return integerInput;
    }

    /**
     * This method is used to generate a random number within the given constrains
     * It also prints the generated input in proper format, so the user can follow the UI
     *
     * @param playerName: the name of the player, used for output of generated input
     * @param minValue: minimum random value
     * @param maxValue: maximum random value
     * @param ballIndex: the index of the ball is used to ensure that more than 10 pins are not knocked down at once
     * @return returns the randomly generated integer within the given constraints
     */
    private static int getRandomInput(String playerName, int minValue, int maxValue, int ballIndex) {
        // Range will be used for RNG
        int rangeValue = maxValue - minValue + 1;

        // Generating RN
        int randomInteger = (int) Math.round(Math.random() * rangeValue) + minValue;

        // This is done to make sure that now more than 10 pins are knocked down
        if (ballIndex == 1) {
            // WHILE-LOOP generates a random number until it is valid
            while (balls[0] + randomInteger > 10) {
                randomInteger = (int) Math.round(Math.random() * rangeValue) + minValue;
            }
        }

        while (!(randomInteger >= 0 && randomInteger <= 10)) {
            randomInteger = (int) Math.round(Math.random() * rangeValue) + minValue;
        }

        // Printing to the UI for the user to keep track of what numbers were generated
        System.out.printf("Enter %s's score for ball %d: %d\n", playerName, ballIndex + 1, randomInteger);
        return randomInteger;
    }

    /**
     * This method does the entire generation of accurate random points ahead of the actual game
     * This is done as all of the scores are generated in one go
     *
     * NOTE: THIS METHOD ONLY WORKS FOR PLAYER AVERAGES RANGING FROM 0 to 200
     * Any player average above 200 will result in a final score of 270
     * Future iteration of this program will fix this error
     *
     * @param playerIndex: used to access all the arrays under the current player's index
     */
    private static void generatePoints(int playerIndex) {
        // Storing player average and frame average to limit array accesses
        int playerAverage = playerAverages[playerIndex];
        int frameAverage;

        // The FRAME SCORES holds the scores per frame, that will later be divided
        int[] frameScores = new int[frames];

        // Calculate the FRAME AVERAGE, and assign a score for each frame
        for (int frame = 1; frame <= frames; frame++) {
            // The player average is updated every time, and thus the frame average is also slightly different
            frameAverage = Math.round(playerAverage / (float) (frames - frame + 1));

            // There is randomness in the frame average, as there is an offset to it
            frameAverage += (int) Math.pow(-1, Math.round(Math.random())) * Math.round(Math.random());
            frameAverage = Math.min(frameAverage, 30);

            playerAverage -= frameAverage;
            frameScores[frame - 1] = frameAverage;
        }

        // Used to check if an array element has already been defined, and if so, it should not be defined (see uses below)
        Arrays.fill(playerPoints[playerIndex], -1);

        // Generate balls that make the frame average work for each frame
        for (int frame = 0; frame < frames; frame++) {
            frameAverage = frameScores[frame];

            // For less than 10, any 2 balls adding to the right average work
            if (frameAverage < 10) {
                if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 2] = (int) Math.round(Math.random() * frameAverage);
                if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = Math.max(frameAverage - playerPoints[playerIndex][(frame + 1) * 2 - 2], 0);
            }

            // If 10, generate spares
            else if (frameAverage == 10) {
                if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 2] = (int) Math.round(Math.random() * 9);
                if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = Math.max(10 - playerPoints[playerIndex][(frame + 1) * 2 - 2], 0);
                if (frame != 9) {
                    if (frameScores[frame + 1] <= 20)
                        playerPoints[playerIndex][(frame + 2) * 2 - 2] = 0;
                }
            }

            // Logic follows through for all frame averages
            else if (frameAverage < 20) {
                if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 2] = (int) Math.round(Math.random() * 9);
                if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = Math.max(10 - playerPoints[playerIndex][(frame + 1) * 2 - 2], 0);
                if (frame != 9)
                    playerPoints[playerIndex][(frame + 2) * 2 - 2] = Math.min(frameAverage - 10, frameScores[frame + 1]);
            } else if (frameAverage == 20) {
                if (frame != 9) {
                    if (frameScores[frame + 1] > 20) {
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 2] = (int) Math.round(Math.random() * 9);
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 1] = Math.max(10 - playerPoints[playerIndex][(frame + 1) * 2 - 2], 0);

                        if (playerPoints[playerIndex][(frame + 2) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 2) * 2 - 2] = 10;
                        if (playerPoints[playerIndex][(frame + 2) * 2 - 1] == -1)
                            playerPoints[playerIndex][(frame + 2) * 2 - 1] = 0;
                    } else {
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 2] = 10;
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 1] = 0;
                    }
                } else {
                    if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                        playerPoints[playerIndex][(frame + 1) * 2 - 2] = 10;
                    if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                        playerPoints[playerIndex][(frame + 1) * 2 - 1] = 5;
                }
            } else if (frameAverage < 30) {
                if (frame != 9) {
                    if (frameScores[frame + 1] > 20) {
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 2] = (int) Math.round(Math.random() * 9);
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 1] = Math.max(10 - playerPoints[playerIndex][(frame + 1) * 2 - 2], 0);
                        playerPoints[playerIndex][(frame + 2) * 2 - 2] = 10;
                        playerPoints[playerIndex][(frame + 2) * 2 - 1] = 0;
                    } else if (frameScores[frame + 1] > 10) {
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 2] = 10;
                        if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                            playerPoints[playerIndex][(frame + 1) * 2 - 1] = 0;
                    }
                } else {
                    if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                        playerPoints[playerIndex][(frame + 1) * 2 - 2] = 10;
                    if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                        playerPoints[playerIndex][(frame + 1) * 2 - 1] = 10;
                }

                if (frame == 9 && playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1) {
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = 10;
                }

            } else {
                if (playerPoints[playerIndex][(frame + 1) * 2 - 2] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 2] = 10;
                if (playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1)
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = 0;

                if (frame != 9) {
                    if (playerPoints[playerIndex][(frame + 2) * 2 - 2] == -1)
                        playerPoints[playerIndex][(frame + 2) * 2 - 2] = 10;
                    if (playerPoints[playerIndex][(frame + 2) * 2 - 2] == -1)
                        playerPoints[playerIndex][(frame + 2) * 2 - 1] = 0;

                    if (frame != 8) {
                        if (playerPoints[playerIndex][(frame + 3) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 3) * 2 - 2] = 10;
                        if (playerPoints[playerIndex][(frame + 3) * 2 - 2] == -1)
                            playerPoints[playerIndex][(frame + 3) * 2 - 1] = 0;
                    }
                } else {
                    playerPoints[playerIndex][(frame + 1) * 2 - 1] = 10;
                }
            }

            if (frame == 9 && playerPoints[playerIndex][(frame + 1) * 2 - 1] == -1) {
                playerPoints[playerIndex][(frame + 1) * 2 - 1] = 10;
            }
        }
    }

    /**
     * This method is used in order to generate accurate input for a bowling game
     * The method takes into account a player's average bowling score, and based on this, generates random but accurate predicted scores for an entire sample game
     *
     * @param playerName: player name, used for printing out score
     * @param playerIndex: player index used to access arrays containing player information
     * @param ballIndex: the index of the ball, used for validating some generated numbers
     * @param frameIndex: special cases are done for certain frames
     * @return returns an accurate score for a certain ball in a certain frame
     */
    private static int getAccurateInput(String playerName, int playerIndex, int ballIndex, int frameIndex) {
        int accurateInteger = 0;

        // Return accurate integer, since player points array is already filled
        accurateInteger = Math.max(playerPoints[playerIndex][frameIndex * 2 + ballIndex - 2], 0);
        if (frameIndex == 10 && ballIndex == 2)
            accurateInteger = Math.max(Math.min(playerAverages[playerIndex] - playerPoints[playerIndex][frames * 2] - playerPoints[playerIndex][frames * 2 - 2] - playerPoints[playerIndex][frames * 2 - 1], 10), 0);

        System.out.printf("Enter %s's score for ball %d: %d\n", playerName, ballIndex + 1, accurateInteger);

        return accurateInteger;
    }

    /**
     * The code itself only calls this method when an input is required for a ball
     * This is done so that the code and logic itself is only written once
     * By doing so, this method determines the source of input, so RANDOM and ACCURATE INPUT can be generated
     *
     * @param playerIndex: used to retrieve player information
     * @param ballIndex: used for special cases for certain balls
     * @param frameIndex: used for special cases for certain frames
     * @return returns the final generated input based on the source of input (as called from this function)
     */
    private static int getNextBall(int playerIndex, int ballIndex, int frameIndex) {
        // The INTEGER INPUT that will be returned
        int integerInput;

        // IF the user did want RANDOM GENERATION, then the user is prompted for the input
        if (!randomInput) {
            integerInput = getIntegerInput(String.format("Enter %s's score for ball %d: ", playerNames[playerIndex], ballIndex + 1), 0, 10);

            // Check done to ensure that more than 10 pins are not knocked down
            if (ballIndex == 1 && !(frameIndex == frames)) {
                while (balls[0] + integerInput > 10) {
                    System.err.printf("%s %s\n", errorMessages[1], errorMessages[4]);
                    integerInput = getIntegerInput(String.format("Enter %s's score for ball %d: ", playerNames[playerIndex], ballIndex + 1), 0, 10);
                }
            }
        }

        // OTHERWISE, RANDOM INPUT is TRUE, but we check if the input should be generated accurately
        else if (!accurateInput) {
            integerInput = getRandomInput(playerNames[playerIndex], 0, 10, ballIndex);
        }

        // FINALLY, both RANDOM and ACCURATE INPUT are true, so the input is generated randomly and accurately
        else {
            integerInput = getAccurateInput(playerNames[playerIndex], playerIndex, ballIndex, frameIndex);
        }
        return integerInput;
    }

    public static void main(String[] args) {
        playerCount = getIntegerInput("Welcome to CB Bowling\nEnter number of bowlers: ", 1, 4);

        // This calls is done to ensure that the next userInput.nextLine() works correctly
        userInput.nextLine();

        // Here, the program check how the input will be taken: from the player, RANDOMLY or RANDOMLY and ACCURATELY
        System.out.print("Would you like random number generation (y/N)?\n> ");
        if (userInput.nextLine().equalsIgnoreCase("y")) randomInput = true;
        if (randomInput) {
            System.out.print("Would you like accurate random number generation (y/N)?\n> ");
            if (userInput.nextLine().equalsIgnoreCase("y")) accurateInput = true;
        }


        // As mentioned earlier, all ARRAYS are initialized using player count so that they have the right length
        scoreBoard = new String[3 * playerCount + 2];
        leaderBoard = new String[playerCount + 2];

        scoreBoard[0] = "Frame   |";
        scoreBoard[1] = "---------";
        leaderBoard[0] = String.format(" %-4s | %-8s | %-6s ", "Rank", "Name", "Score");
        leaderBoard[1] = "-------------------------";

        playerNames = new String[playerCount];
        playerPoints = new int[playerCount][frames * 2 + 2];
        finalScores = new int[playerCount];
        sortedScores = new int[playerCount];
        winners = new int[playerCount];
        playerAverages = new int[playerCount];

        // Defining the PLAYER NAMES array and finishing column 1 of the SCOREBOARD array
        for (int player = 1; player <= playerCount; player++) {
            // Prompting for and adding the player name to the player information
            System.out.print("Enter bowler " + player + ": ");
            playerNames[player - 1] = userInput.nextLine();

            // Appending player information to the scoreboard
            scoreBoard[3 * player - 1] = "Player  |";
            scoreBoard[3 * player + 1] = scoreBoard[1];
            if (playerNames[player - 1].length() <= 8) scoreBoard[3 * player] = String.format("%-8s|", playerNames[player - 1]);
            else scoreBoard[3 * player] = String.format("%-8s|", playerNames[player - 1].substring(0, 8));

            // IF ACCURATE INPUT was turned on, then the average score for each player is also required to be inputted
            if (accurateInput) {
                playerAverages[player - 1] = getIntegerInput("Enter this player's average score: ", 0, frames * 30);
                System.out.println();

                // The entire logic begin the accurate input generation is done in this method
                generatePoints(player - 1);

                // Again, nextLine() must be called so that the current input token is consumed
                userInput.nextLine();
            }

            // Setting total score for each player to 0, so += can be called directly

            playerPoints[player - 1][frames * 2] = 0;
        }

        /*
         * PRE-PROCESSING INPUT
         * This part will consist of getting input for each frame and correctly sorting it to the correct variables
         * Actual processing (where the winner is determined) will be done later
         */

        // 1st FOR-LOOP iterating through each FRAME
        for (int frame = 1; frame <= frames; frame++) {
            // Changing the scoreboard for the current frame
            System.out.println("\nFrame " + frame);
            scoreBoard[0] += String.format("%4d  |", frame);
            scoreBoard[1] += "-------";

            // 2nd FOR-LOOP iterating through each PLAYER
            for (int player = 0; player < playerCount; player++) {
                /*
                 * Refer to each score in the PLAYER POINTS array as an element in BALLS for the player's turn
                 * This makes it easier to keep track of all the operations and comparisons being processed
                 */
                balls[0] = -1;
                balls[1] = -1;
                balls[2] = -1;

                // 3rd FOR-LOOP iterating through each BALL (2 balls, until FRAME = FRAMES)
                for (int ball = 1; ball <= 2; ball++) {
                    balls[ball - 1] = getNextBall(player, ball - 1, frame);

                    // Check for a strike for when BALL equals 1 (1st ball)
                    if (ball == 1 && balls[0] == 10) break;
                }

                playerPoints[player][frame * 2 - 2] = balls[0];
                playerPoints[player][frame * 2 - 1] = balls[1];

                /*
                 * A strike occurs when BALLS[0] is 10, or BALLS[1] is 11
                 * Check for strike first (saves processes)
                 */
                if (balls[0] == 10) {
                    // Notify user of a strike
                    System.out.println(playerNames[player] + " has a strike!");

                    // If this strike occurs in the last frame, then there must be two more balls
                    if (frame == frames) {
                        balls[1] = getNextBall(player, 1, frame);
                        balls[2] = getNextBall(player, 2, frame);

                        /*
                         * The format for a strike in the final frame is 'X|BALLS[1]|BALLS[2]';
                         * BALLS[1] and BALLS[3] are merged here rather than later so that no additional code has to be written for the actual scoreboard append (later)
                         */
                        scores[0] = "X";
                        scores[1] = String.format("%s|%s", balls[1] == 10 ? "X" : balls[1], balls[2] == 10 ? "X" : balls[2]);
                    } else {
                        // The format for a strike is '[BLANK]|X', so the scores array is initialized accordingly
                        scores[0] = "";
                        scores[1] = "X";
                    }
                }

                else {
                    // Set the SCORES array to equal the string version of the BALLS array
                    scores[0] = Integer.toString(balls[0]);
                    if (!(balls[0] + balls[1] == 10)) scores[1] = Integer.toString(balls[1]);

                    // In the case of a spare
                    else {
                        // The format for a spare is '[SCORE 1]|/', so the score array is initialized accordingly
                        scores[1] = "/";

                        // Notify user of a spare
                        System.out.println(playerNames[player] + " has a spare!");

                        // If this spare occurs in the last frame, then there must be one more ball
                        if (frame == frames) {
                            balls[2] = getNextBall(player, 2, frame);
                            scores[1] += "|" + (balls[2] == 10 ? "X" : balls[2]);
                        }
                    }
                }

                /*
                 * Checks whether the PREVIOUS FRAME had a strike or a spare
                 * This is done in order to correctly add the points
                 * The block below also updates the scoreboard (and removes the blank spaces)
                 */
                if (frame * 2 - 2 > 0) {
                    /*
                     * PREVIOUS BOARD contains the preceding state of the board, without the last frame
                     * This is necessary, since the last frame would be empty if there was a strike or a spare in that frame
                     */
                    prevBoard = scoreBoard[3 * player + 3].substring(0, scoreBoard[3 * player + 3].length() - 7);

                    // Check for a strike in the previous frame
                    if (playerPoints[player][frame * 2 - 4] == 10) {
                        /*
                         * the case there was a strike in the previous frame, we must also check for a strike in the case before the previous case
                         * This is because a strike in the previous-previous case would not have yet been accounted for
                         * In order to account for this without double accounting for it, several checks are conducted so that it is handled correctly:
                         */

                        // 1) Check if current frame is also a strike (if so, then previous frame should not be accounted yet) (done in the initial IF statement)

                        // 2) Check if the previous-previous frame is also a strike (this would not be checked for otherwise)
                        if (frame * 2 - 4 > 0 && playerPoints[player][frame * 2 - 6] == 10) {
                            prevBoard = prevBoard.substring(0, prevBoard.length() - 7);
                            playerPoints[player][frames * 2] += 20 + playerPoints[player][frame * 2 - 2];
                            prevScore = Integer.toString(playerPoints[player][frames * 2]);
                            scoreBoard[3 * player + 3] = prevBoard + String.format(" %-5s|", prevScore);
                            prevBoard = prevBoard + String.format(" %-5s|", prevScore);
                        }

                        // 3) Account for the strike in the previous frame
                        if (!(playerPoints[player][frame * 2 - 2] == 10) || frame == frames) {
                            // Update the total points, now that the next two values (after the strike) have been determined
                            if (frame == frames) playerPoints[player][frames * 2] += 10 + balls[0] + balls[1];
                            else playerPoints[player][frames * 2] += 10 + playerPoints[player][frame * 2 - 2] + playerPoints[player][frame * 2 - 1];

                            // Insert the new, updated score into the scoreboard
                            prevScore = Integer.toString(playerPoints[player][frames * 2]);
                            scoreBoard[3 * player + 3] = prevBoard + String.format(" %-5s|", prevScore);
                        }

                        if (frame * 2 - 4 > 0 && playerPoints[player][frame * 2 - 6] == 10 && playerPoints[player][frame * 2 - 2] == 10 && frame != frames) scoreBoard[3 * player + 3] += "      |";
                    }

                    // Check for a spare in the previous frame
                    else if (playerPoints[player][frame * 2 - 4] + playerPoints[player][frame * 2 - 3] == 10) {
                        // Same logic as above, but instead of looking at two values after the spare, only one is considered
                        playerPoints[player][frames * 2] += 10 + playerPoints[player][frame * 2 - 2];
                        prevScore = Integer.toString(playerPoints[player][frames * 2]);
                        scoreBoard[3 * player + 3] = prevBoard + String.format(" %-5s|", prevScore);
                    }
                }

                /*
                 * Below is the OUTPUT logic for the SCOREBOARD array
                 * Essentially, this block determines what is appended to each line of the SCOREBOARD
                 */

                /*
                 * If this frame contained a strike or a spare, the TOTAL SCORE cannot be determined until at least the next frame
                 * As such, the TOTAL SCORE for this frame is left empty
                 */

                if (scores[1].equals("X") || scores[1].equals("/")) scores[2] = "";

                // When there is not a spare or a strike in this frame, the TOTAL SCORE can be determined by adding the two current scores
                else {
                    /*
                     * This check is done for a spare or a strike in the last frame
                     * In this case, the points from the 3rd ball must be added to the 2nd ball
                     * This addition is done to ensure that the total score includes all balls
                     */
                    if (scores[1].contains("/")) balls[1] += balls[2];
                    else if (scores[1].contains("|")) balls[1] += balls[2];

                    /*
                     * Add the current total points together to the last element of the PLAYER POINTS array
                     * Convert this to string format for later
                     */
                    playerPoints[player][frames * 2] += balls[0] + balls[1];
                    scores[2] = Integer.toString(playerPoints[player][frames * 2]);
                }

                /*
                 * Finally, all the scores from this frame for this player are added to the SCOREBOARD array
                 * Basically, these scores are appended to the pre-existing rows in the SCOREBOARD
                 * Each player has 3 associated lines in the SCOREBOARD
                 */

                // 1st line: contains scores from balls[0] and balls[1], as well as balls[2] (in some cases)
                scoreBoard[3 * player + 2] += String.format("%6s|", String.format("%s|%s", scores[0], scores[1]));

                // 2nd line: contains total score (scores[2], or the last element from the PLAYER POINTS array)
                scoreBoard[3 * player + 3] += String.format(" %-5s|", scores[2]);

                // 3rd line: '-' row separator from one player to another
                scoreBoard[3 * player + 4] = scoreBoard[1];

                // Blank line after each player's turn
                System.out.println();
            }
            /*
             * Printing out the SCOREBOARD at the end of each frame
             * This is a simple process: each line (element) from the array is printed
             */
            System.out.println("Scoreboard:");
            for (int line = 0; line < (playerCount * 3 + 2); line++) System.out.println(scoreBoard[line]);
        }

        /*
         * OUTPUT
         * This part will print the final output
         * This includes the winner as well as a leader board
         */

        /*
         * The FOR-LOOPS below have the following purposes
         * Multiple FOR-LOOPS are necessary for different functions
         */

        /*
         * 1) Find the player with the highest score
         * The WINNING PLAYER variable is an integer containing the index of the winning player
         * The winning player index will be used to output the winner at the end of the game
         * Basically, by going through each element and comparing it with the last, we can determine the largest element
         */
        winningPlayer = playerPoints[0][frames * 2];
        for (int player = 1; player < playerCount; player++) if (playerPoints[player][frames * 2] >= playerPoints[player - 1][frames * 2]) winningPlayer = playerPoints[player][frames * 2];

        /*
         * 2) Creating and initializing two arrays.
         * FINAL SCORES will contain a copy of the final scores, at the correct index, that will be used for the leader board and determining the winner(s)
         * SORTED SCORES will be used to sort the players for display on the leader board
         */
        for (int player = 0; player < playerCount; player++) {
            finalScores[player] = playerPoints[player][frames * 2];
            sortedScores[player] = playerPoints[player][frames * 2];
        }

        // Calling the SORT function from the java.util.Arrays library
        Arrays.sort(sortedScores);

        /*
         * 3) Update the LEADER BOARD array
         * The LEADER BOARD array is updated each time for each player
         * The leader board contains the rank (out of 300), player name and score
         * The leader board will be output before showing the winner
         * Since the leader board must be sorted, there is more complex logic involved
         */
        for (int player = 0; player < playerCount; player++) {
            /*
             * For each score in the sorted scores, the index of the that score in the playerPoints array tells us the index of the player
             * By finding the index of the player (in the preceding step, we can add a new row to the leader board
             * With this logic, the LEADER BOARD array now contains 2 lines for the title, and playerCount amount of line containing the leader board ranking in ascending order
             * The order of the leader board position is reversed when the board itself is being outputted
             */
            finalScore = sortedScores[player];
            scoreIndex = 0;

            for (int index = 1; index < playerCount; index++) if (playerPoints[index][frames * 2] == finalScore) {
                scoreIndex = index;
                playerPoints[index][frames * 2] = -1;
                break;
            }

            /*
             * This loop determines all the winning players, based on the highest final score found earlier
             * If a player is not a winner, their position is set as -1
             */
            if (winningPlayer == finalScores[player]) winners[player] = player;
            else winners[player] = -1;

            // Finally, everything is appended to the array actually containing the leader board
            leaderBoard[player + 2] = String.format(" %-4s | %-8s | %-6s ", playerCount - player, playerNames[scoreIndex], finalScore);
        }

        /*
         * 4) Printing out the LEADER BOARD at the end of the game
         * Identical process to printing out the SCOREBOARD: simply print out each element of the leader board
         */
        System.out.println("\nLeader board:");
        for (int line = 0; line < 2; line++) System.out.println(leaderBoard[line]);

        // NOTE: the actual player positions are in reverse order (as explained above, in the FOR-LOOP)
        for (int line = playerCount + 1; line > 1; line--) System.out.println(leaderBoard[line]);

        /*
         * 5) Output the winner(s), and declare the winner(s)'s
         * NOTE: A StringBuilder type variable is used since the String is concatenated inside of a FOR-LOOP
         */

        // First, the winners are determined
        for (int winner = 0; winner < playerCount; winner++) if (winners[winner] != -1) gameResult.append(String.format("%s, ", playerNames[winners[winner]]));

        // Then, ', ' is removed from the end of the string, as the last winner should not have this behind his/her/their name
        gameResult = new StringBuilder(gameResult.substring(0, gameResult.length() - 2));

        /*
         * The ternary operator determines whether 'win' will be in plural form
         * Essentially, when there is only one winner, the GAME RESULT will not contain ', ' even once
         * As such, that is the condition used to determine whether there is one winner
         * Knowing whether there is one or more winners, the 'win' is put into plural form accordingly
         */
        gameResult.append(gameResult.toString().contains(", ") ? " win" : " wins");

        // Finish the final part of the GAME RESULT, including the winning (highest) score, outputted in a proper format
        gameResult.append(" with a score of ").append(winningPlayer).append("!");
        System.out.println(gameResult);

        /*
         * Close the USER INPUT Scanner at the end of the file
         * Leaving the Scanner open may be harmful to the memory (RAM) of the computer
         */
        userInput.close();
    }
}
