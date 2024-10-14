package Games;

/* 予定中の制作順序。
 * スコアの保存方法かんがえ中。
 * 		convertIndex作る（使用したカードを保持する。同役時の参照や、手札が6毎以上）
 * 		同役時の参照カードを保存可能なストレートメソッド
 * 		同様のフラッシュ、ロイヤル、ペア系メソッド、スコア判定統合メソッド
 * スコアを参照したディーラーのリロール判定メソッド
 * result関係
 * リトライ関係
 * オプション（リロール数等）によるコイン倍率の変化
 * pHandsとdHandsに分けてオプションとして追加
 * 
 */

public class Poker {
	public static int pokerMain(int coin) {
		int playerMax = 2;
		int dealerMax = 2;
		int handsMax = 5;
		int pRerollMax = 3;
		int dRerollMax = 3;

		int totalPersons = playerMax + dealerMax;
		String[] playerName = new String[] { "p1", "p2", null, null };
		int[] deck = new int[13 * 4];
		int[][] hands = new int[totalPersons][handsMax];
		int[] rerollIndex = new int[handsMax];
		int[] rerollStock = new int[totalPersons];
		int[][] score = new int[totalPersons][2];// 0に役、1に数
		int[][] input = new int[totalPersons][2];// 0にインプット、1にflag(0:初期値,1:openingAnnounce済,2:hold)

		for (int i = 0; i < playerMax; i++) {
			rerollStock[i] = pRerollMax;
		}
		for (int i = playerMax; i < dealerMax; i++) {
			rerollStock[i] = dRerollMax;
		}

//		debug
		int[][] debugHands = { { 6, 11, 8, 7, 10, 9, 12 } };
		int debugPlayer = 0;
		int[] x = straightJudge(debugPlayer, debugHands);
		System.out.println(x[0] + "---" + x[1] + "debugおわり");
		int y = new java.util.Scanner(System.in).nextInt();

		playerConf(playerName, playerMax, totalPersons);
		openingDraw(deck, hands);
		// プレイヤー数分のforループ→プレイヤーかディーラーのif→ホールド条件のwhileループ→ホールドならスキップ
		for (int nowPlayer = 0; nowPlayer < totalPersons; nowPlayer++) {// プレイヤー数文のforるーぷ
			if (nowPlayer < playerMax) {// プレイヤーorディーラー分岐
				while (input[nowPlayer][0] != 2) {// リロールやめるまでループ
					if (input[nowPlayer][0] == 0) {
						playerAnnounse(nowPlayer, playerName);
						input[nowPlayer][0] = 1;
					}
					HandsDisplay(nowPlayer, playerName, hands);
					RerollDisplay(nowPlayer, hands, input, rerollStock);
					inputAndErrorCheck(nowPlayer, hands, input);
					if (input[nowPlayer][0] != 2) {// リロールやめたら後半スキップ
						getRerollIndex(nowPlayer, hands, input, rerollIndex);
						rerollDraw(nowPlayer, hands, rerollIndex, deck);
						rerollStock[nowPlayer]--;
					}
				}
				return coin;
			} else {
				playerAnnounse(nowPlayer, playerName);
				HandsDisplay(nowPlayer, playerName, hands);
//					dealerRerollJudge();
				
				
			}
		}
		
		result();
		
	}

	public static void playerConf(String[] playerName, int playerMax, int totalPersons) {
		for (int i = 0; i < playerMax; i++) {
			if (playerName[i] == null) {
				System.out.println("player" + (i + 1) + "の名前を入れて");
				playerName[i] = new java.util.Scanner(System.in).nextLine();
			}
		}
		for (int i = playerMax; i < totalPersons; i++) {
			if (playerName[i] == null) {
				playerName[i] = "dealer" + (i - playerMax + 1);
			}
		}

		for (String value : playerName)
			System.out.print(value + " ★ ");
		System.out.println("以上、" + totalPersons + "名のプレイヤーでお送りします。");
	}

	public static void openingDraw(int[] deck, int[][] hands) {
		for (int i = 0; i < hands.length; i++) {
			for (int j = 0; j < hands[i].length; j++) {
				hands[i][j] = tempDraw(deck);
			}
		}
	}

	public static void playerAnnounse(int nowPlayer, String[] playerName) {
		System.out.println();
		System.out.println();
		System.out.println(playerName[nowPlayer] + "'s turn");
	}

	public static void HandsDisplay(int nowPlayer, String[] playerName, int[][] hands) {
		System.out.print(playerName[nowPlayer] + " ");
		for (int i = 0; i < hands[0].length; i++) {
			System.out.print("【" + convertCard(hands[nowPlayer][i]) + "】 ");

		}
	}

	public static void RerollDisplay(int nowPlayer, int[][] hands, int[][] input, int[] rerollStock) {
		System.out.print("(残りリロール" + rerollStock[nowPlayer] + "回) ");
		if (rerollStock[nowPlayer] == 0) {
			input[nowPlayer][0] = 2;
		} else {
			System.out.print("1~" + hands[nowPlayer].length + ".reroll");
		}
		System.out.print("  0.hold ＞");
	}

	// リロールの枚数とインデックスの最大値チェック
	// エラーが出たらインプットからループ
	public static void inputAndErrorCheck(int nowPlayer, int hands[][], int[][] input) {
		while (true) {
			input[nowPlayer][1] = new java.util.Scanner(System.in).nextInt();
			if (input[nowPlayer][1] == 0) {
				input[nowPlayer][0] = 2;
				return;
			}
			int temp = input[nowPlayer][1];
			int digit = 0;
			while (temp != 0) {
				if (temp % 10 > hands[nowPlayer].length)
					System.out.print(temp + "は無効です。　＞");
				digit++;
				temp /= 10;
			}
			if (digit > hands[nowPlayer].length)
				System.out.print(digit - hands[nowPlayer].length + "桁多いです。 ＞");

			if (digit <= hands[nowPlayer].length)
				return;
		}
	}

	public static void getRerollIndex(int nowPlayer, int[][] hands, int[][] input, int[] rerollIndex) {
		for (int i = 0; i < hands[nowPlayer].length; i++) {
			rerollIndex[i] = (input[nowPlayer][1] % 10 - 1);
			input[nowPlayer][1] /= 10;
//			System.out.println(rerollIndex[i]); // debug
		}
	}

	public static void rerollDraw(int nowPlayer, int[][] hands, int[] rerollIndex, int[] deck) {
		for (int i = 0; i < hands[nowPlayer].length; i++) {
			if (rerollIndex[i] == -1)
				break;
			// System.out.print(rerollIndex[i]+"★");//debug
			hands[nowPlayer][(rerollIndex[i])] = tempDraw(deck);
		}
	}

	public static void dealerRerollJudge(int nowPlayer, String[] playerName, int[][] hands, int[] rerollStock,
			int[] deck) {
//			if (score[nowPlayer] < dealerJudge) {

//			}
	}

	// カードを引く前にデッキを確認するメソッド。
	public static int tempDraw(int[] deck) {
		while (true) {
			int tempDraw = new java.util.Random().nextInt(deck.length);
			if (deck[tempDraw] == 0) {
				deck[tempDraw]++;
				// System.out.print(tempDraw+" ");
				return tempDraw;
			}
		}
	}

	// カードindexをdisplay用に変換するメソッド。
	public static String convertCard(int card) {
		String[] suit = new String[] { "♠", "♡", "♦", "♧" };
		String cardMark = "";
		int cardNum = card % 13 + 1;

		switch (cardNum) {
		case 1:
			cardMark = "A";
			break;
		case 11:
			cardMark = "J";
			break;
		case 12:
			cardMark = "Q";
			break;
		case 13:
			cardMark = "K";
			break;
		default:
			break;
		}
		String convertedCard = "";
		if (cardNum > 10 || cardNum == 1) {
			convertedCard = suit[card / 13] + (cardMark);
		} else {
			convertedCard = suit[card / 13] + (cardNum);
		}
		return convertedCard;
	}
//
//	// インデックスの数だけ100倍するメソッド
//	public static int convertDigit(int num, int count) {
//		int convertedDigit = num;
//		for (int i = 0; i < count; i++) {
//			convertedDigit *= 100;
//		}
//		return convertedDigit;
//	}
//	
//	public static void result(int score[][],int totalPersons) {
//		for (int i = 0; i < totalPersons; i++) {
//			scoreClearing(score,i);
//		}
//		
//		
//	}
//
//	public static void scoreClearing(int[][] score, int p) {
//		if (royalJudge[0] == 4 && flashJudge[0] == 4)
//			score[p][0] = 900;
//			score[p][1] = royalJudge[1];//使ったカードのindexだけ保存
//
//		if (straightJudge && flashJudge)
//			Score[p][0] = 800;
////			if(forcard();)
////				comboScore[p][0] =7;
////			if(fullhouse();)
////				comboScore[p][0] = 8;
//	}
//
//	public static int[] straightJudge(int nowPlayer, int[][] hands) {
//		int[] straight = new int[2]; // 0：成立判定 1:使用した数
//		for (int i = 0; i < hands[nowPlayer].length; i++) { // 基準になる手札i
//			straight[0] = 0;
//			for (int j = 1; j < 5; j++) { // i=k+1ならkをリセットして+2を探す
//				for (int k = 0; k < hands[nowPlayer].length; k++) { // 比べる手札k
//					if ((hands[nowPlayer][i] % 13 + 1) == (hands[nowPlayer][k] % 13 + 1) + j) {
//						straight[0]++;
//						if (straight[0] == 4) {
//							straight[1] += hands[nowPlayer][i];
//							return straight;
//						}
//					}
//				}
//			}
//		}
//		straight[0] = 0;
//		straight[1] = 0;
//		return straight;
//	}
//
//	public static int flashJudge(int nowPlayer, int[][] hands) {
//
//		// フラッシュ判定
//		int flash = 0;
//		for (int i = 1; i < hands[nowPlayer].length; i++) {
//			if (hands[nowPlayer][0] / 13 == hands[nowPlayer][i] / 13) {
//				flash++;
//			} else {
//				break;
//			}
//		}
//		return flash;
//	}
//
//	public static int royal(int nowPlayer, int[][] hands) {
//		int royal = 0;
//		for (int i = 1; i < hands[nowPlayer].length; i++) {
//			if (hands[nowPlayer][0] % 13 == hands[nowPlayer][i] / 13) {
//				royal++;
//			} else {
//				break;
//			}
//		}
//		return royal;
//	}
//}
