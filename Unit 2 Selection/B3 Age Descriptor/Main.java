import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		// Age descriptor
		// Created by Malav Mehta, October 5, 2019

		// Declaring variables and scanner for user input
		int age;
		Scanner userInput = new Scanner(System.in);

		// Getting user input
		System.out.println("Enter age:");
		age = userInput.nextInt();
		userInput.close();

		// Output logic
		if (age <= 0) System.out.println("Invalid age: you are not born yet.");
		else if (age < 12) System.out.println("You are a child");
		else if (age < 20) System.out.println("You are a teen");
		else if (age < 66) System.out.println("You are an adult");
		else if (age < 117) System.out.println("You are a senior citizen");
		else System.out.println("Invalid age: you are not human.");
	}
}