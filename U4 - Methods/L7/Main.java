import java.util.Scanner;

class Main {
  public static boolean isPrime(int a) {
  	boolean isPrime = true;

  	if (a != 2) {
  		if (a % 2 == 0 || a < 0 || a == 1) isPrime = false;
  		else {
	  		for (int i = 3; i < a; i += 2) {
	  			if (a % i == 0) {
	  				isPrime = false;
	  				break;
	  			}
	  		}
  		}
  	}

  	return isPrime;
  }

  public static void main(String[] args) {
  	Scanner userInput = new Scanner(System.in);
    System.out.println(isPrime(userInput.nextInt()));
  }
}