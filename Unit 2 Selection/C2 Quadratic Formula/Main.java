// Finding the roots of a quadratic equation
// Created by Malav Mehta, October 9, 2019

// Importing Scanner for user input and Math for certain math operations
import java.util.Scanner;
import java.lang.Math;

class Main {
  public static void main(String[] args) {
    // Setting up a scanner for user input
    Scanner userInput = new Scanner(System.in);

    // Declaring variables
    String solutionDescriptor;
    double a, b, c, D;
    double x1, x2;

    // Getting user input
    System.out.println("Enter quadratic equation a, b and c");
    a = userInput.nextDouble();
    b = userInput.nextDouble();
    c = userInput.nextDouble();

    // Calculating the discriminant
    D = b*b - 4*a*c;

    // Determining the output
    if (D == 0) {
    	x1 = Math.round((-b / 2*a) * 10) / 10;
    	solutionDescriptor = "This has one solution x=" + x1;
    } else if (D > 0) {
    	x1 = Math.round((-b + Math.sqrt(D)) / (2 * a) * 10.0) / 10.0;
    	x2 = Math.round((-b - Math.sqrt(D)) / (2 * a) * 10.0) / 10.0;
    	solutionDescriptor = "This has two solutions x=";
    	if (x1 > x2) solutionDescriptor += x2 + " and x=" + x1;
    	else solutionDescriptor += x1 + " and x=" + x2;
    } else {
    	solutionDescriptor = "This has no solution";
    }
    System.out.println(solutionDescriptor);
  }
}