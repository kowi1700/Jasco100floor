package jasco;

public class Save {
	public static int saveMenu(int coin) {
		while (true) {
			System.out.println("ご用件をお伺いいたします");
			System.out.print("1.coinを預ける　2.coinを引き出す 3.戻る　＞");
			int input = new java.util.Scanner(System.in).nextInt();
			System.out.println();
			switch (input) {
			case 1:
				coin = save(coin);
				continue;
			case 2:
				coin = load(coin);
				continue;
			case 3:
				return coin;
			default:
				System.out.println("変なボタンおさないで。");
				continue;
			}
		}
	}

	public static int save(int coin) {
		System.out.print("4桁の好きな数字を入力してください　＞");
		String strId = new java.util.Scanner(System.in).nextLine();
		try {	
				if(strId.length() > 3 && strId.length() < 5) {
					int id = Integer.parseInt(strId);
					int pass = coin * (1207 + id * 10) + 222 * id;
					String hexPass = Integer.toHexString(pass);
					System.out.println("coinをお預かりいたしました");
					System.out.println("ID:" + strId + " PASS:" + hexPass);
					System.out.println("次回、来店時にお伝えください");
					System.out.print(" -" + coin + "coin	please enter　＞");
					coin = 0;
					new java.util.Scanner(System.in).nextLine();
			}else {
				System.out.println("わたしは 4桁ではない数字は ぜんぶきらいです");
				System.out.println("------------------------------------");
			}
		}catch(NumberFormatException e) {
			System.out.println("ああ　読めませんでしたか 申し訳ございません");
			System.out.println("　　　す   う　　じ　　　をいれてくださいね");
			System.out.println("------------------------------------");
		}
		return coin;
	}

	public static int load(int coin) {
		System.out.print("4桁のIDを入力してください　＞");
		String strId = new java.util.Scanner(System.in).nextLine();
		double id = Integer.parseInt(strId);
		System.out.print("PASSを入力してください　＞");
		String hexPass = new java.util.Scanner(System.in).nextLine();
		double pass = Integer.parseInt(hexPass, 16);
		double temp = (pass - 222 * id) / (1207 + id * 10);
		if (temp % 1 != 0) {
			System.out.println("不正な入力です");
			return coin;
		}
		System.out.print("coinをご返却致します");
		if (temp > coin) {
			System.out.println(" +" + (int)(temp - coin) + "coin　 please enter　＞");
		}
		if (coin > temp) {
			System.out.println((int)(temp - coin) + "coin	please enter　＞");
		}
		if (coin == temp) {
			System.out.println("±0coin	please enter　＞");
		}
		System.out.println("夢のような時間をお過ごしください");
		coin += Math.abs(temp - coin);
		return coin;
	}

	public static int sEncryptionecret(int coin, int id) {
		int temp = coin * (1200 + id) + 222 * id;
		System.out.println(temp);
		int x = temp / 100 % 1000;
		System.out.println(x);

		return coin;
	}
}
