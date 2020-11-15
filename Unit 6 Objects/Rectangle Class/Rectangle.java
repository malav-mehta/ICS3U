import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * The Rectangle class represents axis-aligned rectangles in Cartesian plane
 * that are described by the coordinates of their bottom-left vertex and their
 * widths and heights. The rectangle's parameters are integer values.
 *
 * @author Malav Mehta
 * ICS3U, JAVA OOP Assignment
 */

public class Rectangle {
    private int left;     // the x-coordinate of the left edge
    private int bottom;   // the y-coordinate of the bottom edge
    private int width;    // the width of the rectangle
    private int height;   // the height of the rectangle

    /**
     * Initializes a new rectangle with the bottom-left vertex coordinate
     * (left, bottom) and dimensions width by height. Calls setLocation() and
     * setSize() to ensure that the rectangle is initialized in the required
     * format with error-free values.
     *
     * @param left   the x-coordinate of the rectangle's left edge
     * @param bottom the y-coordinate of the rectangle's bottom edge
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(int left, int bottom, int width, int height) {
        setLocation(left, bottom);
        setSize(width, height);
    }

    /**
     * Initializes a new rectangle from the toString() format:
     * "b:(%d,%d) w:%d h:%d", left, bottom, width, height
     * by unpacking the values and converting them to integers.
     *
     * @param rect a string in format "b:(%d,%d) w:%d h:%d", left, bottom,
     *             width, height
     * @throws NumberFormatException if the %d arguments in the string are not
     *                               properly formatted integers
     */
    public Rectangle(String rect) throws NumberFormatException {
        // replace unwanted characters
        String[] toReplace = {"base", ":", "(", ")", "w", "h"};
        for (String c : toReplace)
            rect = rect.replace(c, "");

        // unpack the four integers into a string array
        String[] r = rect.strip().split(" ");

        // initialize rectangle by parsing the required integers
        setLocation(Integer.parseInt(r[0]), Integer.parseInt(r[1]));
        setSize(Integer.parseInt(r[2]), Integer.parseInt(r[3]));
    }

    /**
     * Initializes a new rectangle with (0, 0) as the bottom-left vertex and
     * with the specified dimensions.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }

    /**
     * Initializes a new square with (0, 0) as the bottom-left vertex with side
     * length sideLength.
     *
     * @param sideLength the height and width of the square
     */
    public Rectangle(int sideLength) {
        this(0, 0, sideLength, sideLength);
    }

    /**
     * Initializes a new rectangle with (0, 0) as the bottom-left vertex and
     * dimensions 0 x 0.
     */
    public Rectangle() {
        this(0, 0, 0, 0);
    }

    /**
     * Initializes the CLI tool.
     *
     * @param args CLI args
     */
    public static void main(String[] args) throws InterruptedException {
        cli();
    }

    /**
     * Used for interacting with the utilities of the Rectangle Class via a
     * command line tool.
     */
    private static void cli() {
        // initialize CLI variables and flush the console
        boolean labelIntersections = false;
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // define the commands in sets
        final String[] helpArr = new String[]{"HELP", "help", "h", "?"};
        final String[] clearArr = new String[]{"CLEAR", "clear"};
        final String[] quitArr = new String[]{"QUIT", "quit", "q"};
        final String[] toggleArr = new String[]{"LABEL", "label"};
        final Set<String> help = new HashSet<>(Arrays.asList(helpArr));
        final Set<String> clear = new HashSet<>(Arrays.asList(clearArr));
        final Set<String> quit = new HashSet<>(Arrays.asList(quitArr));
        final Set<String> toggle = new HashSet<>(Arrays.asList(toggleArr));

        final String[] addArr = new String[]{"ADD", "add", "a", "+"};
        final String[] updateArr = new String[]{"UPDATE", "update", "u", "->"};
        final Set<String> add = new HashSet<>(Arrays.asList(addArr));
        final Set<String> update = new HashSet<>(Arrays.asList(updateArr));

        final String[] getArr = new String[]{"GET", "get", "g", "!"};
        final String[] listArr = new String[]{"LIST", "list", "ls", "l"};
        final String[] perimeterArr = new String[]{"PERIMETER", "perimeter", "PERIM", "perim", "p"};
        final String[] areaArr = new String[]{"AREA", "area", "ar"};
        final String[] intersectionArr = new String[]{"INTERSECTION", "intersection", "int", "i"};
        final Set<String> get = new HashSet<>(Arrays.asList(getArr));
        final Set<String> list = new HashSet<>(Arrays.asList(listArr));
        final Set<String> area = new HashSet<>(Arrays.asList(areaArr));
        final Set<String> perimeter = new HashSet<>(Arrays.asList(perimeterArr));
        final Set<String> intersection = new HashSet<>(Arrays.asList(intersectionArr));

        final String[] deleteArr = new String[]{"DELETE", "delete", "DEL", "del", "d", "-"};
        final String[] removeArr = new String[]{"REMOVE", "remove", "REM", "rem", "r", "-a"};
        final Set<String> delete = new HashSet<>(Arrays.asList(deleteArr));
        final Set<String> remove = new HashSet<>(Arrays.asList(removeArr));

        // initialize the rectangles and the grid
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        JFrame grid = draw(rectangles, false);

        // for handling input
        Scanner in = new Scanner(System.in);
        String[] input;
        String command;

        // print a welcome message
        System.out.println("Welcome to the Rectangle Drawing Utility. The CLI enables\naccess to the utility functions and drawing capabilities of\nthe program. Enter HELP or ? for help on operating this tool.");

        // start the main loop for user interaction
        while (true) {
            // get current command
            System.out.print("> ");
            input = in.nextLine().split("\\s+");
            command = input[0];

            if (input.length == 1) {
                // if it's for help, print descriptions for all available commands
                if (help.contains(command)) {
                    printCmdInfo(help, "List all the commands and combinations");
                    printCmdInfo(clear, "Clears the console and flushes output.");
                    printCmdInfo(quit, "Quits the program. Saving data prior to quitting is recommended.");
                    printCmdInfo(toggle, "Toggle whether rectangle intersections are labelled.");

                    printCmdInfo(add, "Adds a new rectangle. Requires a 4 integer spaced input following the command.\nExample: + 3 5 5 7\nCreates rectangle with base: (3,5) w:5 h:7.");
                    printCmdInfo(update, "Update rectangle. Requires a 8 integer spaced input following the command.\nExample: -> 3 5 5 7 4 7 5 7\nMoves the rectangle at (3, 5) to (4, 7).");

                    printCmdInfo(get, "Retrieves data of a rectangle. Requires a 4 integer spaced input following the command.\nExample: ! 3 5 5 7\nOutputs all the details of the rectangle. Try it out yourself!");
                    printCmdInfo(list, "Lists the details of all the rectangles that are being drawn right now.");
                    printCmdInfo(perimeter, "Outputs the total perimeter of two rectangles. Requires a 8 integer spaced input following the command.\nExample: perim 3 5 5 7 4 7 5 7\nReturns the total perimeter of the two rectangles.");
                    printCmdInfo(area, "Outputs the total area of two rectangles. Requires a 8 integer spaced input following the command.\nExample: ar 3 5 5 7 4 7 5 7\nReturns the total area of the two rectangles.");
                    printCmdInfo(intersection, "Outputs the rectangle forming the intersection of two rectangles. Requires a 8 integer spaced input following the command.");

                    printCmdInfo(delete, "Deletes the specified rectangle. Requires a 4 integer spaced input following the command.\nExample: - 3 5 5 7\nDeletes the rectangle if it exists.");
                    printCmdInfo(remove, "Clears the grid by deleting all rectangles.");
                }

                // flush the console if it's the 'clear' command
                else if (clear.contains(command)) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }

                // quit the program by breaking the main loop
                else if (quit.contains(command))
                    break;

                    // toggle label intersection boolean
                else if (toggle.contains(command)) {
                    labelIntersections = !labelIntersections;
                    System.out.printf("The label intersections options is now %s.\n\n", (labelIntersections ? "on" : "off"));
                }

                // list info for each rectangle being drawn
                else if (list.contains(command))
                    for (Rectangle r : rectangles) printRectInfo(r);

                    // remove all rectangles
                else if (remove.contains(command)) {
                    rectangles.clear();
                    System.out.println("Deleted all rectangles.\n");
                }

                // otherwise it's an unknown command, print error message
                else {
                    System.err.println("ERROR: Command does not exist. Enter ? or HELP for more information.\n");
                    continue;
                }
            } else {
                // add a new rectangle
                if (add.contains(command)) {
                    try {
                        Rectangle tmp = getRectInfo(input, 1);

                        // ensure that there are no duplicates
                        if (arrayContains(rectangles, tmp))
                            System.err.println("WARNING: Rectangle has already been added.\n");
                        else {
                            rectangles.add(tmp);
                            System.out.println("Added rectangle " + tmp + ".\n");
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 4 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // delete a rectangle
                else if (delete.contains(command)) {
                    try {
                        Rectangle tmp = getRectInfo(input, 1);
                        int i = arrayIndexOf(rectangles, tmp);

                        // enforce rectangle's presence
                        if (i == -1)
                            System.err.println("WARNING: Rectangle is not in array.\n");
                        else {
                            rectangles.remove(i);
                            System.out.println("Removed rectangle " + tmp + ".\n");
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 4 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // print information about a rectangle
                else if (get.contains(command)) {
                    try {
                        Rectangle tmp = getRectInfo(input, 1);
                        printRectInfo(tmp);
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 4 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // change a rectangle
                else if (update.contains(command)) {
                    try {
                        Rectangle r1 = getRectInfo(input, 1);
                        Rectangle r2 = getRectInfo(input, 5);

                        // remove previous version and add current
                        int i = arrayIndexOf(rectangles, r1);
                        if (i != -1) {
                            rectangles.remove(i);
                            rectangles.add(r2);
                            System.out.println("Changed " + r1 + " into " + r2 + ".\n");
                        } else
                            System.err.println("WARNING: Rectangle 1 is not in array.\n");
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 8 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // total perimeter of two rectangles
                else if (perimeter.contains(command)) {
                    try {
                        Rectangle r1 = getRectInfo(input, 1);
                        Rectangle r2 = getRectInfo(input, 5);
                        System.out.println("The total perimeter is " + totalPerimeter(r1, r2) + ".\n");
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 8 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // total area of two rectangles
                else if (area.contains(command)) {
                    try {
                        Rectangle r1 = getRectInfo(input, 1);
                        Rectangle r2 = getRectInfo(input, 5);
                        System.out.println("The total area is " + totalArea(r1, r2) + ".\n");
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 8 spaced integers. Enter ? or HELP for more information.\n");
                    }
                }

                // intersection rectangle of two rectangles
                else if (intersection.contains(command)) {
                    try {
                        Rectangle r1 = getRectInfo(input, 1);
                        Rectangle r2 = getRectInfo(input, 5);
                        System.out.println("The intersection of " + r1 + " and " + r2 + " is " + intersection(r1, r2) + ".\n");
                    } catch (Exception e) {
                        System.err.println("ERROR: Rectangle format exception. This command requires 8 spaced integers. Enter ? or HELP for more information.\n");
                    }
                } else {
                    System.err.println("ERROR: Command does not exist. Enter ? or HELP for more information.\n");
                    continue;
                }
            }

            grid = draw(rectangles, labelIntersections, grid);
        }

        System.out.println("Exiting program. Any unsaved work will be lost.");
        System.out.println("Thanks for using this program.");
        grid.dispatchEvent(new WindowEvent(grid, WindowEvent.WINDOW_CLOSING));
        in.close();
    }

    /**
     * Reads a rectangle from a String.
     *
     * @param input the input string array
     * @param start the starting index of the first integer
     * @return the Rectangle read from the input stream
     * @throws Exception when the rectangle integers are not properly formatted
     */
    private static Rectangle getRectInfo(String[] input, int start) throws Exception {
        int l = Integer.parseInt(input[start]);
        int b = Integer.parseInt(input[start + 1]);
        int w = Integer.parseInt(input[start + 2]);
        int h = Integer.parseInt(input[start + 3]);

        Rectangle tmp = new Rectangle(l, b, w, h);
        return tmp;
    }

    /**
     * Performs a linear search to check if an ArrayList contains a certain element.
     *
     * @param rectangles the ArrayList
     * @param rect       the element being searched for
     * @return true if rect is in rectangles else false
     */
    private static boolean arrayContains(ArrayList<Rectangle> rectangles, Rectangle rect) {
        for (Rectangle r : rectangles)
            if (r.equals(rect))
                return true;

        return false;
    }

    /**
     * Linear search for the index of an element in an ArrayList.
     *
     * @param rectangles the ArrayList
     * @param rect       the element that's being searched
     * @return the index of the element or -1 if it's not found
     */
    private static int arrayIndexOf(ArrayList<Rectangle> rectangles, Rectangle rect) {
        for (int i = 0; i < rectangles.size(); ++i) {
            if (rectangles.get(i).equals(rect))
                return i;
        }
        return -1;
    }

    /**
     * Prints useful information (toString() representation, the area and the
     * perimeter) about the rectangle.
     *
     * @param rect the rectangle whose information is to be printed
     */
    private static void printRectInfo(Rectangle rect) {
        System.out.println(rect);
        System.out.printf("Perimeter: %d, Area: %d\n\n", rect.perimeter(), rect.area());
    }

    /**
     * Prints CLI command keywords and descriptions to the output stream.
     *
     * @param cmd  the Set of commands
     * @param desc the description of the command
     */
    private static void printCmdInfo(Set<String> cmd, String desc) {
        for (String s : cmd) System.out.print(s + " ");
        System.out.println("\n" + desc + "\n");
    }

    /**
     * Converts a type-Rectangle ArrayList into a Rectangle array for use in
     * other functions.
     *
     * @param rects the Rectangle ArrayList
     * @return the Rectangle array
     */
    private static Rectangle[] toArray(ArrayList<Rectangle> rects) {
        Rectangle[] converted = new Rectangle[rects.size()];

        // iterate through the ArrayList
        for (int i = 0; i < rects.size(); ++i)
            converted[i] = rects.get(i);

        return converted;
    }

    /**
     * Returns the rectangle intersection of two rectangles. If the rectangles
     * do not intersect, the rectangle that is returned is initialized with all
     * fields set to zero.
     *
     * @param a the first rectangle
     * @param b the second rectangle
     * @return the rectangle intersection of two rectangles
     */
    public static Rectangle intersection(Rectangle a, Rectangle b) {
        // if one of the rectangles is inside the other, it is the intersection
        if (a.contains(b))
            return b;

        else if (b.contains(a))
            return a;

        /*
         otherwise, the intersection is calculated by:

         left:   the x-coordinate of the left edge is the right-most left edge
                 of the two rectangles

         bottom: the y-coordinate of the bottom edge is the top-most bottom
                 edge of the two rectangles

         width:  the distance between the left edge and the left-most right
                 edge of the two rectangles

         left:   the distance between the bottom edge and the bottom-most top
                 edge of the two rectangles
         */
        else {
            int left = Math.max(a.getLeft(), b.getLeft());
            int bottom = Math.max(a.getBottom(), b.getBottom());
            int width = Math.min(a.getRight(), b.getRight()) - left;
            int height = Math.min(a.getTop(), b.getTop()) - bottom;

            if (width < 0 || height < 0)
                left = bottom = width = height = 0;

            return new Rectangle(left, bottom, width, height);
        }
    }

    /**
     * Returns the total area of two rectangles, without overlap.
     *
     * @param a the first rectangle
     * @param b the second rectangle
     * @return the total area of two rectangles, without overlap
     */
    public static int totalArea(Rectangle a, Rectangle b) {
        // Area(a) + Area(b) - Area(a intersecting b)
        return a.area() + b.area() - intersection(a, b).area();
    }

    /**
     * Returns the total perimeter of two rectangles, without overlap.
     *
     * @param a the first rectangle
     * @param b the second rectangle
     * @return the total perimeter of two rectangles, without overlap
     */
    public static int totalPerimeter(Rectangle a, Rectangle b) {
        // Perimeter(a) + Perimeter(b) - Perimeter(a intersecting b)
        Rectangle overlap = intersection(a, b);
        int multiplier = overlap.area() == 0 ? ((a.area() == 0 || b.area() == 0) ? 1 : 2) : 1;
        return a.perimeter() + b.perimeter() - intersection(a, b).perimeter() * multiplier;
    }

    /**
     * Draws the specified rectangle on a new window, created based on the
     * viewport size. The rectangle is drawn accurately if possibly. Otherwise,
     * either the grid on which the rectangle is drawn is scaled down.
     *
     * @param rect the rectangle that will be drawn
     */
    public static void draw(Rectangle rect) {
        // call draw() with @param rectangles = new Rectangle[]{rect}
        draw(new Rectangle[]{rect}, false);
    }

    /**
     * Draws the specified rectangles on a new window, created based on the
     * viewport size. The rectangles are drawn accurately when possible. If
     * not, either the grid on which the rectangle is drawn is scaled down. The
     * scaling is determined by comparing the maximum grid size required.
     * <p>
     * Rectangle intersections are also drawn and marked as by their parent
     * rectangles (the rectangles that intersected to produce the rectangle
     * that is drawn) by their toString() depiction. Only level 1 intersections
     * are drawn (intersections of intersections are not drawn).
     *
     * @param rectangles the rectangle that will be drawn
     */
    public static JFrame draw(Rectangle[] rectangles, boolean labelIntersections) {
        // creates a new gui, non-resizable window using the swing library
        JFrame grid = new JFrame(String.format("Rectangle Grid: %d Rectangle" + (rectangles.length == 1 ? "" : "s"), rectangles.length));
        grid.pack();
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setVisible(true);
        grid.setResizable(false);

        // set bg color and size the grid to be s x s where s = 3/4 screen height
        grid.getContentPane().setBackground(Color.WHITE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        grid.setSize(screenSize.height * 3 / 4, screenSize.height * 3 / 4);
        grid.setLocationRelativeTo(null);

        // adding grid window elements to the grid content pane
        grid.getContentPane().add(new RectangleGrid(rectangles, screenSize.height * 3 / 4, labelIntersections));
        return grid;
    }

    public static JFrame draw(Rectangle[] rectangles, boolean labelIntersections, JFrame grid) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        grid.setTitle(String.format("Rectangle Grid: %d Rectangle" + (rectangles.length == 1 ? "" : "s"), rectangles.length));
        grid.getContentPane().removeAll();
        grid.repaint();
        grid.getContentPane().add(new RectangleGrid(rectangles, screenSize.height * 3 / 4, labelIntersections));
        grid.validate();
        return grid;
    }

    public static JFrame draw(ArrayList<Rectangle> rectangles, boolean labelIntersections, JFrame grid) {
        return draw(toArray(rectangles), labelIntersections, grid);
    }

    public static JFrame draw(ArrayList<Rectangle> rectangles, boolean labelIntersections) {
        return draw(toArray(rectangles), labelIntersections);
    }

    /**
     * Resets all the fields of the rectangle with the newly specified fields.
     *
     * @param left   the x-coordinate of the rectangle's left edge
     * @param bottom the y-coordinate of the rectangle's bottom edge
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public void set(int left, int bottom, int width, int height) {
        setLocation(left, bottom);
        setSize(width, height);
    }

    /**
     * Sets a new location for the bottom-left vertex of the rectangle at
     * coordinates (left, bottom).
     *
     * @param left   the x-coordinate of the rectangle's left edge
     * @param bottom the y-coordinate of the rectangle's bottom edge
     */
    public void setLocation(int left, int bottom) {
        this.left = left;
        this.bottom = bottom;
    }

    /**
     * Moves the location of the rectangle's bottom-left vertex with the
     * top-right vertex moved freely (meaning that the rectangle's width and
     * height are preserved after the translation).
     *
     * @param x the amount of units the rectangle's left edge is translated by
     * @param y the amount of units the rectangle's bottom edge is translated by
     */
    public void translateFreely(int x, int y) {
        left += x;
        bottom += y;
    }

    /**
     * Moves the location of the rectangle's bottom-left vertex withe the
     * top-right vertex locked on its location (meaning that the rectangle will
     * be resized based on how the bottom-left vertex is translated).
     *
     * @param x the amount of units the rectangle's left edge is translated by
     * @param y the amount of units the rectangle's bottom edge is translated by
     */
    public void translateLocked(int x, int y) {
        // define the coordinates of original locked top-right vertex
        int right = getRight(); // the x-coordinate of the right edge
        int top = getTop();     // the y-coordinate of the top edge

        left += x;
        bottom += y;

        if (left >= right) {
            // translation is top-right of locked point
            if (bottom >= top) {
                width = left - right;
                height = bottom - top;
                left = right;
                bottom = top;
            }

            // translation is bottom-right of locked point
            else {
                width = left - right;
                height = top - bottom;
                left = right;
            }
        } else {
            // translation is top-left of locked point
            if (bottom >= top) {
                width = right - left;
                height = bottom - top;
                bottom = top;
            }

            // translation is bottom-left of locked point
            else {
                width = right - left;
                height = top - bottom;
            }
        }
    }

    /**
     * Assigns non-negative dimensions for the rectangle based on the
     * dimensions specified by the method parameters.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public void setSize(int width, int height) {
        // defaults negative parameters to 0
        this.width = Math.max(width, 0);
        this.height = Math.max(height, 0);
    }

    /**
     * Returns the x-coordinate of the rectangle's left edge.
     *
     * @return the x-coordinate of the rectangle's left edge
     */
    public int getLeft() {
        return left;
    }

    /**
     * Returns the y-coordinate of the rectangle's bottom edge.
     *
     * @return the y-coordinate of the rectangle's bottom edge
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the x-coordinate of the rectangle's right edge.
     *
     * @return the x-coordinate of the rectangle's right edge
     */
    public int getRight() {
        return left + width;
    }

    /**
     * Returns the x-coordinate of the rectangle's top edge.
     *
     * @return the x-coordinate of the rectangle's top edge
     */
    public int getTop() {
        return bottom + height;
    }

    /**
     * Returns a string representation of the rectangle.
     *
     * @return a string representation of the rectangle in the format:
     * b:(left,bottom) w:width h:height
     */
    public String toString() {
        return String.format("base: (%d,%d) w:%d h:%d", left, bottom, width, height);
    }

    /**
     * Returns the area of the rectangle.
     *
     * @return the area of the rectangle.
     */
    public int area() {
        return width * height;
    }

    /**
     * Returns the maximum area for the rectangle with its perimeter locked.
     * This occurs if the rectangle is a square (the area is optimized when the
     * side lengths of the rectangle are equal to one another).
     *
     * @return the maximum area for the rectangle with its perimeter locked
     */
    public double maxArea() {
        // equally distribute the perimeter to 4 sides of the optimized square
        return Math.pow(perimeter() / 4.0, 2);
    }

    /**
     * Returns the perimeter of the rectangle. If the rectangle is a point,
     * having no width and no height, then its perimeter is 0. If the rectangle
     * is a line, with either its width or its height equal to 0, then its
     * perimeter is its length.
     *
     * @return the perimeter of the rectangle
     */
    public int perimeter() {
        int perimeter = 0;

        // ensures that a straight line's perimeter is only its length
        perimeter += this.width == 0 ? this.height : this.height * 2;
        perimeter += this.height == 0 ? this.width : this.width * 2;

        return perimeter;
    }

    /**
     * Returns the minimum perimeter for the rectangle with its area locked.
     * This is determined by calclating the perimeter of square with the same
     * area as the rectangle.
     *
     * @return the minimum perimeter for the rectangle with its area locked
     */
    public double minPerimeter() {
        // treat rectangle as square to obtain optimized perimeter
        return Math.sqrt(area()) * 4.0;
    }

    /**
     * Returns true if this rectangle's properties are equal to the properties
     * of the other rectangle.
     *
     * @param other the other rectangle
     * @return true if this rectangle has the same properties as the other
     */
    public boolean equals(Rectangle other) {
        // check if all properties are equal
        return other != null
            && left == other.getLeft()
            && bottom == other.getBottom()
            && width == other.getWidth()
            && height == other.getHeight();
    }

    /**
     * Returns true if the point is within or on the rectangle.
     *
     * @param point the point's coordinates formatted as int[2]{x, y}
     * @return true if the point is within or on the rectangle
     */
    public boolean contains(int[] point) {
        int x = point[0];
        int y = point[1];

        // check if point.x in [left, right] and point.y in [bottom, top]
        return x >= left
            && x <= getRight()
            && y >= bottom
            && y <= getTop();
    }

    /**
     * Returns true if the line, described by the format y = mx + b is ever
     * crosses the interior or the edges of the rectangle.
     *
     * @param m the slope of the line
     * @param b the y-intercept of the line
     * @return true if the line y = mx + b is contained in the rectangle
     */
    public boolean contains(double m, double b) {
        double xTop = (getTop() - b) / m;
        double xBottom = (bottom - b) / m;

        // check for x = (y - b) / m, y in {bottom, top} in or on rectangle
        return (xTop >= left
            && xTop <= getRight())
            || (xBottom >= left
            && xBottom <= getRight());
    }

    /**
     * Returns true if this rectangle contains the other rectangle.
     *
     * @param other the other rectangle
     * @return true if this rectangle contains the other rectangle
     */
    public boolean contains(Rectangle other) {
        return left <= other.getLeft()
            && getRight() >= other.getRight()
            && bottom <= other.getBottom()
            && getTop() >= other.getTop();
    }

    /**
     * Returns the distance between this rectangle and a point.
     *
     * @param point the point's coordinates formatted as int[2]{x, y}
     * @return the distance between this rectangle and a point
     */
    public double distance(int[] point) {
        int x = point[0];
        int y = point[1];
        double dist;
        int dx = 0, dy = 0;


        // the distance is invalid if the point is inside the rectangle
        if (this.contains(point))
            dist = -1;

        else {
            // get delta x for x to the left or right of the rectangle
            if (x >= getRight())
                dx = x - getRight();

            else if (x <= left)
                dx = left - x;

            // get delta y for y above or below the rectangle
            if (y >= getTop())
                dy = y - getTop();

            else if (y <= bottom)
                dy = bottom - y;

            // compute distance with the Pythagoras Theorem
            dist = Math.sqrt(dx * dx + dy * dy);
        }

        return dist;
    }
}


/**
 * The RectangleGrid class is built as an extension of the JComponent class and
 * overrides the methods that draw a graphical representation of the
 * rectangles that are inputted as its parameters.
 *
 * @author Malav Mehta
 * ICS3U, OOP Assignment
 */

class RectangleGrid extends JComponent {
    private final Rectangle[] rectangles;       // rectangles that will be drawn
    private final int gridLength;               // grid: gridLength by gridLength
    private final ArrayList<Rectangle> intersections = new ArrayList<Rectangle>(); // rectangle intersections
    private final double PADDING = 2.18;        // padding on grid's outer edges
    private final boolean labelIntersections;   // whether or not the intersections will be drawn
    private int GRID_SPACING = 10;              // spacing between grid lines
    private double ratio;                       // ratio for scaling the grid

    /**
     * Initializes a new RectangleGrid that will draw the rectangles specified
     * in the parameter and will draw them on a grid with dimensions gridLength
     * by gridLength.
     *
     * @param rectangles the rectangles that will be drawn
     * @param gridLength the side length of the grid
     */
    public RectangleGrid(Rectangle[] rectangles, int gridLength, boolean labelIntersections) {
        this.rectangles = rectangles;
        this.gridLength = gridLength;
        this.labelIntersections = labelIntersections;

        // get the intersections of the rectangles
        computeIntersections();

        // calculate the ratio that will be used for scaling
        calculateRatio();
    }

    /**
     * Computes the intersection of all rectangles. Ensures that no
     * intersection will be drawn twice. The intersections are added to the
     * intersection field of the RectangleGrid, and will also be drawn.
     */
    private void computeIntersections() {
        for (int i = 0; i < rectangles.length; i++)
            for (int j = i + 1; j < rectangles.length; j++) {
                Rectangle intersectRect = Rectangle.intersection(rectangles[i], rectangles[j]);

                // only rectangles with a non-zero area are added since non-
                // intersecting rectangles will return a rectangle without area
                if (intersectRect.area() > 0)
                    intersections.add(intersectRect);
            }
    }

    /**
     * Computes the ratio by which the the rectangle will be scaled. This is
     * important so that if the grid contains only smaller rectangles then it
     * is "zoomed in" or if it contains only larger rectangles then is is
     * "zoomed out". This also ensures that any outlier rectangles (rectangles
     * that are far from the other rectangles) are still shown on the grid with
     * the required inner padding.
     */
    private void calculateRatio() {
        // set 21 as the minimum grid size
        int max = 20;

        for (Rectangle rect : rectangles) {
            // calculates the extreme edges of each rectangle
            int left = Math.abs(rect.getLeft());
            int top = Math.abs(rect.getBottom()) + rect.getHeight();
            int right = Math.abs(left + rect.getWidth());
            int bottom = Math.abs(rect.getBottom());

            /*
            determines how far from the origin the outermost edge of the
            rectangle is situated

            this value is compared to the max value to get the maximum length
            of the outermost edge of the outermost rectangle, since this is the
            maximum coordinate that the grid will have to show

            the max value is replace if the current rectangle has an edge that
            is more to the outside than the previous
             */
            max = Math.max(max, Math.max(left, Math.max(top, Math.max(right, bottom))));
        }

        // assigns the ratio based on the outermost edge
        // if the max value is unaltered, the scale ratio is set to 1
        ratio = gridLength / PADDING / max;
    }

    /**
     * Called by JComponent for drawing the RectangleGrid as a JComponent.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Method that draws a rectangle. It scales the rectangle and labels its
     * graphical representation with its toString() depiction.
     *
     * @param rect the rectangle that is to be drawn
     */
    public void draw(Graphics g, Rectangle rect, boolean isIntersection) {
        // set the rectangle color
        Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        g.setColor(c);

        // in case the labels are blocked, the rectangle and its color are
        // printed to the console for the user
        // System.out.println(rect + " is drawn in color " + c);

        // calculates the (left, top) and width/height coordinates for
        // rendering the scaled rectangle
        int left = (int) (rect.getLeft() * ratio) + gridLength / 2;
        int width = (int) (rect.getWidth() * ratio);
        int height = (int) (rect.getHeight() * ratio);
        int top = gridLength / 2 - (int) (rect.getBottom() * ratio) - height;
        g.fillRect(left, top, width, height);

        // draws the toString() depiction under the rectangle
        c = new Color(0, 0, 0);
        g.setColor(c);
        String label = (isIntersection ? "i: " : "") + rect.toString();
        if (!isIntersection || labelIntersections)
            g.drawString(label, left, top + height + 13);
    }

    /**
     * Draws all the components of the grid, including the grid, the axis, the
     * axis labels, the rectangles and the rectangle intersections.
     */
    public void draw(Graphics g) {
        // sets the color for the non-axis grid lines
        Color c = new Color(220, 220, 220);
        g.setColor(c);

        // set the spacing between grids based on the ratio
        GRID_SPACING = (int) (5.0 * Math.round((gridLength / ratio / 10) / 5.0));
        int legend = gridLength / 2;

        // draw the grid lines, spaced every GRID_SPACING units
        for (int i = gridLength / 2 + (int) (GRID_SPACING * ratio); i < gridLength; i += (int) (GRID_SPACING * ratio)) {
            // draws the y-axis aligned grid lines
            g.drawLine(0, i, gridLength, i);
            g.drawLine(0, gridLength - i, gridLength, gridLength - i);

            // draws the x-axis aligned grid lines
            g.drawLine(i, 0, i, gridLength);
            g.drawLine(gridLength - i, 0, gridLength - i, gridLength);
            legend = i;
        }

        // draws all rectangles and their intersections
        for (Rectangle rect : rectangles)
            draw(g, rect, false);

        for (Rectangle rect : intersections)
            draw(g, rect, true);

        // set color to black for axis
        c = new Color(0, 0, 0);
        g.setColor(c);

        // draw axis
        g.drawLine(0, gridLength / 2, gridLength, gridLength / 2);
        g.drawLine(gridLength / 2, 0, gridLength / 2, gridLength);

        // draw legend on left edge
        c = new Color(255, 0, 160);
        g.setColor(c);
        g.drawLine(gridLength - legend, gridLength / 2, gridLength - legend + (int) (GRID_SPACING * ratio), gridLength / 2);
        g.drawLine(gridLength - legend, gridLength / 2 - 1, gridLength - legend + (int) (GRID_SPACING * ratio), gridLength / 2 - 1);
        g.drawLine(gridLength - legend, gridLength / 2 - 2, gridLength - legend + (int) (GRID_SPACING * ratio), gridLength / 2 - 2);
        g.drawLine(gridLength - legend, gridLength / 2 - 3, gridLength - legend + (int) (GRID_SPACING * ratio), gridLength / 2 - 3);
        g.drawString(GRID_SPACING + " units", gridLength - legend, gridLength / 2 - 7);

        // set color to black for labels
        c = new Color(0, 0, 0);
        g.setColor(c);

        // max and min label string content
        String max = "" + (int) (gridLength / 2.0 / ratio);
        String min = "-" + (int) (gridLength / 2.0 / ratio);

        // used for getting string properties for when they're drawn
        FontMetrics f = g.getFontMetrics();

        // label x-axis
        g.drawString("x-min: " + min, 3, gridLength / 2 + 15);
        // right-label is right-aligned by using FontMetrics to calculate width
        g.drawString("x-max: " + max, gridLength - f.stringWidth("x-max: " + max) - 3, gridLength / 2 + 15);

        // label y-axis
        g.drawString("y-max: " + max, gridLength / 2 + 3, +13);
        g.drawString("y-min: " + min, gridLength / 2 + 3, gridLength - 28);
    }
}