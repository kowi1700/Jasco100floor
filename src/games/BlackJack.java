package games;

public class BlackJack {
	//public static void main(String[] args) {//デバッグ用
	public static int mainMachine(int coin) {
		boolean inGame = true;
		System.out.println("ブラックジャックへようこそ！");
		System.out.println("-----------------------");
			
		do {
			//賭け金を決める
			int bet = betCoin(coin);
			if(bet > 0) {
				coin -= bet;
				inGame = startGame();
				//手札を取得
				int[] playerHand = drawCard();
				int[] dealerHand = drawCard();
				//手札を表示する
				System.out.print("あなたの手札: ");
				displayHand(playerHand);
				System.out.println();
				//手札を表示する(ディーラー)
				System.out.print("ディーラーの手札: ");
				displayHand(dealerHand);
				System.out.println();
				//追加で手札を引く
				playerHand = addCard(playerHand);
				//手札を表示する
				System.out.print("ディーラーの手札: ");
				displayHand(dealerHand);
				System.out.println();
				//追加で手札を引く(ディーラー)
				dealerHand = dealerAdd(dealerHand);
				//手札を表示する
				System.out.print("あなたの手札: ");
				displayHand(playerHand);
				//スコアを取得する
				int[] scores = getScore(playerHand, dealerHand);
				//勝敗を判定する
				bet = judgeWinner(scores, bet);
				coin += bet;
				System.out.printf("[ 所持金: %d coin ]%n", coin);
				//リトライを尋ねる
				inGame = askRetry();
			}else {
				inGame = false;
			}
		}while(inGame);
		
		return coin;
	}
	
	//賭け金を決めるメソッド
	public static int betCoin(int coin) {
		int bet = 0;
		if(coin > 0) {
			System.out.println("いくら賭けますか？");
			System.out.printf("[ 所持金: %d coin ]%n", coin);
			String input = new java.util.Scanner(System.in).nextLine();
			try {
				int inputBet = Integer.parseInt(input);
				if(inputBet > coin || inputBet <= 0) {
					System.out.println("つまらない冗談なら よそでどうぞ");
					bet = -1;
				}else {
					System.out.printf("[ 賭け金: %d coin]%n", inputBet);
					bet = inputBet;
				}
			}catch(NumberFormatException e) {
				System.out.println("賭け金 といえば 数字 をいれるものですよ");
			}
		}else {
			System.out.println("おカネも ないのに 遊ぶのはにんげんの底 だ");
			bet = -1;
		}
		return bet;
	}
	
	//ゲームを開始するメソッド
	public static boolean startGame() {
		boolean startGame = false;
		while(true) {
			System.out.println("[Enter]を押して手札を引きます");
			String input = new java.util.Scanner(System.in).nextLine();
			if(input.equals("")) {
				startGame = true;
				break;
			}else {
				System.out.println("遊びくらいはまじめにやりましょうよ");
			}
		}
		return startGame;
	}
	
	//手札を取得するメソッド
	public static int[] drawCard() {
		int[] hand = new int[2];
		for(int i = 0; i < hand.length; i++) {
			hand[i] = new java.util.Random().nextInt(13) + 1;
		}
		return hand;
	}
	
	//手札を表示するメソッド
	public static void displayHand(int[] hand) {
		for(int card : hand) {
			switch(card) {
			case 1 : System.out.print("[A]");
			break;
			case 11 : System.out.print("[J]");
			break;
			case 12 : System.out.print("[Q]");
			break;
			case 13 : System.out.print("[K]");
			break;
			default : System.out.printf("[%d]", card);
			break;
			}
		}
	}
	
	//追加で手札を引くメソッド
	public static int[] addCard(int[] hand) {
		boolean yesAdd = false;
		do {
			System.out.println("追加でカードを引きますか？[Enter] Yes / [0] No");
			String input = new java.util.Scanner(System.in).nextLine();
			if(input.equals("")) {
				yesAdd = true;
				int[] newHand = new int[hand.length + 1];
				for(int i = 0; i <= hand.length; i++) {
					if(i == hand.length) {
						newHand[i] = new java.util.Random().nextInt(13) + 1;
					}else{
						newHand[i] = hand[i];
					}
				}
				hand = newHand;
				System.out.print("あなたの手札: ");
				displayHand(hand);
				System.out.println();
			}else if(input.equals("0")) {
				yesAdd = false;
			}
		}while(yesAdd);
		return hand;
	}
	
	//ディーラーが追加で手札を引く
	public static int[] dealerAdd(int[] hand) {
		int sum = 0;
		for(int card : hand) {
			sum += card;
		}
		while(sum < 15) {
			int[] newHand = new int[hand.length + 1];
			for(int i = 0; i <= hand.length; i++) {
				if(i == hand.length) {
					newHand[i] = new java.util.Random().nextInt(5) + 1;
				}else {
					newHand[i] = hand[i];
				}
			}
			sum += newHand[newHand.length - 1];
			hand = newHand;
			System.out.println("ディーラーがカードを引きます");
			System.out.print("ディーラーの手札: ");
			displayHand(hand);
			System.out.println();
		}
		System.out.println("ディーラーはこれ以上カードを引きません");
		return hand;
	}
	
	//スコアを取得するメソッド
	public static int[] getScore(int[] hand, int[]hand1) {
		int sum = 0; int sum1 = 0;
		for(int card : hand) {
			sum += card;
		}
		for(int card : hand1) {
			sum1 += card;
		}
		int[] scores = new int[2];
		scores[0] = sum; //プレイヤー
		scores[1] = sum1; //ディーラー
		return scores;
	}
	
	//勝敗を判定するメソッド
	public static int judgeWinner(int[] scores, int bet) {
		System.out.println();
		if(scores[0] == 21) System.out.println("プレイヤーは、BLACKJACK!!");
		if(scores[1] == 21) System.out.println("ディーラーは、BLACKJACK!!");
		if(scores[0] > 21) {
			System.out.println("プレイヤーは、BURST...");
			scores[0] = 0;
		}
		if(scores[1] > 21) {
			System.out.println("ディーラーは、BURST...");
			scores[1] = 0;
		}
		
		System.out.printf("あなたのスコア: %d / ディーラーのスコア: %d%n", scores[0], scores[1]);
		if(scores[0] > scores[1]) {
			System.out.println("プレイヤーの勝ち！");
			System.out.printf("[ 賭け金: %d coin]%n", bet);
			bet *= 2;
			System.out.printf("[ 報酬: %d coin]%n", bet);
		}else if(scores[0] < scores[1]) {
			System.out.println("ディーラーの勝ち！");
			bet = 0;
			System.out.printf("[ 賭け金: %d coin]%n", bet);
		}else {
			System.out.println("引き分けです");
			System.out.printf("[ 賭け金: %d coin]%n", bet);
			System.out.printf("[ 報酬: 0 coin]%n");
		}
		return bet;
	}
	
	//リトライを尋ねるメソッド
	public static boolean askRetry() {
		System.out.println("[Enter]: もう一度 / [それ以外]: もうやめる");
		String input = new java.util.Scanner(System.in).nextLine();
		if(input.equals("")) {
			return true;
		}else {
			return false;
		}
	}
}
