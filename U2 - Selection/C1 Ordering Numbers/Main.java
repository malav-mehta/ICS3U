// Numbers in order
// Created by Malav Mehta, October 8, 2019

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
  	// setting up scanner for user input
  	Scanner userInput = new Scanner(System.in);

  	// declaring variables
  	int num1, num2, num3, numTemp;

  	// getting user input
  	System.out.println("Enter 3 integer values:");
  	num1 = userInput.nextInt();
  	num2 = userInput.nextInt();
  	num3 = userInput.nextInt();

  	// sorting integers method 1

  	// compare 1 and 2
  	// if (num1 > num2) {
  	// 	numTemp = num1; // if num1 greater than num2, switch their values, so num1 and num2 are sorted
  	// 	num1 = num2;
  	// 	num2 = numTemp;
  	// }

  	// // same as above, with num2 and num3
  	// if (num2 > num3) {
  	// 	numTemp = num2;
  	// 	num2 = num3;
  	// 	num3 = numTemp;

  	// 	// same as above: this time, num1 and num2 are compared again, due to the possibility of the previous change being undone with the num2 and num3 comparison
	  //	if (num1 > num2) {
	  //		numTemp = num1;
	  //		num1 = num2;
	  //		num2 = numTemp;
	  //	}
  	// }

  	// alternative method using &&
  	if (num1 > num2 && num1 > num3) { // check if num1 is the greatest
  		if (num2 > num3) { // check if num 2 is greater than num3
  			System.out.println(num3 + ", " + num2 + ", " + num1); // if yes, then place num2 after num3, and num 1 is determined to be output last
  		} else {
  			System.out.println(num2 + ", " + num3 + ", " + num1); // if not, then place num3 before num2, since num3 must be greater than or equal to num2
  		}
  	} else if (num2 > num1 && num2 > num3) { // same as above
  		if (num1 > num3) {
  			System.out.println(num3 + ", " + num1 + ", " + num2);
  		} else {
  			System.out.println(num1 + ", " + num3 + ", " + num2);
  		}
  	} else {
  		if (num2 > num1) { // same as above
  			System.out.println(num1 + ", " + num2 + ", " + num3);
  		} else {
  			System.out.println(num2 + ", " + num1 + ", " + num3);
  		}
  	}
  }
}