import java.util.Scanner;
import java.lang.Math;

class Main {
  public static int gcd(int a, int b) {
  	int div = 0;

  	for (int i = 1; i <= Math.max(a, b); i++) {
  		if (a % i == 0 && b % i == 0)
  			div = i;
  	}

  	return div;
  }

  public static void main(String[] args) {
  	Scanner userInput = new Scanner(System.in);
    System.out.println(gcd(userInput.nextInt(), userInput.nextInt()));
  }
}