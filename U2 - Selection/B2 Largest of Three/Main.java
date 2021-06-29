import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// Largest of Three
		// Created by: Malav Mehta, October 5, 2019

		// Declaring variables and starting scannner
		int num1, num2, num3;
		Scanner userInput = new Scanner(System.in);

		// Getting user input
		System.out.println("Enter first number:");
		num1 = userInput.nextInt();

		System.out.println("Enter second number:");
		num2 = userInput.nextInt();

		System.out.println("Enter third number:");
		num3 = userInput.nextInt();

		// Boolean logic for output
		if ((num1 > num2 && num1 > num3) || (num1 == num2 && num1 > num3) || (num1 == num3 && num1 > num2)) System.out.println("The largest number is: " + num1);
		else if ((num2 > num1 && num2 > num3) || (num2 == num1 && num2 > num3) || (num2 == num3 && num2 > num1)) System.out.println("The largest number is: " + num2);
		else if ((num3 > num1 && num3 > num2) || (num3 == num1 && num3 > num2) || (num3 == num2 && num3 > num1)) System.out.println("The largest number is: " + num3);
	}
}