import java.util.Scanner;

class Main {
  public static boolean isLetter(char c) {
  	return (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122));
  }
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    System.out.println(isLetter(userInput.next().charAt(0)));
  }
}