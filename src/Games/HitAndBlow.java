package Games;

import java.util.Iterator;

public class HitAndBlow {
	public static int Hit_and_blow(int coin) {

		int digit = 3;
		int maxNum = 9;
		int[] option = { digit, maxNum };

		double bet = 0;
		boolean betLoop = true;
		int ansLoop = 0;
		int ansCount = 0;

		opening(coin);
		while (betLoop) {
			bet = betting(); // bet入力
			if (bet == 0) // 退出判定
				return coin;
			betLoop = bettingCheck(bet, coin, option); // 不正入力でループ
		}

		int[] num = new int[option[0]];
		int[] ans = new int[option[0]];
		coin -= bet;
		setup(coin, bet);
		generateNum(num, option);
//		debug(num);

		while (ansLoop == 0) {
			ansCount++;
			ansConvert(ans, ansInput(bet, ansCount, option));
			countDisplay(num, ans);
			ansLoop = endCheck(hitCount(num, ans), coin, reward(bet, ansCount + 1, option), option);
		}
		if (ansLoop == 1) {
			allLostEnd();
			coin = 0;
			return coin;
		}
		result(reward(bet, ansCount, option));
		coin += (reward(bet, ansCount, option));
		return coin;
	}

	public static void option(int[] option) {
		while (true) {
			System.out.println("--------------------------");
			System.out.println("option bonus:" + Math.round(getOptionBonus(option) * 100.0) / 100.0 + "倍");
			System.out.println("0.digit:" + option[0]);
			System.out.println("1.maxNum:" + option[1]);
			System.out.println("9.戻る");
			System.out.print("＞");
			boolean loop = true;
			int input = 9;
			while (loop) {
				input = new java.util.Scanner(System.in).nextInt();
				int[] not = { 2, 3, 4, 5, 6, 7, 8 };
				if (tools.ErrorChecker.digitErrorCheck(1, input) || tools.ErrorChecker.numErrorCheck(not, input)) {
					System.out.println("無効な入力です");
					System.out.print("＞");
				} else {
					loop = false;
				}
			}
			if (input == 9)
				return;
			optionChange(option, input);
		}
	}

	public static void optionChange(int[] option, int optionIndex) {
		boolean loop = true;
		int input = 0;
		while (loop) {
			System.out.print("1~9で入力してください　＞");
			input = new java.util.Scanner(System.in).nextInt();
			if (tools.ErrorChecker.digitErrorCheck(1, input)) {
				System.out.println("桁数が妙に多いです。");
			} else {
				loop = false;
			}
		}
		if (optionIndex == 1 && option[0] > input) {
			System.out.println("桁数よりも少ない数で遊ぶことはできません");
			return;
		}
		option[optionIndex] = input;
	}

	public static double getOptionBonus(int[] option) {
		return Double.valueOf(option[0]) / 3 * (Double.valueOf(option[1]) + 1) / 10;
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
		System.out.print("betするcoin数を入力して！（0.やめる 999.オプションメニュー）　＞");
		return new java.util.Scanner(System.in).nextInt();
	}

	public static boolean bettingCheck(double bet, int coin, int[] option) {
		if (bet == 999) {
			option(option);
			return true;
		} else if (bet > coin) {
			System.out.println("手持ちのcoinをこえています。");
			return true;
		} else if (bet < 0) {
			System.out.println("0より低い数はbetできません。");
			return true;
		}
		return false;
	}

	public static void setup(int coin, double bet) {
		System.out.println(" -" + (int) bet + "coin");
		System.out.print(" 所持coin:" + coin);
	}

	public static void generateNum(int[] num, int[] option) {
		boolean loop = true;
		while (loop) {
			loop = false;
			for (int i = 0; i < option[0]; i++) {
				num[i] = new java.util.Random().nextInt(option[1]) + 1;
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

	public static int ansInput(double bet, int ansCount, int[] option) {
		System.out.println("　現在の報酬：+" + reward(bet, ansCount, option) + "coin　　");
		System.out.println();
		int input = 0;
		boolean inputLoop = true;
		while (inputLoop) {
			System.out.print(ansCount + "答目　＞");

			input = new java.util.Scanner(System.in).nextInt();
			if (tools.ErrorChecker.digitErrorCheck(option[0], input)) {
				System.out.println("妙に桁が多いです。");
			} else if (tools.ErrorChecker.numErrorCheck(tools.ErrorChecker.getBetweenNum(9, option[1]), input)) {
				System.out.println("使えない数字が含まれてます。");
			} else if (input == 0) {
				System.out.println("0は使用できません。");
			} else {
				inputLoop = false;
			}
		}
		return input;
	}

	public static void ansConvert(int[] ans, int input) {
		// 1桁ずつ抜き取ってans配列に移動
//		System.out.println(input);// debug
		for (int i = 0; i < ans.length; i++) {
			ans[ans.length - 1 - i] = input % 10;
			input /= 10;
		}
		// 左詰め
		int count = 1;
		while (ans[0] == 0) {
			for (int i = 0; i < ans.length - 1; i++) {
				ans[i] = ans[i + 1];
			}
			ans[ans.length - count] = 0;
			count++;
		}
	}

	public static void countDisplay(int[] num, int[] ans) {
//		for (int i = 0; i < ans.length; i++) { // debug
//			System.out.print(num[i] + " "); // debug
//		}
//		System.out.println();				//debug
//		for (int i = 0; i < ans.length; i++) { // debug
//			System.out.print(ans[i] + " "); // debug
//		}
		System.out.print(" " + hitCount(num, ans) + "hit " + blowCount(num, ans) + "blow ");
	}

	public static int endCheck(int hit, int coin, int getCoin, int[] option) {
		if (hit == option[0])
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

	public static int reward(double bet, int ansCount, int[] option) {
		double reward = bet * 5 * getOptionBonus(option) - bet / 5 * Double.valueOf(ansCount);
		return (int) reward;
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
}