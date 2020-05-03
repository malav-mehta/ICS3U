/*
 * MegaMind Assignment
 * Created by: Malav Mehta
 * For:        ICS3U, Mr. Hudson
 * On:         February 20, 2020
 *
 * ASSIGNMENT INSTRUCTIONS:
 * Your goal is to create a one player game of Mastermind against the computer. The
 * computer will generate a 4 character code based on colours and the player is to
 * guess the code. The computer will tell the player two pieces of information:
 *    - an x for each proper position AND colour
 *    - an o for each correct colour only
 *
 * NOTE - the order of x's and o's does not match the order of pins
 */

// Scanner for getting input
import java.util.Scanner;

public class Main {
  // Maximum amount of rounds that the user can input guesses.
  private static final int MAX_GUESSES = 10;

  // The maximum length of the code.
  private static final int MAX_LENGTH = 4;

  // The possible colors for the pegs which will be input.
  private static final char[] COLORS = { 'R', 'G', 'P', 'Y', 'N', 'O', 'B', 'W' };

  // Computer generated code is held in a character array.
  private static char[] generatedCode = new char[MAX_LENGTH];

  // Scanner object created for getting input from the IO stream.
  private static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    // Welcome user to the game.
    System.out.println("Welcome to MasterMind");

    // 2D array will be used for storing the information of the board.
    String [][] board = new String[10][2];

    // To check if the user has won the game.
    boolean userWon = false;

    // Initialize the game by getting the computer generated code.
    generateCode();
    // generatedCode = new char[]{ 'Y', 'P', 'O', 'B' }; // For the TEST CASE

    // FOR-LOOP that runs for MAX_GUESSES amount of time.
    for (int i = 0; i < MAX_GUESSES; i++) {
      // Getting the code from the year.
      board[i][0] = nextCode();

      // Getting feedback on the user's inputted code.
      board[i][1] = generateFeedback(board[i][0]);

      // Print the current board to update the user.
      printBoard(board);

      // If the feedback is "xxxx", meaning that the user has guessed each color in the
      // correct position, the user has won the game and FOR-LOOP will be broken.
      if (board[i][1].equals("xxxx")) {
        userWon = true;
        break;
      }
    }

    // Ternary operator that determines what the final message will be, based on whether
    // the user has won the game.
    System.out.println(userWon ? "YOU WIN!" : "YOU LOSE. The code was " + generatedCode[0] + generatedCode[1] + generatedCode[2] + generatedCode[3] + ". Better luck next time.");

    // Close the game.
    in.close();
  }

  /**
   * Determines whether the specified character is found int he array that is put into the
   * function as its parameter,
   * @param el the character that the function checks for inside the array.
   * @param arr the array in which the character needs to be looked for.
   * @return whether or not the element is in the array.
   */
  private static boolean elementInArray(char el, char[] arr) {
    boolean elInArr = false;

    // Run a FOR-EACH LOOP in order to iterate through each element in the array, and matching
    // it with the specified character (linear search through the array).
    for ( char c : arr ) {
      if (c == el) {
        elInArr = true;
        break;
      }
    }

    return elInArr;
  }

  /**
   * Generates a distinct peg color for each element of the code.
   * @return the distince peg
   */
  private static char nextPeg() {
    char peg;

    // Ensure that there are no duplicates in the computer generated code.
    do {
      peg = COLORS[(int) (Math.random() * 8)];
    } while (elementInArray(peg, generatedCode));

    return peg;
  }

  /**
   * Generate the code that needs to be broken for the game of MasterMind. This code is generated
   * by getting MAX_LENGTH amount of pegs and appending them to the generatedCode.
   */
  private static void generateCode() {
    for (int i = 0; i < MAX_LENGTH; i++)
      generatedCode[i] = nextPeg();
  }

  /**
   * Checks if the user inputted code is valid.
   * @param code the user code that will be checked.
   * @return whether the code is invalid.
   */
  private static boolean isCodeInvalid(String code) {
    boolean invalidCode = false;

    // The code must be of the correct length.
    if (code.length() == MAX_LENGTH) {
      // Check if each element of the user inputted code is a valid color peg.
      for (int i = 0; i < MAX_LENGTH; i++) {
        if (!(elementInArray(code.charAt(i), COLORS))) {
          invalidCode = true;
          break;
        }
      }
    }
    else
      invalidCode = true;

    return invalidCode;
  }

  /**
   * Prints the divider between section.
   */
  private static void printDivider() {
    System.out.println("-------------");
  }

  /**
   * Gets the next user input code after validation.
   * @return the user input code.
   */
  private static String nextCode() {
    // Prompt for the next code.
    System.out.println("\nEnter Code");
    String code;

    while (true) {
      // Get the code and check for validation. If not valid, then continue loop.
      // Process the input by removing spaces, commas and periods, and turning the string into upper case.]
      code = in.nextLine().replaceAll("\\s+","").replaceAll(",", "").replaceAll("\\.", "").toUpperCase();
      if (isCodeInvalid(code))
        System.err.printf("ERROR! You entered the following code: '%s'.\nThis code is invalid. Try again below.\n> ", code);
      else
        break;
    }

    // Print divider and return the validated user input.
    printDivider();
    return code;
  }

  // Print the current board.
  private static void printBoard(String[][] board) {
    // Title the board.
    System.out.println("Current Board");

    // Print each element of the 1st dimension of the board array.
    for (int i = MAX_GUESSES - 1; i >= 0; i--)
      if (board[i][0] != null)
        System.out.printf("%-5s | %4s\n", board[i][0], board[i][1]);

    // Print divider
    printDivider();
  }

  /**
   * Generates feedback for the user inputted code, by comparing it to the computer generated code
   * and getting the amount of colored vs uncolored feedback.
   * @param inputCode the user's guess.
   * @return string containing the feedback (x and o, up to MAX_LENGTH in length).
   */
  private static String generateFeedback(String inputCode) {
    // Variables needed to generate feedback.
    String coloredFeedback = "", uncoloredFeedback = "", added = "";
    char coloredMarker = 'x', uncoloredMarker = 'o', peg;

    for (int i = 0; i < MAX_LENGTH; i++) {
      // Get current peg.
      peg = inputCode.charAt(i);

      // Only if the color is in the generatedCode array.
      if (elementInArray(peg, generatedCode)) {
        // If the peg is at the same position in the string as in the array, add to colored feedback,
        if (peg == generatedCode[i]) {
          // If the peg color was already accounted for (in the ADDED string, then remove one from the uncolored
          // feedback and transfer it onto the colored feedback string).
          if (added.indexOf(peg) != -1)
            uncoloredFeedback = uncoloredFeedback.substring(0, uncoloredFeedback.length() - 1);

          // Add a marker to the colored feedback.
          coloredFeedback += coloredMarker;
        }

        // If the color already has been included, add an uncolored marker to the uncolored feedback.
        else if (added.indexOf(peg) == -1)
          uncoloredFeedback += uncoloredMarker;
      }

      // Add current color to ADDED (or considered) colors.
      added += peg;
    }

    // Return the colored and uncolored feedback.
    return coloredFeedback + uncoloredFeedback;
  }
}