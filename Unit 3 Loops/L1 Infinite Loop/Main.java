import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    //define variables
    Scanner userInput = new Scanner(System.in);
    int age;

    System.out.println("\nEnter your guess between 1 and 10 : ");
    age = userInput.nextInt();

    while (age < 1 || age > 10) {
      System.out.println("Invalid guess");
      System.out.println("\nEnter your guess between 1 and 10 : ");
      age = userInput.nextInt();
    }
  System.out.println("Valid guess entered");

  }
}