/**
 * 101 INTRO TO METHODS
 * @author: Malav Mehta
 * @date: December 16, 2019
 */
public class Main {
    private static int rollDice() {
        int roll = (int) (Math.random() * 6 + 1);
        System.out.printf("You rolled a %d!\n", roll);
        return roll;
    }

    private static void roll2Die() {
        int dice1 = rollDice();
        int dice2 = rollDice();
        int total = dice1 + dice2;
        System.out.printf("%d and %d: a total of %d\n", dice1, dice2, total);

        System.out.printf("QUESTION 4.\nFrom the method 'ROLL TWO DIE', the first dice roll was %d and the second dice roll was %d, with the total being %d", dice1, dice2, total);
    }

    public static void main(String[] args) {
        // QUESTION 1
        System.out.println("QUESTION 1.\nA method definition is when a method is defined (i.e. what will happen when the method is called, what will be done with its parameters.\nMethod invocation is the event of calling the method, and passing valid parameters if needed.\n\n");

        // QUESTION 2
        System.out.println("QUESTION 2.\nThe verses are printed in the following order:\n2\n1\n3\n1\n\n");

        // QUESTION 3
        System.out.printf("QUESTION 3.\nFrom the method 'ROLL DICE', the following dice value was generated: %d\n\n\n", rollDice());

        // QUESTION 4
        roll2Die();

    }
}
