package Games;

public class dicepoker {
	public static int dicePoker(int coin) {
//		public static void main(String[] args) {

		int digit = 3;
		int maxNum = 9;

		int[] num = new int[digit];
		int bet = 0;
		int getCoin = 0;
		int ansCount = 0;
		int hit = 0;
		int blow = 0;

		bet = opening();
		if (bet == 0)
			return coin;
		if (bet > coin) {
			System.out.println("");
		}
		decideNum(num, maxNum, digit);
		System.out.println(" bet:" + bet + "coin");

		while (hit != 2 || bet != 0) {
			answerInput(bet, getCoin, ansCount);
		}
		return coin;
	}

	public static int opening() {
		System.out.println();
		System.out.println();
		System.out.println("	HIT AND BLOW");
		System.out.println();
		System.out.println();
		System.out.print("betするコインを入力してね。 (0.やめる) ＞");
		int openingInput = new java.util.Scanner(System.in).nextInt();
		return openingInput;
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
		for (int i : num) // debug
			System.out.print(" " + i + " "); // debug
		System.out.println();
	}

	public static void answerInput(int bet, int getCoin, int ansCount) {
		System.out.print("　現在の報酬：" + getCoin + "coin　　");
		System.out.print(ansCount + "答目　＞");
		int ans = new java.util.Scanner(System.in).nextInt();
	}

	public static boolean digitErrorCheck(int digit, int num) {
		int temp = num;
		int count = 0;
		while (temp == 0) {
			temp %= 10;
			count++;
			if (count == digit)
				return false;
		}
		return true;
	}
}
