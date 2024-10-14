package Jasco;

public class GameCorner {
	public static int selectGame(int coin) {
		boolean inGames = true;
		
		if(coin > 0) {
			do {
				System.out.printf("[ 所持金: %d coin ]  ゲームコーナーだ。なにで遊ぼうか？%n", coin);
				System.out.println("[0]: 戻る [1]: 「ダイスポーカー」 [2]: 「ハイ&ロー」 [3]: (未定) [4]: (未定)");
				String input = new java.util.Scanner(System.in).nextLine();
				try {
					int selectNum = Integer.parseInt(input);
					switch(selectNum) {
					case 0 : 
						System.out.println("ゲームコーナーを出ます");
						inGames = false;
					break;
					case 1 : coin = Games.dicepoker.dicePoker(coin);
					break;
					case 2: coin = Games.HighAndLow.mainMachine(coin);
					default : System.out.println("[アナウンス]: 申し訳ありません。聞き取れませんでした");
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
