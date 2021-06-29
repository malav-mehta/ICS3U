// While sum
// Created by Malav Mehta, October 29, 2019

// Import scanner library
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Declaring variables
    Scanner userInput = new Scanner(System.in);
    int N, sum;

    System.out.println("Enter N:");
    N = userInput.nextInt();

    while (N < 0) {
    	System.out.println("Invalid input.");
    	System.out.println("Enter N:");
    	N = userInput.nextInt();
    }

    System.out.println("The sum of the first " + N + "integers is " + ((N * (N + 1)) / 2));
  }
}