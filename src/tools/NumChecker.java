package tools;

public class NumChecker {
	//素数を判定するメソッド
	public static boolean isPrime(int num) {
		boolean isPrime = true;
		for(int i = 2; i < Math.sqrt(num) + 1; i++) {
			if(num % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
}
