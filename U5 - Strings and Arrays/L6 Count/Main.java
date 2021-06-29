import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // DO NOT ALTER- this will get your numbers into an Array
    Scanner input = new Scanner(System.in);

    String s = input.nextLine();
    char c = input.next().charAt(0);

    System.out.println(count(c, s));

  }
  //modify the method below
  public static int count(char c, String str) {
  	int counter = 0;

  	for (int i = 0; i < str.length(); i++)
  		if (str.charAt(i) == c)
  			counter++;

  	return counter;
  }
}