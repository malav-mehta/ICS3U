class Main {
  public static void main(String[] args) {

   //1. An array to store marks for twenty students has been declared as follows:

	int[] marks = new int[20];

	//a) What is the array identifier?
	System.out.println("Question 1");
	System.out.println("a: The array identifier is 'marks'.");

	//b) What is the identifier of the first element in the array?
	System.out.println("b: marks[0]");

	//c) What value is stored in each array element, or position, by the declaration?
	System.out.println("c: The value is 0.");

	//d) What is the value of marks.length?
	System.out.println("d: 20");

	//e) What are the indices of the array? (i.e., the lowest and highest positions in the array)
	System.out.println("e: lowest is 0, highest is 19");

	 //2. Write declarations to create arrays that would be appropriate for storing the indicated data.
	//a) the number of votes cast for five candidates in an election
	int[] votes = new int[5];

	//b) the answers to a twenty-question true/false quiz
	boolean[] answers = new boolean[20];

	//c) the average family size in the years 1900, 1910, ..., 2000, 2010
	double[] familySize = new double[12];

	 //3. a) Write a statement that creates and initializes an array terms of double values to store the terms of the sequence
	double[] terms = new double[6];

	for (int i = 1; i <= 6; i++)
		terms[i - 1] = (double) i / (double) (i + 1);

	//b) What is the value of terms.length?
	System.out.println("\nQuestion 3\nb: 6");

	//4. The table gives atomic masses of the eight lightest elements listed according to atomic number.

	 double[] mass = {1, 4, 6.9, 9, 10.8, 12, 14, 16};

	//a) What is the value of mass[2]?
	System.out.println("\nQuestion 4\na: 6.9");

	//b) What is the value of mass[5]?
	System.out.println("b: 12");

	//c) What are the possible values of the indices of the array?
	System.out.println("c: 0 to 7");

	//d) What is the identifier of the element whose value is 6.9?
	System.out.println("d: mass[2]");

	//e) Of what type are the elements?
	System.out.println("e: They are doubles.");

	//f) What is the value of mass.length?
	System.out.println("f: 8");
  }
}