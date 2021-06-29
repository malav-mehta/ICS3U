import java.util.Random;

class Student {
	String name;
	int age;
	int grade;
	String gender;
	String address;
	long phoneNumber;
	String parentEmail1;
	String parentEmail2;
	String password;

	public String getAddress() { return this.address; }
	public long getPhoneNumber() { return this.phoneNumber; }
	public String getParentEmail1() { return this.parentEmail1; }
	public String getParentEmail2() { return this.parentEmail2; }

	public Student(String name, int age, int grade, String gender, String address, long phoneNumber, String parentEmail1, String parentEmail2) {
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.parentEmail1 = parentEmail1;
		this.parentEmail2 = parentEmail2;
		this.createStudentPassword();
	}

	public Student() {
		this("student", 14, 9, "other", "n/a", 1234567890, "none", "none");
	}

	public Student(String name) {
		this(name, 14, 9, "other", "n/a", 1234567890, "none", "none");
	}

	public Student(String name, int grade, int age, char gender, String password) {
		this(name, age, grade, Character.toString(gender), "n/a", 1234567890, "none", "none");
		this.password = password;
	}

	private char nextChar() {
		Random rand = new Random();
		switch(rand.nextInt(3)) {
			case 0:
				return (char) (rand.nextInt(26) + 65);
			case 1:
				return (char) (rand.nextInt(26) + 97);
			default:
				return (char) (rand.nextInt(10) + 48);
		}

	}

	private void createStudentPassword() {
		this.password = "";
		for (int i = 1; i <= 8; i++)
			this.password += this.nextChar();
	}

	public void showPassword() {
		System.out.println("The password is: " + this.password);
	}

	public void print() {
		System.out.printf("%s is a %d year-old %s student in grade %d.\n", name, age, gender, grade);
		System.out.printf("Address:\t %s\n", address);
		System.out.printf("Parent 1:\t %s\t\t\tParent 2: %s \n", parentEmail1, parentEmail2);
		System.out.printf("Password:\t %s\n", "********");
	}

	public void copyAddress(Student other) {
		this.address = other.getAddress();
		this.phoneNumber = other.getPhoneNumber();
		this.parentEmail1 = other.getParentEmail1();
		this.parentEmail2 = other.getParentEmail2();
	}
}

class Main {
  public static void main(String[] args) {
	// Instance of Student using default constructor
	Student s1 = new Student();

	// Instance of Student using constuctor 2
	Student s2 = new Student("William Gates", 11, 16, 'M', "Apassword14555");

	// Instance of Student using constructor 3
	Student s3 = new Student("Steven Jobs");

	// Call your print method to display s1,s2, and s3
	s1.print();
	System.out.println();
	s2.print();
	System.out.println();
	s3.print();
  }
}