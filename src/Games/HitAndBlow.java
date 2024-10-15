package Games;

public class HitAndBlow {
	public static int HitAndBlow(int coin) {

		int digit = 3;
		int maxNum = 9;

		int[] num = new int[digit];
		int[] ans = new int[digit];
		int bet = 0;
		boolean betLoop = true;
		int ansLoop = 0;
		int ansCount = 0;

		opening(coin);
		while (betLoop) {
			bet = betting(); // bet入力
			if (bet == 0) // 退出判定
				return coin;
			betLoop = bettingCheck(bet, coin); // 不正入力でループ
		}
		coin -= bet;
		System.out.println(" -"+bet+"coin");
		System.out.print(" 所持coin:" + coin );
		generateNum(num, maxNum, digit);
//		debug(num);

		while (ansLoop == 0) {
			ansCount++;
			ansConvert(ans, ansInput(bet, ansCount));
			countDisplay(num, ans);
			ansLoop = endCheck(hitCount(num, ans), coin, getCoin(bet, ansCount + 1),digit);
		}
		if (ansLoop == 1) {
			allLostEnd();
			coin = 0;
			return coin;
		}
		result(getCoin(bet, ansCount));
		coin += (getCoin(bet, ansCount));
		return coin;
	}

	public static void debug(int[] num) {
		for (int i : num) // debug
			System.out.print(" " + i + " "); // debug
		System.out.println();
	}

	public static void opening(int coin) {
		System.out.println("-----------------------------");
		System.out.println();
		System.out.println();
		System.out.println("	HIT AND BLOW");
		System.out.println();
		System.out.println();
		System.out.print("所持coin:" + coin + "　		please enter　＞");
		String temp = new java.util.Scanner(System.in).nextLine();
		System.out.println("-----------------------------");
	}

	public static int betting() {
		System.out.print("betするcoin数を入力して！（0.やめる）　＞");
		return new java.util.Scanner(System.in).nextInt();
		System.out.println();
	}

	public static boolean bettingCheck(int bet, int coin) {
		if (bet > coin) {
			System.out.println("手持ちのcoinより多い数はbetできません。");
			return true;
		} else if (bet < 0) {
			System.out.println("0より低い数はbetできません。");
			return true;
		}
		return false;
	}

	public static void generateNum(int[] num, int max, int digit) {
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

	public static int getCoin(int bet, int ansCount) {
		return bet * 2 - bet / 5 * (ansCount - 1);
	}

	public static int ansInput(int bet, int ansCount) {
		System.out.println("　現在の報酬：" + getCoin(bet, ansCount) + "coin　　");
		System.out.println();
		System.out.print(ansCount + "答目　＞");
		int input = new java.util.Scanner(System.in).nextInt();
		return input;
	}

	public static void ansConvert(int[] ans, int input) {
		for (int i = 0; i < ans.length; i++) {
			ans[i] = input % 10;
			input /= 10;
		}
	}

	public static int hitCount(int[] num, int[] ans) {
		int hit = 0;
		for (int i = 0; i < num.length; i++) {
			if (num[i] == ans[i])
				hit++;
		}
		return hit;
	}

	public static int blowCount(int[] num, int[] ans) {
		int blow = 0;
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if (i == j)
					continue;
				if (num[i] == ans[j])
					blow++;
			}
		}
		return blow;
	}

	public static void countDisplay(int[] num, int[] ans) {
		System.out.print(" " + hitCount(num, ans) + "hit " + blowCount(num, ans) + "blow ");
	}

	public static int endCheck(int hit, int coin, int getCoin,int digit) {
		if (hit == digit)
			return 2;
		if (getCoin * (-1) > coin)
			return 1;
		return 0;
	}

	public static void allLostEnd() {
		System.out.println();
		System.out.println();
		System.out.println("	じゃあな");
		System.out.println();
		System.out.println();

	}

	public static void result(int getCoin) {
		System.out.println();
		if (getCoin > 0)
			System.out.println("you are winner!");
		if (getCoin < 0)
			System.out.println("you are looser...");
		if (getCoin == 0)
			System.out.println("Nothing happened, right?");
		System.out.println();
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
