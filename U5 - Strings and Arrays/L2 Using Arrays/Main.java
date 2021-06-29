class Main {
  public static void main(String[] args) {
    // Answer #1 in comments here
    /* 1: 4
     * 2: 0
     * 3: 3
     */

    // Write the code for number 2 here.
    int[] sample = new int[10];
    for (int i = 0; i < 10; i++)
    	sample[i] = 1;

    int temp = sample[0];
    sample [0] = sample[9];
    sample[9] = temp;

    for (int i = 0; i < 10; i++)
    	if (sample[i] < 0)
    		sample[i] = -sample[i];

    int sampleSum = 0;
    for (int i = 0; i < 10; i++)
    	sampleSum += sample[i];

    for (int i = 0; i < 10; i += 2)
    	System.out.println(sample[i]);
  }
}