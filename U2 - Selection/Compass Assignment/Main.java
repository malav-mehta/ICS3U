// Compass Assignment (Level 4+)
// Created by Malav Mehta, October 23, 2019

// Importing the Scanner for getting user input

import java.util.Scanner;

// Main class through which the program will be executed
class Main {
	// Main method for the program's code
	public static void main(String[] args) {
		// Initiating a scanner to get user input from the IO stream
		Scanner userInput = new Scanner(System.in);

		// Declaring variables that will be used throughout the method
		boolean wantsConversion = true;
		int bearing, angle;
		char dir1, dir2, choice, conversion;
		String direction, compass;

		// Setting a loop so that multiple conversions are possible
		while(wantsConversion) {
			// Getting choice (as char so that even if the input is not an "int" then the program is able to validate it)
			System.out.print("\n(1) convert bearing to compass\n(2) convert compass to bearing\nChoose: ");
			choice = userInput.next().charAt(0);

			switch(choice) {
				case '1':
					// Getting the bearing using a Scanner for user input
					System.out.print("Enter bearing: ");

					// Validating input: if the input is not of type int, then bearing is set to -1, and the code below determines this input as invalid.
					try {
						bearing = userInput.nextInt();
					} catch (Exception e) {
							bearing = -1;
					}

					// DETERMINING THE CLOSEST DIRECTION
					// Only doing other checks if the bearing is not invalid
					if (!(bearing < 0 || bearing > 359)) {
						// Since bearing is valid, all cases are checked below
						if (bearing <= 45)			direction = "N" + bearing + "E";		 // bearing < 45, bearing N_E; bearing == 45, bearing N45E (45 omitted later in the code)
						else if (bearing < 90)		direction = "E" + (90 - bearing) + "N";  // bearing !< 45 and !>90 means it's closest to E and is towards N
						else if (bearing < 135) 	direction = "E" + (bearing - 90) + "S";  // bearing !< 90 and !>135 means it's closest to E and is towards S
						else if (bearing < 180) 	direction = "S" + (180 - bearing) + "E"; // bearing !< 135 and !>90 means it's closest to E and is towards N
						else if (bearing <= 225)	direction = "S" + (bearing - 180) + "W"; // bearing !< 45 and !>90 means it's closest to E and is towards N
						else if (bearing < 270) 	direction = "W" + (270 - bearing) + "S"; // bearing !< 45 and !>90 means it's closest to E and is towards N
						else if (bearing < 315) 	direction = "W" + (bearing - 270) + "N"; // bearing !< 45 and !>90 means it's closest to E and is towards N
						else						direction = "N" + (360 - bearing) + "W"; // bearing !< 45 and !>90 means it's closest to E and is towards N

						if (direction.charAt(1) == '0') 				 direction = Character.toString(direction.charAt(0));
						else if (direction.substring(1, 3).equals("45")) direction = Character.toString(direction.charAt(0)) + Character.toString(direction.charAt(3));
					} else direction = "invalid"; // If bearing is valid, then state that the direction = "invalid", and don't check all cases.

					// Outputting direction
					if (direction == "invalid") direction = "Invalid - please enter valid choice";
					else direction = "Bearing " + bearing + " is " + direction;
					break;
				case '2':
					// Getting the compass direction
					System.out.print("Compass direction: ");
					compass = userInput.next();

					// Preparing the output string (without the bearing)
					direction = "Compass " + compass.toUpperCase() + " is a bearing of ";

					if (compass.length() < 5 && compass.length() != 0) {
						if (compass.length() == 1) {
							dir1 = compass.toLowerCase().charAt(0);
							angle = 0;
							if (dir1 == 'n') dir2 = 'e';
							else if (dir1 == 'e') dir2 = 's';
							else if (dir1 == 's') dir2 = 'w';
							else dir2 = 'n';
						} else if (compass.length() == 2) {
							dir1 = compass.toLowerCase().charAt(0);
							angle = 45;
							dir2 = compass.toLowerCase().charAt(compass.length() - 1);
						} else {
							dir1 = compass.toLowerCase().charAt(0);
							try {
								angle = Integer.parseInt(compass.substring(1, (compass.length() - 1)));
							} catch (Exception e) {
								angle = -1;
							}
							dir2 = compass.toLowerCase().charAt(compass.length() - 1);
						}
						// Checking the invalid case: when the input is not a one letter compass direction || when the inputted directions are not N, E, S, W (in length or in character) || 0 <= angle < 45 || same directions (N0N, W45W) || opposite directions E and W or S and N
						if ((compass.length() > 4) || (dir1 != 'n' && dir1 != 'e' && dir1 != 's' && dir1 != 'w') || (angle < 0 || angle > 45) || (dir2 != 'n' && dir2 != 'e' && dir2 != 's' && dir2 != 'w') || (dir1 == dir2) || ((dir1 == 'n' || dir1 == 's') && (dir2 == 'n' || dir2 == 's')) || ((dir1 == 'e' || dir1 == 'w') && (dir2 == 'e' || dir2 == 'w'))) direction = "Invalid - please enter valid choice";
						else { // If not invalid, then bearing must be calculated
							if (dir1 == 'n') { // If the first direction is A, being a quadrant angle, then B = 0/360 for N, 90 for E, 180 for S or 270 for W
								if (angle == 0) 	  bearing = 0;
								else if (dir2 == 'e') bearing = angle;       // bearing is either B + angle
								else 				  bearing = 360 - angle; // or bearing is either B - angle
							} else if (dir1 == 'e') { // Same logic applied for each cardinal direction (compass direction)
								if (dir2 == 'n')	  bearing = 90 - angle;
								else 				  bearing = 90 + angle;
							} else if (dir1 == 's') {
								if (dir2 == 'e')	  bearing = 180 - angle;
								else 				  bearing = 180 + angle;
							} else {
								if (dir2 == 's')	  bearing = 270 - angle;
								else 				  bearing = 270 + angle;
							}
							direction += Integer.toString(bearing); // Add the bearing to the direction (output) string
						}
					} else {
						direction = "Invalid - please enter valid choice";
					}
					break;
				default:
					direction = "Invalid - please enter valid choice"; // If the choice != 1 or 2
					break;
			}

			System.out.println(direction); // Print output at the end

			// Ask for another conversion
			System.out.print("\nDo another conversion? y/n: ");
			conversion = userInput.next().toLowerCase().charAt(0);
			if (conversion == 'n') wantsConversion = false;
		}

		// Closing the program with bye message and closing the Scanner
		System.out.println("Goodbye.");
		userInput.close();
	}
}