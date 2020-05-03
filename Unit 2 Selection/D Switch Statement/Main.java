// Percent to Letter Grade
// Created by Malav Mehta, October 10, 2019

// importing scanner for user input
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
	// setting up scanner for user input
	Scanner userInput = new Scanner(System.in);

	// declaring variables
	char letterGrade;
	String gradeRange = "Range: ";

	// getting input
	System.out.println("Enter Letter Grade:");
	letterGrade = userInput.next().toLowerCase().charAt(0);

	// determining grade range
	switch (letterGrade) {
		case 'a':
			gradeRange += "80-100";
			break;
		case 'b':
			gradeRange += "70-79";
			break;
		case 'c':
			gradeRange += "60-69";
			break;
		case 'd':
			gradeRange += "50-59";
			break;
		case 'f':
			gradeRange += "Failure";
			break;
		default:
			gradeRange = "Invalid";
			break;
	}

	System.out.println(gradeRange);
  }
}