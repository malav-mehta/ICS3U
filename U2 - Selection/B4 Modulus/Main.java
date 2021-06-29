import java.util.Scanner;

class Main {
  public static void main(String[] args) {
  	// Mod quiz
  	// Created by: Malav Mehta, October 5, 2019

  	// Setting up scanner for user input
  	Scanner userInput = new Scanner(System.in);

  	// Declaring variables
  	int num1, num2, answer, userAnswer;

  	// Getting user input
  	System.out.println("Enter first num:");
  	num1 = userInput.nextInt();

  	System.out.println("Enter second num:");
  	num2 = userInput.nextInt();

  	System.out.println("What is " + num1 + " mod " + num2 + "?");
  	userAnswer = userInput.nextInt();

  	// Calculating answer
  	answer = num1 % num2;

  	// Determining output
  	if (answer == userAnswer) System.out.println("Congrats - correct.");
  	else System.out.println("Incorrect\nModulus returns the remainder.\n" + num2 + " divides into " + num1 + ", " + ((num1 - answer) / num2) + " times and there is " + answer + " leftover.\n" + num1 + " mod " + num2 + " is " + answer + ".");
  }
}