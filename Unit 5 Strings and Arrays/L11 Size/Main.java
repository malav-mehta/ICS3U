// Ex. 407 Array Size
// Malav Mehta, ICS3U, Feb 10 2020

class Main {
	private static int size(int[][][] arr)
	{
		int size = 0;

		for (int i = 0; i < arr.length; i++)
		for (int j = 0; j < arr[i].length; j++)
			size += arr[i][j].length;

		return size;
	}

    public static void main(String[] args) {
    	// Test case
    	int[][][] arr = {{{1, 2, 3, 4, 5}, {1, 2, 3}, {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}}, {{1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}}, {{1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}}};
    	System.out.println(size(arr));
    }
}