/*
 * Unit 4 In Class Assignment: Methods
 * Created by: Malav Mehta
 * On: Monday, January 20, 2019
 * For: Mr. Hudson, ICS3U, Colonel By Secondary School
 */
import java.util.Scanner;

public class Main {
  // You may use this throughout this program
  public static Scanner myInput = new Scanner(System.in);

  public static void main(String[] args) {
    double totalBill = 0;
    int userChoice;
    // 1. Create a method to take in a character (c) and integer value (n) and print
    // that character n times
    printBreak('*', 20);
    // 2. Create a welcome menu procedure that corresponds to the expected output
    welcomeMenu();
    printBreak('*', 20);

    // 3. Create a method 'getChoice' to ask for and collect valid user choice (menu
    // item 1-5)

    // 4. Create a method that will take in the valid menu option and add the
    // corresponding value to the total bill

    // 5. Create the boolean function notDone to check if 0 was entered
    do {
      userChoice = getChoice();
      totalBill += setValue(userChoice);

    } while (notDone(userChoice));

    printBreak('$', 5);
    System.out.println("\nTotal Bill is: " + Math.round(totalBill * 100.0) / 100.0);
    System.out.println("\n Paying in cash \n");

    // 6. Create a method or methods to ask the user to enter the amount of money
    // the user is paying with in dollars and return the String value of the change
    // in dollars and exact change
    // you can alter the main here to call your method(s)
    double dollarAmount = getDollarAmount(totalBill);
    printChange(dollarAmount, totalBill);
  }

  /**
   * Used to print a specific character a specific number of times with dashed
   * appended to the end of each character iteration.
   *
   * @param character: specific character that will be outputted
   * @param n:         specifies the amount of times the character will be
   *                   outputted
   */
  private static void printBreak(char character, int n) {
    // Create an output string on which the characters will be concatenated
    String output = "";
    for (int i = 0; i < n; i++)
      // Concatenate the characters for n times in a for loop
      output += character + "-";

    // Print the final result
    System.out.println(output);
  }

  /**
   * Prints the welcome menu at the beginning of the program. Contains the
   * information for the user to interact with the system.
   */
  private static void welcomeMenu() {
    // Print the welcome menu line by line
    System.out.println("Welcome to the Methods Restaurant");
    System.out.println("Choose your option\n");
    System.out.println("1. Hamburger  ($4.99)");
    System.out.println("2. Cheeseburger  ($5.79)");
    System.out.println("3. Fries  ($2.49)");
    System.out.println("4. Drink  ($1.99)\n");

    System.out.println("0. Finished Order \n");
  }

  /**
   * Used to get the choice of menu items. This function also validates input
   * (i.e. the choice has to be on the menu and has to be and integer.
   *
   * @return the choice, after it has been validated
   */
  private static int getChoice() {
    // Determines whether the choice is valid
    boolean validChoice = false;

    // Set to 0 as default (sentinel value)
    int choice = 0;

    // Loop the input so that the user MUST enter a valid choice or exit the program
    do {
      // Prompt the input
      System.out.print("Enter choice: ");

      // Try-Catch to ensure that the input is an integer
      try {
        // Get input
        choice = myInput.nextInt();

        // If the input is valid (belongs to [0, 4]), then set the choice as valid and
        // exit the loop
        if (choice >= 0 && choice <= 4)
          validChoice = true;
        // Otherwise, let the user know that the input was invalid, and try again
        else
          System.out.println("Invalid choice. Please try again.");
      } catch (Exception e) {
        System.err.println("Invalid choice. Please try again.");
        myInput.nextLine();
      }
    } while (!validChoice);

    // Return the final choice
    return choice;
  }

  /**
   * Set value uses the choice and the predetermined cost of each choice to
   * determine the value of an item from the menu.
   *
   * @param choice the user's choice off the menu, or the sentinel value (0)
   * @return the price of the choice
   */
  private static double setValue(int choice) {
    // The price will be output;
    double price;

    // Switch the choice and check case by case to determine the correct price
    switch (choice) {
    case 1:
      price = 4.99;
      break;
    case 2:
      price = 5.79;
      break;
    case 3:
      price = 2.49;
      break;
    case 4:
      price = 1.99;
      break;
    default:
      // Defaults to 0, when the choice is the sentinel value
      price = 0;
      break;
    }

    // Return the value of the choice
    return price;
  }

  /**
   * Check if the user is done selecting menu items
   *
   * @param choice used to determine whether the choice was the sentinel value
   * @return a boolean which determines whether or not to exit the loop
   */
  private static boolean notDone(int choice) {
    // If the choice == 0, then it will return false (not not done -> done), and
    // otherwise it will return true (not done -> not done)
    return !(choice == 0);
  }

  /**
   * Used to get the dollar amount inputted by the user in the correct format and
   * validated
   *
   * @param total is the user's current total, used to determine whether the user
   *              has payed enough
   * @return the inputted dollar amount after validation
   */
  private static double getDollarAmount(double total) {
    // Determines if valid input was entered
    boolean validChoice = false;

    // Set the dollar amount to 0 as a default
    double dollarAmount = 0;

    // Loop until valid input has been entered
    do {
      // Prompt for dollar amount
      System.out.print("Enter dollar amount: ");

      // Try-Catch ensures the inputted value is a double
      try {
        dollarAmount = myInput.nextDouble();
        // Ensures that the user has paid enough
        if (dollarAmount >= total)
          validChoice = true;
        // If the user enters -1 (they don't have enough money) cancel the transaction
        // and exit the program
        else if (dollarAmount == -1) {
          System.out.println("Transaction cancelled.");
          System.exit(0);
        }
        // Inform the user that they don't have enough money
        else
          System.out
              .println("You haven't entered enough money. Please try again or enter -1 to cancel the transaction.");
      } catch (Exception e) {
        System.err.println("Invalid entry. Please try again.");
        myInput.nextLine();
      }
    } while (!validChoice);

    // Return input
    return dollarAmount;
  }

  /**
   * Prints the change for each coin type, by determining the different between
   * how much the user has paid and how much less they were supposed to pay.
   *
   * @param dollarAmount is the amount the user has paid
   * @param total        is the amount the user needs to pay
   */
  private static void printChange(double dollarAmount, double total) {
    // Get the remaining amount in pennies
    int remaining = (int) Math.round((dollarAmount - total) * 100);

    // Initialize coin amounts
    int dollars, quarters, dimes, nickels, pennies;

    // Calculate amount of change from each coin type
    dollars = (remaining - remaining % 100) / 100;
    remaining = remaining % 100;

    quarters = (remaining - remaining % 25) / 25;
    remaining = remaining % 25;

    dimes = (remaining - remaining % 10) / 10;
    remaining = remaining % 10;

    nickels = (remaining - remaining % 5) / 5;
    remaining = remaining % 5;

    pennies = remaining;

    // Print this information in the specified format
    System.out.printf("Change is:\n%d dollars\n%d quarters\n%d dimes\n%d nickels\n%d pennies", dollars, quarters, dimes,
        nickels, pennies);
  }
}