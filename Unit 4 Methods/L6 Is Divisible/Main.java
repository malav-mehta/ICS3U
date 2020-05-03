import java.util.Scanner;

class Main {
  public static boolean isDivisible(int a, int b) {
  	return a % b == 0;
  }
  public static void main(String[] args) {
  	Scanner userInput = new Scanner(System.in);
    System.out.println(isDivisible(userInput.nextInt(), userInput.nextInt()));
  }
}