// Ex. 406 Array Max
// Malav Mehta, ICS3U, Feb 10 2020

class Main {
	private static int max(int[][] arr) {
		int max = arr[0][0];

		for (int i = 0; i < arr.length; i++)
		for (int j = 0; j < arr[i].length; j++)
			if (arr[i][j] >= max)
				max = arr[i][j];

		return max;
	}

    public static void main(String[] args) {
    	// Test case
    	int[][] arr = {{123, 23456, 23, 34, 1234, 43, 2344, 454}, {1343, 34332}, {-1234, 452, 3532}, {1234, 4533, 222}};
    	System.out.println(max(arr));
    }
}