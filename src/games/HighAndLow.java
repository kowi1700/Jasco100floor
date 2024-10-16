package games;

public class HighAndLow {
	public static int mainMachine(int coin) {
		boolean inGame = true;
		int bet = 0;
		System.out.println("ハイアンドローへようこそ");
		
		do {
			System.out.println("いくら賭けますか？");
			System.out.printf("[ 所持金: %d coin ]%n", coin);
			String input = new java.util.Scanner(System.in).nextLine();
			try{
				bet = Integer.parseInt(input);
				if(bet > 0 && coin >= bet) {
					while(inGame) {
						
						//ランダムな数字を表示するメソッド1
						int oldNum = randomNum();
						//入力を受け付けるメソッド
						boolean isHigh = isHigh();
						//ランダムな数字を表示するメソッド2
						int newNum = randomNum();
						//勝敗を判定するメソッド
						bet = judgeWinner(bet, isHigh, oldNum, newNum);
						if(bet == 0) inGame = false;
						//勝ち金の表示
						System.out.printf("[ 現在の賭け金: %d coin ]%n", bet);
						//続けるか尋ねるメソッド
						inGame = askContinue();
					}
				}else {
					System.out.println("もう、冗談はやめてくださいよ");
					input = "冗談";
				}
				
			}catch(NumberFormatException e) {
				System.out.println("もう、冗談はやめてくださいよ");
			}
			
		}while(coin > 0 && bet > 0 && inGame);
		
		coin += bet;
		System.out.println("ハイ&ローを終了します");
		System.out.printf("%d coin 獲得　[ 所持金: %d coin ]  %n", bet, coin);
		return coin;
	}
	
	//ランダムな数字を表示するメソッド
	public static int randomNum() {
		int num = new java.util.Random().nextInt(13) + 1;
		switch(num) {
		case 13 : System.out.println("カードは...> " + "K" + " <");
		break;
		case 12 : System.out.println("カードは...> " + "Q" + " <");
		break;
		case 11 : System.out.println("カードは...> " + "J" + " <");
		break;
		case 1 :  System.out.println("カードは...> " + "A" + " <");
		break;
		default : System.out.println("カードは...> " + num + " <");
		break;
		}
		return num;
	}
	
	//入力を受け付けるメソッド
	public static boolean isHigh() {
		boolean isHigh = false;
		int input = -1;
		
		do {
			System.out.println("[0]: ハイ / [1]: ロー");
			input = new java.util.Scanner(System.in).nextInt();
			switch(input) {
			case 0 : 
				isHigh = true;
			break;
			case 1 : 
			break;
			default : System.out.println("エラー: 範囲外の入力です");
			break;
			}
		}while(input > 1 || input < 0);
		
		return isHigh;
	}
	
	//勝敗を判定するメソッド
	public static int judgeWinner(int bet, boolean isHigh, int oldNum, int newNum) {
		if(oldNum > newNum) {
			if(isHigh) {
				System.out.println("ざんねーん");
				bet = 0;
			}else {
				System.out.println("おめでとう！");
				bet *= 2;
			}
		}else if(oldNum < newNum) {
				if(isHigh) {
				System.out.println("おめでとう！");
				bet *= 2;
			}else {
				System.out.println("ざんねーん");
				bet = 0;	
			}
		}else {
				System.out.println("ひきわけだよ");
		}
		return bet;
	}
	
	//ゲームを続けるか尋ねるメソッド
	public static boolean askContinue() {
		boolean inGame = true;
		while(true) {
			System.out.println("ゲームを続けるならEnter、やめるならQを押してね");
			String input = new java.util.Scanner(System.in).nextLine();
			if(input.equals("Q") || input.equals("q")) {
				inGame = false;
				break;
			}else if(input.equals("")) {
				break;
			}else {
				System.out.println("話はちゃんと聞いてね！");
			}
		}
		return inGame;
	} 
}
