import java.util.Scanner;

class Main {
  public static double largest(double a, double b, double c) {
  	double largest = a;
  	if (b > largest)
  		largest = b;
  	if (c > largest)
  		largest = c;

  	return largest;
  }
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    // Getting user input
    System.out.println(largest(userInput.nextDouble(), userInput.nextDouble(), userInput.nextDouble()));
  }
}