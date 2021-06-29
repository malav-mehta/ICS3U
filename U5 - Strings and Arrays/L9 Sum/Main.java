// Ex. 405 Sum
// Malav Mehta, ICS3U, Feb 10, 2020

class Main {
	private static double sum(double[][] arr) {
		double finalSum = 0;

		for (int i = 0; i < arr.length; i++)
		for (int j = 0; j < arr[0].length; j++)
			finalSum += arr[i][j];

		return finalSum;
	}

	public static void main(String[] args) {
		// Test case
		double[][] arr = {{0.4, 5.12, 5.42, 34.3}, {4.5, 6.2, 7.1, 34.67}, {3, 5, 32.3, 55.2}};
		System.out.println(sum(arr));
	}
}