package Games;

public class dicepoker {
	public static int dicePoker(int coin) {

//	public static void main(String[] args) {
		int digit = 3;
		int maxNum = 9;

		int[] num = new int[digit];

		decideNum(num, maxNum, digit);
//		for (int i : num)						//debug
//			System.out.print(" " + i + " ");	//
		
		return coin;
	}

	public static void decideNum(int[] num, int max, int digit) {
		boolean loop = true;
		while (loop) {
			loop = false;
			for (int i = 0; i < digit; i++) {
				num[i] = new java.util.Random().nextInt(max) + 1;
			}
			for (int i = 0; i < num.length - 1; i++) {
				for (int j = i + 1; j < num.length; j++) {
					if (num[i] == num[j]) {
						loop = true;
					}
				}
			}
		}
	}
}
