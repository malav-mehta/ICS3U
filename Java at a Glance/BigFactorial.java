/*
 * Created by:  Malav Mehta
 * For:         Java At a Glance (Carleton University)
 * On:          January 11, 2019
 *
 * This program was created as a solution to the Midterm Assignment for the 2019-2020
 * Java@Glance program. The assignment was outlined as follows:
 *    1) EASY: Use the BigInteger class to print 2020! and calculate the number of
 *       zeroes at its end.
 *    2) HARD: Do the task outlined in 1) without using the BigInteger class.
 *    3) PREMIUM: Calculate the zeroes at the end of 2020! without actually calculating
 *       it value.
 *
 * To solve this problem, the BigFactorial class was created. It is capable of
 * calculating up to approximately (1.024819e17)! (THEORETICAL ESTIMATE, NOT TESTED).
 *
 * Compilation: $ javac BigFactorial.java
 * Execution:   $ java BigFactorial
 *
 * SAMPLE RUN:
 * This program will output its factorial and the zeroes at its end.
 * Enter an INT or LONG value below. Enter -1 to quit.
 * > 2020
 * FACTORIAL LIMIT: 2020
 * Method        | Zero Count    | Calculated Factorial (2020!)
 * --------------|---------------|-------------------------------------------
 * Big Integer   | 503           | 386096...0
 * --------------|---------------|-------------------------------------------
 * Big Factorial | 503           | 386096...0
 * --------------|---------------|-------------------------------------------
 * Calculated    | 503           | NOT APPLICABLE (zeroes were calculated)
 *
 * Enter an INT or LONG value below. Enter -1 to quit.
 * > -1
 * ENDING PROGRAM...
 */

import java.math.BigInteger;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;


public class BigFactorial {
    // The Factorial will be stored in an expandable ArrayList variable, with variable at index 0 given place value 1, at index 1 given place value 2 etc.
    public ArrayList<Long> NUMBER = new ArrayList<>();

    // Factorial with BigInteger
    private BigInteger FACTORIAL = BigInteger.ONE;

    private long FACTORIAL_LIMIT = 1;
    private long ZEROES_COUNTED = 0;
    private long ZEROES_BIG_INTEGER = 0;
    private long ZEROES_CALCULATED = 0;

    // STRING, INT and LONG constructors are available
    private BigFactorial(String N) {
        long factorialLimit = 1L;

        try {
            if (N.charAt(0) == '-')
                N = N.substring(1);

            factorialLimit = Long.parseLong(N);
        } catch (NumberFormatException nfe) {
            System.err.println("NUMBER FORMAT EXCEPTION " + nfe.getMessage());
            System.out.println("Setting the factorial limit to 1");
        } finally {
            initializeClass(factorialLimit);
        }
    }

    private BigFactorial(int N) {
        long factorialLimit = Math.abs(N);
        initializeClass(factorialLimit);
    }

    private BigFactorial(long N) {
        initializeClass(Math.abs(N));
    }

    // Each constructor points to this method to initialize the object
    private void initializeClass(long N) {
        FACTORIAL_LIMIT = N;
        NUMBER.add(1L);
        findFactorial();
    }

    // For printing the factorial
    public String toString() {
        String NUMBER_STRING = "";

        for (int i = NUMBER.size() - 1; i >= 0; i--)
            NUMBER_STRING += NUMBER.get(i);

        return NUMBER_STRING;
    }

    private void findFactorial() {
        long startTime = System.nanoTime();
        calculateFactorial();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration / 1000000 + "ms for BigFactorial");

        long startTimeBI = System.nanoTime();
        bigIntegerFactorial();
        long endTimeBI = System.nanoTime();

        long durationBI = (endTimeBI - startTimeBI);  //divide by 1000000 to get milliseconds.
        System.out.println(durationBI / 1000000 + "ms for BigInteger\n");

        ZEROES_COUNTED = countZeroes();
        ZEROES_BIG_INTEGER = countBigIntegerZeroes();
        ZEROES_CALCULATED = calculateZeroes();
    }

    // Calculate factorial iteratively
    private void calculateFactorial() {
        for (long i = 2; i <= FACTORIAL_LIMIT; i++)
            this.multiply(i);
    }

    // Calculate factorial with BigInteger
    private void bigIntegerFactorial() {
        for (long i = 2; i <= FACTORIAL_LIMIT; i++)
            FACTORIAL = FACTORIAL.multiply(BigInteger.valueOf(i));
    }

    private void add(BigFactorial addend) {
        long temp;

        if (NUMBER.size() >= addend.NUMBER.size()) {
            for (int i = 0; i < NUMBER.size(); i++) {
                try {
                    NUMBER.set(i, NUMBER.get(i) + addend.NUMBER.get(i));
                } finally {}
            }
        }

        for (int i = 0; i < NUMBER.size(); i++) {
        temp = NUMBER.get(i);
        if (temp >= 10) {
            NUMBER.set(i, temp % 10);
            if (i != NUMBER.size() - 1)
                NUMBER.set(i + 1, NUMBER.get(i + 1) + temp / 10);
            else
                NUMBER.add(temp / 10);
        }
    }
    }

    // Multiply method used for calculating factorial
    private void multiply(long multiplier) {
        long temp;

        // Each digit of the number is multiplied by the multiplier
        for (int i = 0; i < NUMBER.size(); i++)
            NUMBER.set(i, NUMBER.get(i) * multiplier);

        // The array is then normalized (i.e. each element cannot exceed a value of 9 (available digits are 0-9 for each position)
        // Excess from each element is carried-forward to the next element (i.e. instead of writing 15 * 2 as 210, it must be written as 30)
        for (int i = 0; i < NUMBER.size(); i++) {
            temp = NUMBER.get(i);
            if (temp >= 10) {
                NUMBER.set(i, temp % 10);
                if (i != NUMBER.size() - 1)
                    NUMBER.set(i + 1, NUMBER.get(i + 1) + temp / 10);
                else
                    NUMBER.add(temp / 10);
            }
        }
    }

    // Count zeroes from the ArrayList by counting the number of zeroes at its beginning until a non-zero element is encountered
    private long countZeroes() {
        long zeroes = 0;
        for (long i : NUMBER) {
            if (i == 0L)
                zeroes++;
            else
                break;
        }
        return zeroes;
    }

    // Same method as above, but using the string version of the BigInteger factorial result
    private long countBigIntegerZeroes() {
        long zeroes = 0;
        String factorial = FACTORIAL.toString();

        for (int i = factorial.length() - 1; i >= 0; i--) {
            if (factorial.charAt(i) == '0')
                zeroes++;
            else
                break;
        }

        return zeroes;
    }

    // Calculate zeroes without calculating factorial
    private long calculateZeroes() {
        long zeroes = 0;
        int counter = 0;
        long factor;

        // Multiples of 5^n add n zeroes at the end of factorial
        // Iterative logic: add all zeroes for 5^1 then add all zeroes for 5^2 etc until when 5^n exceeds the FACTORIAL_LIMIT
        while (true) {
            counter++;
            factor = (long) Math.pow(5, counter);
            if (FACTORIAL_LIMIT % factor == FACTORIAL_LIMIT)
                break;
            else
                zeroes += FACTORIAL_LIMIT / factor;
        }

        return zeroes;
    }

    // Getters for variables that need access from outside the object
    private long getFactorialLimit() { return FACTORIAL_LIMIT; }
    private long getCountedZeroes() { return ZEROES_COUNTED; }
    private long getCountedZeroes_BigInteger() { return ZEROES_BIG_INTEGER; }
    private long getCalculatedZeroes() { return ZEROES_CALCULATED; }
    private String getFactorial_BigInteger() { return FACTORIAL.toString(); }

    public static void main(String[] args) {
        String factorialLimit;
        Scanner in = new Scanner(System.in);
        System.out.println("This program will output its factorial and the zeroes at its end.");

        while (true) {
            System.out.print("Enter an INT or LONG value below. Enter -1 to quit.\n> ");
            factorialLimit = in.next();
            in.nextLine();

            if (factorialLimit.equals("-1")) {
                System.out.println("ENDING PROGRAM...");
                break;
            } else {
                BigFactorial f = new BigFactorial(factorialLimit);

                // Print results in Table Format
                System.out.printf("FACTORIAL LIMIT: %s\n", f.getFactorialLimit());
                System.out.printf("Method        | %-14s| Calculated Factorial (%s!)\n", "Zero Count",f.getFactorialLimit());
                System.out.print ("--------------|---------------|-------------------------------------------\n");
                System.out.printf("Big Integer   | %-14s| %s\n", f.getCountedZeroes_BigInteger(), f.getFactorial_BigInteger());
                System.out.print ("--------------|---------------|-------------------------------------------\n");
                System.out.printf("Big Factorial | %-14s| %s\n", f.getCountedZeroes(), f.toString());
                System.out.print ("--------------|---------------|-------------------------------------------\n");
                System.out.printf("Calculated    | %-14s| %s\n\n", f.getCalculatedZeroes(), "NOT APPLICABLE (zeroes were calculated)");
            }
        }
    }
}
