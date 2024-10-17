package jasco;
import jankDou.brokenGame;

public class GameCorner2 {
	public static int selectGame(int coin) {
		boolean inGames = true;
		
		if(coin > 0) {
			do {
				System.out.printf("[ 所持金: %d coin ]  ここはゲームコーナーの奥だ。なにで遊ぼうか？%n", coin);
				System.out.println("[0]: 戻る [1]: (故障中) [2]: (故障中) [3]: (故障中) [4]: 奥にすすむ");
				String input = new java.util.Scanner(System.in).nextLine();
				try {
					int selectNum = Integer.parseInt(input);
					switch(selectNum) {
					case 0 :  coin = GameCorner.selectGame(coin);
					break;
					case 1 : coin = brokenGame.brokenDisplay(coin);
					break;
					case 2 : coin = brokenGame.brokenDisplay(coin);
					break;
					case 3 : coin = brokenGame.brokenDisplay(coin);
					break;
					case 4 : 
					default : System.out.println("[アナウンス]: 好奇心もほどほどに！猫 しんじゃった！");
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
