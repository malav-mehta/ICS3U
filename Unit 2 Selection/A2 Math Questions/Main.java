// Math question generator
// Created by: Malav Mehta, October 5, 2019

import java.util.Scanner;
import java.lang.Math;

class Main {
	// Method which generates a question, calculates its answer and checks it against the user's answer
	// static (called from the main method, whcih is also static)
	// void (no return statement required for this method)
	static void askQuestion() {
		// Setting up a Scanner to get user input
		Scanner userInput = new Scanner(System.in);

		// Declaring variables
		double num1, num2, answer, userAnswer;

		// GENERATING QUESTION

		// Getting two new random numbers, rounded to one decimal place (with numbers generation from 0 to 50)
		num1 = Math.round((Math.random() * 50)*10.0)/10.0;
		num2 = Math.round((Math.random() * 50)*10.0)/10.0;

		// Generating a random number between 1 and 4 to randomize the math operation. A switch case is used to calculate the answer based on the math operation that gets generated.
		switch ((int) Math.round(Math.random() * 3 + 1)) {
			case 1: // Similar structure for each case (and the default case), structured as follows
				System.out.print(num1 + " + " + num2 + " = "); // Printing the question (based on the generated operation)
				answer = num1 + num2; // Calculating the answer (based on the generated operation)
				break; // Exit the switch statement
			case 2:
				System.out.print(num1 + " - " + num2 + " = ");
				answer = num1 - num2;
				break;
			case 3:
				System.out.print(num1 + " / " + num2 + " = ");
				answer = num1 / num2;
				break;
			default:
				System.out.print(num1 + " * " + num2 + " = ");
				answer = num1 * num2;
				break;
		}

		answer = Math.round(answer * 10.0) / 10.0; // Rounding the answer to one decimal place
		userAnswer = userInput.nextDouble(); // Getting the user's answer

		if (answer == userAnswer) System.out.println("That's right. Good Job!\n"); // If the answers match, the user is correct and the staetment is printed accordingly.
		else System.out.println("Incorrect. The correct answer was " + answer + ".\n"); // Otherwise, show that the user's answer is incorrect and output an according statement.
	}
	public static void main(String[] args) {
		// Introducing the program
		System.out.println("Welcome the math question generator. This program will ask you three simple math questions. Good luck!\nRound answers to 1 decimal place.\n\n");
		// Setting a for loop to ask three questions (instead of rewriting the code each time)
		for (int i = 0; i < 3; ++i) askQuestion();
	}
}