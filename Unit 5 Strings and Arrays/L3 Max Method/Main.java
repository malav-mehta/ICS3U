import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // DO NOT ALTER - this will get your numbers into an Array
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    double[] temp = new double[n];

    for (int x=0; x<n; x++)
      temp[x] = s.nextDouble();

    // Call max
    System.out.println(max(temp));
  }

  private static double max(double[] array) {
  	double temp = array[0];

  	for (int i = 1; i < array.length; i++)
  		if (array[i] > temp)
  			temp = array[i];

  	return temp;
  }
}