import java.lang.Math;

class Fraction {
  private int NUM;
  private int DEN;

  public int getNum() { return this.NUM; }
  public int getDen() { return this.DEN; }
  public void setNum(int num) { this.NUM = num; }
  public void setDen(int den) { this.DEN = den; }

  Fraction(int num, int den) {
    this.NUM = num;
    this.DEN = den;
  }

  Fraction() {
  	this.NUM = 0;
  	this.DEN = 0;
  }

  public String toString() {
    return this.NUM + "/" + this.DEN;
  }

  public double size() {
    return Math.abs((double) this.NUM / this.DEN);
  }

  public boolean isLargerThan(Fraction other) {
    return this.size() >= other.size();
  }

  public Fraction larger(Fraction other) {
    return this.isLargerThan(other) ? this : other;
  }

  public void timesEquals(Fraction p) {
    this.NUM *= p.getNum();
    this.DEN *= p.getDen();
  }

  public Fraction times(Fraction p) {
    Fraction product = new Fraction();
    product.setNum(this.NUM * p.getNum());
    product.setDen(this.DEN * p.getDen());
    return product;
  }

  public void plusEquals(Fraction other) {
    this.NUM *= other.getDen();
    this.NUM += other.getNum() * this.DEN;
    this.DEN *= other.getDen();
  }

  public Fraction plus(Fraction other) {
    Fraction sum = new Fraction();
    sum.setNum(this.NUM * other.getDen() + other.getNum() * this.DEN);
    sum.setDen(this.DEN * other.getDen());
    return sum;
  }

  private int GCF(int a, int b) {
    return b == 0 ? a : GCF(b, a % b);
  }

  public void reduce() {
    int reductionFactor = GCF(this.NUM, this.DEN);
    this.NUM /= reductionFactor;
    this.DEN /= reductionFactor;
  }

  // a)
	public static Fraction product(Fraction a, Fraction b) {
		Fraction prod = new Fraction(a.getNum() * b.getNum(), a.getDen() * b.getDen());
		prod.reduce();
		return prod;
	}

	// b)
	public static Fraction abs(Fraction p) {
		p.setNum(Math.abs(p.getNum()));
		p.setDen(Math.abs(p.getDen()));
		return p;
	}

	public static boolean isPositive(Fraction p) {
		int count = 0;
		if (p.getNum() < 0)
			count++;

		if (p.getDen() < 0)
			count++;

		return count % 2 == 0;
	}
}

class Main {
  public static void main(String[] args) {
    // TESTS
    Fraction p = new Fraction(-3, 4);
    Fraction q = new Fraction (3, -4);

    System.out.println(Fraction.product(p, q)); // a)
    System.out.println(Fraction.abs(p)); // b)
    System.out.println(Fraction.isPositive(q)); // c)
  }
}