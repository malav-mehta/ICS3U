class Child {
	private int height;
	private double mass;

	Child(int height, double mass) {
		this.height = height;
		this.mass = mass;
	}

	public boolean equals(Child other) {
		return ((Math.abs(this.height - other.height) <= 2 && Math.abs(this.mass - other.mass) <= 0.5));
	}

	private boolean goodBMI() {
		return (mass * 10000 / height / height) > 18;
	}

	public String toString() {
		return "Child - " + (goodBMI() ? "good" : "bad");
	}
}

class Main {
  public static void main(String[] args) {
    Child test = new Child(165, 45);
    Child test2 = new Child(165, 55);
    System.out.println(test);
    System.out.println(test2);
  }
}