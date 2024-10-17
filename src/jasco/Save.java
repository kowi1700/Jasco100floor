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
		double id = new java.util.Scanner(System.in).nextInt();
		double pass = coin * (1207 + id * 10) + 222 * id;
		System.out.println("coinをお預かりいたしました");
		System.out.println("ID:" + (int) id + " PASS:" + (int) pass);
		System.out.println("次回、来店時にお伝えください");
		System.out.print(" -" + coin + "coin	please enter　＞");
		coin = 0;
		new java.util.Scanner(System.in).nextLine();
		return coin;
	}

	public static int load(int coin) {
		System.out.print("4桁のIDを入力してください　＞");
		double id = new java.util.Scanner(System.in).nextInt();
		System.out.print("PASSを入力してください　＞");
		double pass = new java.util.Scanner(System.in).nextInt();
		double temp = (pass - 222 * id) / (1207 + id * 10);
		if (temp % 1 != 0) {
			System.out.println("不正な入力です");
			return coin;
		}
		System.out.print("coinをご返却致します");
		if (temp > coin)
			System.out.println(" +" + (temp - coin) + "coin　 please enter　＞");
		if (coin > temp)
			System.out.println(temp - coin + "coin	please enter　＞");
		if (coin == temp)
			System.out.println("±0coin	please enter　＞");
		System.out.println("夢のような時間をお過ごしください");
		coin = (int) temp;
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
