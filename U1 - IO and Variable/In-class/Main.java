// Importing the scanner util in order to get user input.
import java.util.Scanner;

// Importing the math library for math operations (will be used as required)
import java.lang.Math;


class Main {
  public static void main(String[] args) {
  	// Assignment 1 – Input, Output, Variables and Math
  	// 12 days of Christmas song using custom variables
  	// by: Malav Mehta, Thursday, September 26, 2019

  	// Setting up a scanner in order to get user Input
  	Scanner userInput = new Scanner(System.in);

  	// Declaring all required variables (as per specifications outlined in the assignment document)
  	// a-g will be prompted from the user
  	// h-q will be calculated after a-g have been defined/initialized

  	int a, j, k, l, m; // integers
  	final String b; // constant strings
  	boolean c; // booleans
  	double d, i, o, p, q, h; // doubles
  	long e; // long integers
  	float f; // floats
  	char g; // characters
  	String n; // string (NOT constant)

  	// Prompting to get values for variables a-g, based on the variable type.

  	System.out.println("Enter an integer: ");
  	a = userInput.nextInt(); // Extracting an integer from the input stream through nextInt() method

  	System.out.println("Enter a string: ");
  	b = userInput.next(); // Extracting a string from the input stream through next() method

  	System.out.println("Enter a boolean: ");
  	c = userInput.nextBoolean(); // Extracting a boolean from the input stream through nextBoolean() method

  	System.out.println("Enter a double: ");
  	d = userInput.nextDouble(); // Extracting a double from the input stream through nextDouble() method

  	System.out.println("Enter a long integer: ");
  	e = userInput.nextLong(); // Extracting a long integer from the input stream through nextLong() method

  	System.out.println("Enter a float value: ");
  	f = userInput.nextFloat();  // Extracting a float from the input stream through nextFloat() method

  	System.out.println("Enter one character: ");
  	g = userInput.next().charAt(0); // Extracting a character from the input stream by keeping only the first character of the input recieved from next()

  	// Calculating the values of variables h-q, based on the outlined requirements.
  	h = Math.round(d * 100.0) / 100.0;
  	i = (double) Math.random() * ((2000 * a + 500) - (1000 * a) + 1) + 1000 * a;
  	j = (int) i;
  	k = (j - (j % 365)) / 365;
  	l = ((j - k * 365) - ((j - (k * 365)) % 7)) / 7;
  	m = (j - (k * 365) - (l * 7));
  	n = j + " days is " + k + " y/" + l + " w/" + m + "d";
  	o = (Math.sqrt(3 + Math.pow(a, 8) - 5 * a)) / 2 * a + 3;
  	p = (h / 2) + (Math.pow(f, 2) / (8 * h));
  	q = (2 * Math.atan(i / (2 * p))) * 180 / Math.PI;

  	// Ouputting the result
  	System.out.println("\n\nTwelve days of " + b + "\n\nOn the " + a + " day of " + b + ",\nMy " + c + " love gave to me:\n" + d + " drummers drumming\n" + e + " pipers piping\n" + f + " lords " + g + "-leaping\n" + h + " ladies dancing\n" + i + " maids " + g + "-milking\n" + j + " swans " + g + "-swimming\n" + k + " geese " + g + "-laying\n" + l + " golden rings\n" + m + " calling birds\n" + n + " french hens\n" + o + " turtle doves\n" + "And a " + q + " in a \"mixed-up\" tree.");
  }
}