/*

FACTORING CHALLENGE SOLUTION

This program accepts three integers, as input values a, b and c for the format ax^2+bx+c. The quadratic is
then factored by this program, and the factored version is printed to the console. This program assumes all
polynomials are guaranteed to be able to be factored.

Malav Mehta
September 19, 2019
ICS3U-03
Mr. Benjamin Hudson

*/

// Import the JAVA Scanner utility to be able to get user input.
import java.util.Scanner;

// Import the JAVA Math library.
import java.lang.Math;

public class Main {
  // Method used to determine how a value is show when printed (i.e. -1 -> -, 1 ->
  // [space character], x -> x).
  public static String formatValue(int x) {
    String outputString;
    switch (x) {
    case -1:
      outputString = "-";
      break;
    case 1:
      outputString = "";
      break;
    default:
      outputString = Integer.toString(x);
    }
    return outputString;
  }

  // Method used to find the GCF between two values.
  public static int gcf(int x, int y) {
    if (y == 0) {
      return x;
    }
    return gcf(y, x % y);
  }

  // Method used to find the common factor of two integers.
  public static int[] factor2(int a, int b) {
    int[] factoredValues = new int[2]; // Array which will be returned.
    int tempCoefficient = Math.abs(gcf(a, b)); // Coefficient by which all values will be divided for factoring.

    // Finding the factored form of a and b.
    factoredValues[0] = a / tempCoefficient;
    factoredValues[1] = b / tempCoefficient;

    // Returning the array containing the factored values.
    return factoredValues;
  }

  // Method used to find the common factor of three integers.
  public static int[] factor3(int a, int b, int c) {
    int[] factoredValues = new int[4]; // Array which will be returned.
    int tempCoefficient = Math.abs(gcf(gcf(a, b), c)); // Coefficient which will be returned and transferred to the
                                                       // actual coefficient.

    // Finding the factored form of the a, b, and c values.
    factoredValues[0] = a / tempCoefficient;
    factoredValues[1] = b / tempCoefficient;
    factoredValues[2] = c / tempCoefficient;
    factoredValues[3] = tempCoefficient;

    // Returning the array containing the factored values.
    return factoredValues;
  }

  // The main method of the 'Main' class that will be run when the program is run.
  public static void main(String[] args) {
    // Setting up the Scanner with the JAVA util import to scan user input in the
    // 'userInput' variable.
    Scanner userInput = new Scanner(System.in);

    // Declaring all the variables up front.
    int a, b, c; // The a, b and c values for a quadratic in the form ax^2 + bx + c.
    int order = 0; // Used to determine the order of the polynomials when they are printed.
    int coefficient = 1; // Will be used when a, b and c are factored.
    int r, s; // The two roots for the quadratic in the form a(x-r)(x-s).
    int denominatorR, denominatorS; // The common denominator for the both fractions (when the roots r and s will be
                                    // found using the quadratic formula).

    int[] factoredVariable = new int[4]; // Used to get a variables from the return line in a method.

    double discriminator; // The square root of the discriminator (D = b^2-4ac)^(1/2).

    String outputCoefficient, outputDenominatorR, outputDenominatorS, stringR, stringS; // Strings used for preliminary
                                                                                        // formatting of the output.;

    // Getting the input values to initialize a, b and c.
    a = userInput.nextInt();
    b = userInput.nextInt();
    c = userInput.nextInt();

    // Getting the a, b and c values factored.
    factoredVariable = factor3(a, b, c); // Results returned in the form of an array.
    a = factoredVariable[0];
    b = factoredVariable[1];
    c = factoredVariable[2];
    coefficient = factoredVariable[3];

    // Calculating the discriminator, denominator.
    discriminator = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
    denominatorR = 2 * a;

    // Calculating the two roots (without division by 2a);
    r = -b + (int) discriminator;
    s = -b - (int) discriminator;

    // Calculating the alternative denominator 2 (for the second fraction (second
    // root)).
    if (a > 0) {
      denominatorS = denominatorR; // Both equal to 2a.
    } else {
      denominatorS = -denominatorR; // When a is negative, the negative can be factored out from the s value.
      s *= -1;
    }

    // Simplifying both fractions
    factoredVariable = factor2(r, denominatorR);
    r = factoredVariable[0];
    denominatorR = factoredVariable[1];

    factoredVariable = factor2(s, denominatorS);
    s = factoredVariable[0];
    denominatorS = factoredVariable[1];

    // Determining the order of the terms/polynomials, using the 'order' variable.
    // The order variable is given points based on the magnitude of certain variable
    // in comparison with others (using absolute values).
    if (Math.abs(r) > Math.abs(s)) {
      order += 1;
    } else if (Math.abs(r) < Math.abs(s)) {
      order -= 1;
    }

    if (Math.abs(denominatorR) > Math.abs(denominatorS)) {
      order += 2;
    } else if (Math.abs(denominatorR) < Math.abs(denominatorS)) {
      order -= 2;
    }

    // Removing negative coefficients (by checking the denominators if they are
    // negative).
    if (denominatorR < 0) {
      denominatorR *= -1;
      r *= -1;
      coefficient *= -1;
    }
    if (denominatorS < 0) {
      denominatorS *= -1;
      s *= -1;
      coefficient *= -1;
    }

    // This section prepares all the values for output by converting them to String
    // format. This is done in order to add the signs, and ensure that the overall
    // formatting for all of the variables is correct (done as per the given
    // requirements).
    outputDenominatorR = Integer.toString(denominatorR);
    outputDenominatorS = Integer.toString(denominatorS);
    if (denominatorS == 1) outputDenominatorS = "\0";
    if (denominatorR == 1) outputDenominatorR = "\0";

    stringR = Integer.toString(-r); // The format is (x-r), therefore we have to use -r as our point of reference to
                                    // get the answer in the form (x+r).
    stringS = Integer.toString(-s); // The format is (x-s), therefore we have to use -s as our point of reference to
                                    // get the answer in the form (x+s).
    if (-r > 0) {
      stringR = "+" + stringR;
    } else if (r == 0) {
      stringR = "\0";
      }
    if (-s > 0) {
      stringS = "+" + stringS;
    } else if (s == 0) {
      stringS = "\0";
    }
    outputCoefficient = formatValue(coefficient);

    // Printing the output based on the results and the points awarded to the
    // 'order' variable above in order to determine the order.
    if (order < 0) {
      if ((outputDenominatorR == "\0") && (stringR == "\0")) {
        System.out.printf("%sx(%sx%s)", outputCoefficient, outputDenominatorS, stringS);
      } else {
        System.out.printf("%s(%sx%s)(%sx%s)", outputCoefficient, outputDenominatorR, stringR, outputDenominatorS,
            stringS);
      }
    } else {
      if ((outputDenominatorS == "\0") && (stringS == "\0")) {
        System.out.printf("%sx(%sx%s)", outputCoefficient, outputDenominatorR, stringR);
      } else {
        System.out.printf("%s(%sx%s)(%sx%s)", outputCoefficient, outputDenominatorS, stringS, outputDenominatorR,
            stringR);
      }
    }
  }
}
