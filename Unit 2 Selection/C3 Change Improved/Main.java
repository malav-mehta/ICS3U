// Improved change calculator (under 100 cents)
// Created by Malav Mehta, October 9, 2019

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Setting up scanner for user input
    Scanner userInput = new Scanner(System.in);

    // Declaring variables
    String quarterDescriptor, dimeDescriptor, nickelDescriptor, pennyDescriptor;
    int cents, quarters, dimes, nickels, pennies;

    // Getting user input
    System.out.println("Enter amount less than $1");
    cents = userInput.nextInt();

    // Calculating number of coins
    quarters =  (cents - cents % 25) / 25;
    dimes = ((cents - quarters * 25) - (cents - quarters * 25) % 10) / 10;
    nickels = ((cents - quarters * 25 - dimes * 10) - (cents - quarters * 25 - dimes * 10) % 5) / 5;
    pennies = cents - quarters * 25 - dimes * 10 - nickels * 5;

    // Determining output
    if (quarters == 0) quarterDescriptor = "\0";
    else if (quarters == 1) quarterDescriptor = "1 quarter";
    else quarterDescriptor = quarters + " quarters";

    if (dimes == 0) dimeDescriptor = "\0";
    else if (dimes == 1) dimeDescriptor = "1 dime";
    else dimeDescriptor = dimes + " dimes,";

    if (nickels == 0) nickelDescriptor = "\0";
    else if (nickels == 1) nickelDescriptor = "1 nickel";
    else nickelDescriptor = nickels + " nickels,";

    if (pennies == 0) pennyDescriptor = "\0";
    else if (pennies == 1) pennyDescriptor = "1 penny";
    else pennyDescriptor = pennies + " pennies";

    if (dimes != 0 || nickels != 0 || pennies != 0) quarterDescriptor += ", ";
    if (nickels != 0 || pennies != 0) dimeDescriptor += ", ";
    if (pennies != 0) nickelDescriptor += ", ";

    // Output
    System.out.println("Your change is: " + quarterDescriptor + dimeDescriptor + nickelDescriptor + pennyDescriptor);
  }
}