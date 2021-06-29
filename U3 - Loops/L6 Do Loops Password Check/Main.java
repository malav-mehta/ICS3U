// Do Loops Password Check
// Created by Malav Mehta, October 30, 2019

// Import Scanner library
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Declaring variables
    Scanner userInput = new Scanner(System.in);
    String inpStr;

    // Input-Processing
    do {
    	System.out.println("Enter Password:");
    	inpStr = userInput.nextLine();
    } while (!(inpStr.equals("coded")));

    // Output
    System.out.println("Access Granted!");
  }
}