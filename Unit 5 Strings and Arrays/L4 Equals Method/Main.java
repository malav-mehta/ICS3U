class Main {
  public static void main(String[] args) {
  	// This should be empty when your submit - the tester will call your method in a variety of ways.
  }

  public static boolean equals (double[]a, double[] b) {
  	boolean identical = true;

  	if (a.length == b.length) {
  		for (int i = 0; i < a.length; i++) {
  			if (a[i] != b[i]) {
  				identical = false;
  				break;
  			}
  		}
  	} else
  		identical = false;

  	return identical;
  }
}