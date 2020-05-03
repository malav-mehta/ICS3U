import java.lang.Math;

class Main {
  public static void main(String[] args) {
    double sum;
    long product;
    int total;

    sum = 0;
    for (double i = 1.0; i <= 1000.0; i++) sum += 1 / i;
    System.out.println(sum);

    sum = 0;
    for (double i = 100.0; i <= 5000.0; i += 100.0) sum += Math.sqrt(i);
    System.out.println(sum);

    product = 1;
    for (int i = 2; i<= 20; i++) product *= i;
    System.out.println(product);

    total = 0;
    for (int i = -12; i <= 20; i++) total += Math.pow(i, 3);
    System.out.println(total);

    sum = 0;
    for (int i = 1; i <= 25; i++) sum += Math.pow(i, 1.0/i);
    System.out.println(sum);
  }
}