import java.math.BigInteger;
import java.util.ArrayList;
//O(1) space
//O(n) time
class Fibonacci {
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static BigInteger prevPrev = ZERO;
	private static BigInteger prev = ONE;
	public static String get(int n) {
		if(n <=1) {
			return n + "";
		}else{
			BigInteger curr = BigInteger.ZERO;
			for(int i = 2; i <= n; i++) {
				curr = prevPrev.add(prev);
				prevPrev = prev;
				prev = curr;
			}
			reinitialise();
			return curr.toString();
		}
	}
	public static ArrayList<String> firstK(int k) {
		ArrayList<String> fibonacciSequence = new ArrayList<String>();
		fibonacciSequence.add("0");
		if(k == 0) {
			return fibonacciSequence;
		}else if(k == 1) {
			fibonacciSequence.add("1");
			return fibonacciSequence;
		}
		else{
			fibonacciSequence.add("1");
			BigInteger curr = BigInteger.ZERO;
			for(int i = 2; i <= k; i++) {
				curr = prevPrev.add(prev);
				prevPrev = prev;
				prev = curr;
				fibonacciSequence.add(curr.toString());
			}
			reinitialise();
			return fibonacciSequence;
		}
	}
	private static void reinitialise() {
		prevPrev = ZERO;
		prev = ONE;
	}
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		System.out.println(f.get(1));
		System.out.println(f.get(5));
		System.out.println(f.firstK(10));
		System.out.println(f.get(99));
	}
}