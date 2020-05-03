/**
 * Methodize In-class Assignment Practice
 * @author Malav Mehta
 * @class ICS3U
 * @date Thursday, January 16, 2020
 */

// Importing the required dependencies (Scanner for user input and Math for mathematical operations)
import java.util.Scanner;
import java.lang.Math;

/**
 * Main class
 * This class runs the program and contains the required methods to analyze
 * the inputted quadratic in standard form.
 */
class Main {
	/**
	 * Converts the integer coefficient (either a, b, and c of a quadratic
	 * in the standard form) into a properly formatted String. This means that
	 * each term in the quadratic is individually formatted in this method. This
	 * method looks out for: extraneous "1"s, "0"s and "+/-" symbols so that
	 * the output format is properly formatted.
	 *
	 * @param coefficient: takes in the a, b or c value
	 * @param position: determines whether the input value was a, b, or c. This
	 * is used to append the corresponding term (either x^2, x or nothing) to the
	 * end of the term.
	 */
    public static String intToString(int coefficient, int position) {
    	// String that will be output
        String stringFormat = "";

		// Check if coefficient is zero: if it is zero, than the term does not need to be generated.
        if (coefficient != 0) {
        	// Determine the sign appended to the front ("+" for positive, "-" for negative)
            if (coefficient > 0)
                stringFormat += "+";
            else
                stringFormat += "-";

            // Add the absolute value of the coefficient, since the "sign" has already been added
            if (!(coefficient == 1 || coefficient == -1))
                stringFormat += Integer.toString(Math.abs(coefficient));

			// Add the polynomial based on the position
            if (position == 1)
                stringFormat += "x^2";
            else if (position == 2)
                stringFormat += "x";
        }

        // Return the final string
        return stringFormat;
    }

    /**
     * showQuadratic calls the intToString function for each coefficient in the quadratic,
     * and then it determines using these coefficients what should be output.
     *
     * @param a, b, c are the coefficients for the terms of the quadratic in standard form.
     */
    public static void showQuadratic(int a, int b, int c) {
    	// String that will be printed to the terminal
    	String quadratic;

    	// Check if a is zero to determine if the equation really is a quadratic
    	if (a != 0) {
    		// First, the raw quadratic is generated from the intToString methods for each coefficient
	        quadratic = intToString(a, 1) + intToString(b, 2) + intToString(c, 3);

	        // The only thing left to do is to check if there is an unnecessary negative sign in front of the equation
	        if (a == 0) {
	            if (b == 0) {
	                if (!(c < 0)) {
	                	// When there is only a "c" term, and it is positive, remove the "plus sign"
	                    quadratic = quadratic.substring(1);
	                }
	            } else {
	                if (!(b < 0)) {
	                	// If there is no "a" term, and the "b" term is positive, remove the "plus sign"
	                    quadratic = quadratic.substring(1);
	                }
	            }
	        } else {
	            if (!(a < 0)) {
	            	// If the "a" term is positive, remove the "plus sign"
	                quadratic = quadratic.substring(1);
	            }
	        }

	        // Output the quadratic to the console.
	        System.out.println(quadratic);
    	} else {
    		// Inform the user that this is not a quadratic, and exit the program.
    		quadratic = "This is not a quadratic.";
    		System.out.println(quadratic);

    		// The program is exited since you don't need to calculate the discriminant of a linear equation.
    		System.exit(0);
    	}
    }

	/**
	 * Calculates the discriminant from the given quadratic coefficients,
	 * and returns that value as an integer.
	 */
    public static int discriminant(int a, int b, int c) {
        return b * b - 4 * a * c;
    }

    /**
     * Rounds the double from the parameter to 2 decimal places, by rounding its value
     * when multiplied by a 100 and then dividing that final value by 100.
     */
    public static double roundToTwoDP(double decimalToRound) {
        return Math.round(decimalToRound * 100.0) / 100.0;
    }

    /**
     * Calculates the first root using the quadratic formula, by adding the discriminant.
     * Takes in the coefficient of the quadratic equation.
     */
    public static double getRoot1(int a, int b, int c) {
        return roundToTwoDP(((double) -b + (double) Math.sqrt(discriminant(a, b, c))) / (double) (2 * a));
    }

	/**
     * Calculates the second root using the quadratic formula, by subtracting the discriminant.
     * Takes in the coefficient of the quadratic equation.
     */
    public static double getRoot2(int a, int b, int c) {
        return roundToTwoDP(((double) -b - (double) Math.sqrt(discriminant(a, b, c))) / (double) (2 * a));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("+++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Welcome to the Root Calculator!");
        System.out.println("Enter integer values for a quadratic equation a, b and c");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        System.out.println("+++++++++++++++++++++++++++++++++++++\n");

        /*
         * write the method to show the quadratic equation this procedure should take in
         * the a, b and c values Level 2: determine if the equation is quadratic ...
         * a!=0 Level 3: display the quadratic in ax^2 + bx + c form Level 4: display no
         * extraneous signs .... not 2x^2 +-3x +-1 Level 4+: Do not display extraneous
         * ones, zeroes .... not 1x^2 +1x + 0
         */
        showQuadratic(a, b, c);

        // Write the method to find the discriminant D = b*b - 4*a*c
        int D = discriminant(a, b, c);
        System.out.println("The Discriminant is: " + D);

        // Write getRoot1 and getRoot2 that return the roots
        // Write a round method that you call in getRoot1 and getRoot2 to Round the
        // roots to two decimal places

    	if (D < 0) {
            System.out.println("This has no solution");
        } else if (D == 0) {
            System.out.println("This has one solution x=" + getRoot1(a, b, c));
        } else {
            System.out.println("This has two solutions x=" + getRoot1(a, b, c) + " and x=" + getRoot2(a, b, c));
        }
    }
}