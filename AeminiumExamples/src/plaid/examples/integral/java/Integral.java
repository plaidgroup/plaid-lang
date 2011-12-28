package plaid.examples.integral.java;

public class Integral {
	
	public static double EPSILON = 0.00000001;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Area = " + compute(0, 1));
	}

	public static double f(final double x) {
		return x*x +1;
	}
	
	public static double compute(final double x1, final double x2) {
		if ( x2-x1 < EPSILON ) {
			// compute trapez area 
			final double area = ((f(x1)+f(x2))/2) * (x2-x1);
			return area;
		} else {
			// divide and conquer
			final double middle = (x2+x1) / 2;
			return compute(x1, middle) + compute(middle, x2);
		}
	}
}