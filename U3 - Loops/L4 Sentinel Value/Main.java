// Sentinel value
// Created by Malav Mehta, October 29, 2019

// Importing scanner
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
  	// Declare variables
  	Scanner userInput = new Scanner(System.in);
  	int sentinel, temp;

  	// Get input
  	System.out.println("Enter a sentinel value:");
  	sentinel = userInput.nextInt();
  	temp = sentinel - 1;

  	// Process + output
  	while (temp != sentinel) {
  		System.out.println("Enter an integer:");
  		temp = userInput.nextInt();
  	}

  	// Final output
  	System.out.println("Stop");
  }
}