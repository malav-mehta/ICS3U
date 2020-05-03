import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Student[] students = new Student[4];
		Scanner in = new Scanner(System.in);
		String name, gender, address, parentEmail1, parentEmail2;
		int age, grade;
		long phoneNumber;

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

			System.out.print("Address: ");
			address = in.nextLine();

			System.out.print("Phone number: ");
			phoneNumber = in.nextLong();
			in.nextLine();

			System.out.print("Parent/Guardian #1 eMail: ");
			parentEmail1 = in.nextLine();

			System.out.print("Parent/Guardian #2 eMail: ");
			parentEmail2 = in.nextLine();

			students[i] = new Student(name, age, grade, gender, address, phoneNumber, parentEmail1,parentEmail2);
		}

		System.out.println("\nData is below: ");
		for (int i = 0; i < students.length; i++) {
			students[i].print();
			students[i].showPassword();
			System.out.println();
		}
		students[students.length - 1].copyAddress(students[0]);
		students[students.length - 1].print();
	}
}