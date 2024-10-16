package jasco;

public class GameCorner {
	public static int selectGame(int coin) {
		boolean inGames = true;
		
		if(coin > 0) {
			do {
				System.out.printf("[ 所持金: %d coin ]  ここはゲームコーナーだ。なにで遊ぼうか？%n", coin);
				System.out.println("[0]: 戻る [1]: 「ヒット＆ブロー」 [2]: 「ハイ&ロー」 [3]: (未定) [4]: (未定)");
				String input = new java.util.Scanner(System.in).nextLine();
				try {
					int selectNum = Integer.parseInt(input);
					switch(selectNum) {
					case 0 : 
						System.out.println("ゲームコーナーを出ます");
						inGames = false;
					break;
					case 1 : coin = games.HitAndBlow.Hit_and_blow(coin);
					break;
					case 2: coin = games.HighAndLow.mainMachine(coin);
					break;
					default : System.out.println("[アナウンス]: 時間は寝るほどあります！もっともっともっと遊びましょう！");
					break;
					}
					
				} catch(NumberFormatException e){
					System.out.println("[アナウンス]: 申し訳ありません。どうか数字でお話ください");
				}
			}while(inGames);
		}else {
			System.out.println("おカネ、もないのに、遊ぶなんて、とんでもないことだ");
		}
		
		return coin;
	}
}
