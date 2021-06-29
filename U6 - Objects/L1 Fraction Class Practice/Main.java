class Fraction {
	int num;
	int den;
}

class Main {
	public static void main(String[] args) {
		// Q1
		Fraction q1 = new Fraction();
		q1.num = 2;
		q1.den = 7;

		// Q2
		// The first statement declares the variable type, but the variable is never
		// initialized and 'p' does not hold an object until it is created.

		// Q3
		// p contains 1/3: its num and den were assigned 2, but 1 was removed and added
		// from the num and den respectively.
		// q and r contain 4/3: q's num was assigned twice the value of p's den before the
		// addition of 1 and r' den was assigned two more than p's num value after a
		// reduction by 1. Since they both hold the same memory location, changes to one
		// of them changes both of them.

		// Q4
		Fraction f1 = new Fraction(), f2 = new Fraction();

		// a)
		f1.num *= 2;

		// b)
		int temp = f2.num;
		f2.num = f2.den;
		f2.den = temp;

		// c)
		f1.num *= f2.num;
		f1.den *= f2.den;

		// d)
		f2.num *= f1.den;
		f1.num *= f2.den;
		f2.num += f1.num;
		f2.den *= f1.den;
	}
}