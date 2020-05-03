import java.util.Scanner;

class Student {
	private String name;
	private int age;
	private int grade;
	private String gender;

	Student(String name, int age, int grade, String gender) {
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.gender = gender;
	}

	public String toString() {
		return String.format("%s is a %d year-old %s student in grade %d.", name, age, gender, grade);
	}
}

class Main {
	public static void main(String[] args) {
		Student[] students = new Student[4];
		Scanner in = new Scanner(System.in);
		String name, gender;
		int age, grade;

		for (int i = 0; i < students.length; i++) {
			System.out.println("\nTaking data for student number " + (i + 1) + ".");
			System.out.print("Name: ");
			name = in.nextLine();

			System.out.print("Age: ");
			age = in.nextInt();
			in.nextLine();

			System.out.print("Grade: ");
			grade = in.nextInt();
			in.nextLine();

			System.out.print("Gender: ");
			gender = in.nextLine();

			students[i] = new Student(name, age, grade, gender);
		}

		System.out.println("\nData is below: ");
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i]);
		}
	}
}