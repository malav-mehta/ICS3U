import java.lang.Math;

public class Rectangle {
  private int left;
  private int bottom;
  private int width;
  private int height;

  public Rectangle(int left, int bottom, int width, int height) {
    setLocation(left, bottom);
    setSize(width, height);
  }

  public Rectangle(int width, int height) {
    this(0, 0, width, height);
  }

  public Rectangle(int side) {
    this(side, side);
  }

  public Rectangle() {
    this(0);
  }

  private void setLocation(int left, int bottom) {
    this.left = left;
    this.bottom = bottom;
  }

  private void setSize(int width, int height) {
    this.width = Math.max(width, 0);
    this.height = Math.max(height, 0);
  }

  public int getLeft() {
    return left;
  }

  public int getBottom() {
    return bottom;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String toString() {
    return String.format("b:(%d,%d) w:%d h:%d", this.left, this.bottom, this.width, this.height);
  }

  private int area() {
    return this.width * this.width;
  }

  private int perimeter() {
    int perimeter = 0;

    perimeter += this.width == 0 ? this.height : this.height * 2;
    perimeter += this.height == 0 ? this.width : this.width * 2;

    return perimeter;
  }

  public double maxArea() {
    return Math.pow(this.perimeter() / 4.0, 2);
  }

  public double maxPerimeter() {
    return Math.sqrt(this.area()) * 4.0;
  }

  public boolean contains(int x, int y) {
    return x >= this.left && x <= this.left + this.width &&
      y >= this.bottom && y <= this.bottom + this.height;
  }

  public boolean contains(Rectangle x) {
    return this.left <= x.getLeft() && this.left + this.width >= x.getLeft() + x.getWidth() &&
      this.bottom <= x.getBottom() && this.bottom + this.height >= x.getBottom() + x.getHeight();
  }

  public boolean containsLine(double m, double b) {
    double xTop = (this.bottom + this.height - b) / m;
    double xBottom = (this.bottom - b) / m;
    return (xTop >= this.left && xTop <= this.left + this.width) ||
      (xBottom >= this.left && xBottom <= this.left + this.width);
  }

  public Rectangle intersection(Rectangle x) {
    if (this.contains(x))
      return x;
    else if (x.contains(this))
      return this;
    else {
      int left = Math.max(this.left, x.getLeft());
      int bottom = Math.max(this.bottom, x.getBottom());
      int width = Math.min(this.left + this.width, x.getLeft() + x.getWidth()) - left;
      int height = Math.min(this.bottom + this.height, x.getBottom() + x.getHeight()) - bottom;
      if (width < 0 || height < 0)
        return new Rectangle();
      else
        return new Rectangle(left, bottom, width, height);
    }
  }

  public static void main(String[] args) {
    Rectangle a = new Rectangle(3, 2, 6, 4);
    Rectangle b = new Rectangle(6, 1, 4, 3);
    System.out.println(a.intersection(b));
  }
}