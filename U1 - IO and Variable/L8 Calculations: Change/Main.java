import java.util.Scanner;

class Main {
  public static void main(String[] args) {
  	// Change finder (< 100 cents)
  	// Malav Mehta, Monday, September 16, 2019

  	// Setting up scanner for user input
    Scanner userInput = new Scanner(System.in);

    // Declaring variables
    int totalCents, quarters, dimes, nickels, pennies, remainingCents, oldRemainingCents;

    // Getting input
    System.out.println("Enter cents:");
    totalCents = userInput.nextInt();

    // Calculating output
    remainingCents = totalCents % 25;
    quarters = (totalCents - remainingCents) / 25;

    oldRemainingCents = remainingCents;
    remainingCents = oldRemainingCents % 10;
    dimes = (oldRemainingCents - remainingCents) / 10;

    oldRemainingCents = remainingCents;
    remainingCents = oldRemainingCents % 5;
    nickels = (oldRemainingCents - remainingCents) / 5;

	pennies = remainingCents;

	// Output
	System.out.println("Quarters " + quarters + ", dimes " + dimes + ", nickels " + nickels + ", pennies " + pennies);
  }
}