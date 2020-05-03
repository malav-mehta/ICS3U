// Unit 2 (Selection) In Class Assignment
// Created by Malav Mehta, OCtober 28, 2019

// IMPORTING NECESSARY LIBRARIES
// Scanner for user input
import java.util.Scanner;
// Math for math operations
import java.lang.Math;


// Class through which the program will be run
public class Main {
  // Method through which the program will be run
  public static void main(String[] args) {
  	// DECLARING ALL NECESSARY VARIABLES (that will be used later in the IPO process)

  	// String to store the name of the team
  	String teamName;

  	// Integer values for integer points, and pX = points of player X
  	int p1, p2, p3, teamEquality, teamTotal, teamBonus, overallScore, teamSkillValues;

  	// Char to store rankings/classifications
  	// pcX = classification for player X
  	char pc1, pc2, pc3, teamSkill;

  	// Initializing a scanner to get user input from the IO stream
  	Scanner userInput = new Scanner(System.in);

  	// Prompt the user for the team name
  	System.out.print("Enter team name: ");
  	teamName = userInput.nextLine();

  	// Prompt the user for the team member ranks
  	System.out.print("Enter team member ranks:\n");

  	// ERROR CHECKING with TRY-CATCH
  	// Essentially, if any non-integer value is inputted, pX = 0 (and this is later declared invalid in the input)
  	// This works with any non-integer input, including floats, Strings, chars or longs

  	// Repeated for all pX
  	try {
  		p1 = userInput.nextInt();
  	} catch (Exception e) {
  		p1 = 0;
  	}

  	try {
  		p2 = userInput.nextInt();
  	} catch (Exception e) {
  		p2 = 0;
  	}

  	try {
  		p3 = userInput.nextInt();
  	} catch (Exception e) {
  		p3 = 0;
  	}

  	// Clossing the scanner (no need to get additional user input);
    userInput.close();

  	// If the integer is valid (in terms of its type, not value), then ERROR CHECK the value
  	// Check if the 0 < pX < 21 (since the values must be 1-20, inclusive)
  	if (p1 <= 0 || p1 > 20 || p2 <= 0 || p2 > 20 || p3 <= 0 || p3 > 20) {
  		System.out.println("Invalid input – please restart program to try agian."); // ERROR MESSAGE
  		System.exit(1); // QUIT THE PROGRAM if the input is not valid
  	}

  	// GENERATING PLAYER CLASSIFICATION
  	if (p1 < 10) pc1 = 'N'; // Player is guaranteed to be >= 1, so checking for < 10 checks if 1 <= pX <= 9 to see if the classification is N
  	else if (p1 < 16) pc1 = 'M'; // Player is guaranteed to be >= 10, so checking for < 16 checks if 10 <= pX <= 15 to see if the classification is M
  	else pc1 = 'F'; // Otherwise since the input is guaranteed to be < 20, the pX must be such that 16 <= pX <= 20

  	// Same logic for p2 and p3 as with p1
  	if (p2 < 10) pc2 = 'N';
  	else if (p2 < 16) pc2 = 'M';
  	else pc2 = 'F';

  	if (p3 < 10) pc3 = 'N';
  	else if (p3 < 16) pc3 = 'M';
  	else pc3 = 'F';

  	// GENERATING TEAM EQUALITY classification
  	teamEquality = 3; // If no player classifications are equal, then teamEquality starts at 3
  	if (pc1 == pc2 || pc1 == pc3 || pc2 == pc3) {
  		teamEquality--; // If any 2 are equal, teamEquality-- changes the value to 2
  		if (pc1 == pc2 && pc1 == pc3) {
  			teamEquality--; // If all 3 are equal, than teamEquality change the value to 1 from 2
  		}
  	}

  	// GENERATING TEAM SKILL classification
  	// First, finding the team total
  	teamTotal = p1 + p2 + p3;

  	if (teamTotal <= 25) teamSkill = 'B'; // Since pX is guaranteed to be between 1 and 20 inclusive, this checks if 3 <= teamTotal <= 25
  	else if (teamTotal <= 45) teamSkill = 'A'; // Now, pX is guaranteed to be >= 26, so this checks if 26 <= teamTotal <= 45
  	else teamSkill = 'E'; // Otherwise, teamTotal is guaranteed to be >= 46 and <= 60, so teamSkill is 'E' (since pX can be 20 max)

  	// CALCULATING TEAM BONUS
  	// If teamEquality is 3, that is to say all players are from different skill levels, than the teamBonus is automatically 20, and no other checks required
  	if (teamEquality == 3) teamBonus = 20;
  	else { // Otherwise, the teamSkillValues variable needs to be initialized first, based on the requirements stated in the doc
  		if (teamSkill == 'B') teamSkillValues = 5;
  	  	else if (teamSkill == 'A') teamSkillValues = 4;
  	  	else teamSkillValues = 2; // If it's not B or A, then teamSkill is E, so teamSkillValues is 2

  	  	// Calculating the team bonus, by ensuring that decimals (floating point values) exist through casting all integer variables in the calculation to floating point variables (where required)
  	  	teamBonus = (int) Math.round((teamSkillValues * teamEquality / 3.0) * ((float) Math.sqrt( p1 +  p2 +  p3)));
  	}

  	// Calcualting the overall score as required: total + bonus
  	overallScore = teamTotal + teamBonus;


  	// Ouputting all values as per specification
    System.out.print("Player Classification: " + pc1 + ", " + pc2 + ", " + pc3 + "\nTeam Equality Classification: " + teamEquality + "\nTeam Skill Classification: " + teamSkill + "\nTeam Total: " + teamTotal + "\nTeam Bonus: " + teamBonus + "\n" + teamName + " Overall Score: " + overallScore);
  }
}