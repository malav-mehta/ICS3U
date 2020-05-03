/*
 * Created by: Malav Mehta
 * For:        ICS3U, Mr. Hudson
 * On:         February 16, 2020
 *
 * This program was created as a solution to the U5 Challenge for ICS3U in the
 * 2019-2020 academic year. The challenge was outlined as follows:
 *
 * The picture above represents a room, width x cm, height y cm and depth z cm.
 * On one wall, width x and height y sits a spider, 100 cm from the ceiling and
 * 100 cm from the left wall. On the opposite wall sits a fly, 100 cm from the
 * floor and 100 cm from the right wall. Unfortunately for the fly, it is asleep
 * and unaware of the spider. The spider intends to crawl along any of the four
 * walls, the ceiling or the floor in order to catch the fly.
 *
 * Find the shortest distance the spider must travel in order to get to the fly.
 * Of course, the shortest path depends on the values of x, y and z: The shortest
 * path may involve crawling along the ceiling, or along the floor, or along one
 * of the side walls, or even along five of the six different surfaces.
 *
 * The data contains 5 sets of 3 integers on 5 separate lines (See the example
 * below). The three numbers are separated by one space and represent the x, y, z
 * values in centimetres. Each number is between 101 and 500.
 *
 *
 * Sample Input
 * 326 227 219
 * 487 258 371
 * 347 353 372
 * 102 177 425
 * 149 113 450
 *
 * Sample output
 * for (326 , 227 , 219) the shortest distance is: 463
 * for (487 , 258 , 371) the shortest distance is: 691
 * for (347 , 353 , 372) the shortest distance is: 735
 * for (102 , 177 , 425) the shortest distance is: 512
 * for (149 , 113 , 450) the shortest distance is: 543
 */

// Scanner for getting input
import java.util.Scanner;

// For mathematical operations
import java.lang.Math;

public class Main {
  // Scanner declaration for getting input from the IO stream
  private static Scanner in = new Scanner(System.in);

  // Class-scoped variables to hold the dimensions of the room
  private static int x = 0, y = 0, z = 0;

  /**
   * Used for collecting the room's dimensions from the input stream and then
   * transferring this data into the class-scoped x, y and z variables.
   */
  private static void get_dimensions() {
    x = in.nextInt();
    y = in.nextInt();
    z = in.nextInt();
  }

  /**
   * Used for returning the minimum value of an array, using an extremely simple
   * searching method with the use of Math.min to check every element of the
   * array.
   *
   * @param array takes in the array whose minimum will be found
   * @return the minimum value of that array
   */
  private static int array_min(int[] array) {
    // Start by equating the minimum value to the first element
    int min_value = array[0];

    // For each element in the array, set the min value to be the min between it and
    // the element from the array that is being look at in the current iteration
    for (int i : array)
      min_value = Math.min(min_value, i);

    // Return the min_value after passing through the array
    return min_value;
  }

  /**
   * Uses the Pythagoras Theorem in order to calculate the hypotenuse of a given right
   * triangle when the sides are entered as parameters. This function is used to find
   * the shortest (diagonal distance) between faces on the net (2D representation) of
   * the room.
   *
   * @param leg_1 takes in the first leg (or the a value)
   * @param leg_2 takes in the second leg (or the b value)
   * @return returns the calculated hypotenuse: rnd(sqrt(a^2 + b^2))
   */
  private static int get_diagonal(int leg_1, int leg_2) {
    return (int) Math.round(Math.sqrt(leg_1 * leg_1 + leg_2 * leg_2));
  }

  public static void main(String[] args) {
    // Array holding the different paths that will be calculated and considered for
    // determining the shortest path between the spider (S) and fly (F)
    int[] paths = new int[8];

    // The amount of cases that will be looked at; as defined by the problem, there
    // will be 5 sets of integers that will be inputted
    int cases = 1;

    // The horizontal and vertical distances between the spider (S) and fly (F) on the
    // 2D representation of the room (these will be input to find the diagonal)
    int horizontal, vertical;

    // Run the program 'cases' amount of times
    for (int i = 1; i <= cases; i++) {
      // New dimensions are set for each iteration
      get_dimensions();

      /*
       * The logic behind the program is as described here. There are 16 distinct paths
       * that the spider (hereon referred to as S) can take to reach the fly (hereon
       * referred to as F). These paths are found by creating a 2D representation of the
       * room by forming the net of the rectangular prism. In doing so, the problem can
       * be solved in 2 dimensions by considering the shortest 2D distance between S and
       * F as calculated from their net.
       *
       * Below is a visualisation of the net:
       * +---------------+----+
       * | S   |   1     | F  | x
       * |     |         |    |
       * +--------------------+
       *       |   2     |  y
       *       |         |
       *       |         |
       *       |     z   |
       *       +---------+
       *       |   3     |
       *       |         |
       *       +---------+
       *       |   4     |
       *       |         |
       *       |         |
       *       |         |
       *       +---------+
       *
       * NOTE: As labelled, S (spider) and F (fly) will be on opposing sides, with S at the
       * front and F at the back. The other faces of the cube are labelled 1-4, as shown in
       * the diagram.
       *
       * X-Y means that X (being either S or F) will have its face connected to face Y (being
       * any number from 1-4, as shown above).
       *
       * HORIZONTAL distance is from LEFT to RIGHT.
       * VERTICAL distance is from TOP to BOTTOM.
       * ^ These values are determined by inspecting the above diagram.
       */

      // Case 1: S-1 & F-1 or S-3 & F-3
      horizontal = x + z;
      vertical = Math.abs(y - 200);
      paths[0] = get_diagonal(horizontal, vertical);

      // Case 2: S-2 & F-2 or S-4 & F-4
      horizontal = y + z;
      vertical = Math.abs(x - 200);
      paths[1] = get_diagonal(horizontal, vertical);

      // Case 3: S-1 & F-2 [S-1 & F-3 not considered: always longer]
      horizontal = y + z;
      vertical = x;
      paths[2] = get_diagonal(horizontal, vertical);

      // Case 4: S-2 & F-1
      horizontal = z + x;
      vertical = y;
      paths[3] = get_diagonal(horizontal, vertical);

      // Case 5: S-2 & F-3 [S-2 & F-4 not considered: always longer]
      horizontal = z + 200;
      vertical = x + y - 200;
      paths[4] = get_diagonal(horizontal, vertical);

      // Case 6: S-3 & F-1
      horizontal = 2*x + z - 200;
      vertical = x + y;
      paths[5] = get_diagonal(horizontal, vertical);

      // Case 7: S-3 & F-2 [S-3 & F-4, S-4 & F-1 not considered: equivalent to S-2 & F-1, S-3 & F-2 respectively]
      horizontal = x + y + z - 200;
      vertical = 200;
      paths[6] = get_diagonal(horizontal, vertical);

      // Case 7: S-4 & F-2 [S-4 & F-3 not considered: equivalent to S-1 & F-2]
      horizontal = 2*y + z - 200;
      vertical = x + y;
      paths[7] = get_diagonal(horizontal, vertical);

      // Find and print the minimum.
      System.out.printf("for (%d , %d , %d) the shortest distance is: %d\n", x, y, z, array_min(paths));
    }
  }
}