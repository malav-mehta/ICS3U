import java.util.Scanner;

class Main {
  // Try to use methods to break this code into blocks.
  // Method to get values
  // Method to calculate frequency (num of times num occurs)
  // Method to display info
  private static Scanner in = new Scanner(System.in);

  private static int[] addElement(int[] array, int element) {
    int[] newArray = new int[array.length + 1];
    for (int i = 0; i < array.length; i++)
      newArray[i] = array[i];

    newArray[array.length] = element;
    return newArray;
  }

  private static int[] getValues() {
    int counter = 0;
    int[] values = new int[0];
    while (true) {
      int score = in.nextInt();

      if (score < 0)
        break;
      else if (score < 10) {
        counter++;
        values = addElement(values, score);
      }
    }

    return values;
  }

  private static double mean(int[] array) {
    int elements = array.length;
    int sum = 0;

    for (int i = 0; i < elements; i++)
      sum += array[i];

    return (double) Math.round((double) sum / (double) elements * 10.0) / 10.0;
  }

  private static int calculateFrequency(int element, int[] array) {
  	int freq = 0;
  	for (int i = 0; i < array.length; i++)
  		if (array[i] == element)
  			freq++;

  	return freq;
  }

  private static void displayInfo(int[] array) {
    System.out.println("Score   # of Occurrences");
    for (int i = 0; i < 10; i++)
      System.out.printf("%-7d %d\n", i + 1, calculateFrequency(i + 1, array));
  }

  public static void main(String[] args) {
    int[] scores = getValues();
    displayInfo(scores);
    System.out.println("Mean: " + mean(scores));
  }
}