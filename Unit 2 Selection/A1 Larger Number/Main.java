import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Using if statements to compare numbers
    // Created by Malav Mehta, October 4, 2019

    // Defining scanner to get user input
    Scanner userInput = new Scanner(System.in);

    // Declaring variables
    int n1, n2;

    // Getting user input
    System.out.println("Enter first number:");
    n1 = userInput.nextInt();

    System.out.println("Enter second number:");
    n2 = userInput.nextInt();

    // Determining the output
    if (n1 > n2) {
    	System.out.println ("Result: " + n1 + " is larger than " + n2);
    } else if (n2 > n1) {
    	System.out.println ("Result: " + n2 + " is larger than " + n1);
    } else {
    	System.out.println("Result: The numbers are equal");
    }
  }
}