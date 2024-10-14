package Jasco;

public class Main {
	public static void main(String[] args) {
		int coin = 1; //初期の所持コイン
		boolean inJasco = true;
		int selectNum = 0; //選択肢用
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("[アナウンス]: ジャスコの100階へようこそ！夢のようなひとときをお過ごしください");
		System.out.println("-----------------------------------------------------------------");
		
		do {
			selectNum = Operation.askCommand(coin);
			switch(selectNum) {
			case 0 :  inJasco = Operation.goOut(coin);
			break;
			case 1 :  coin = GameCorner.selectGame(coin);
			break;
			case 2 : System.out.println("<ここには入れません>");
			break;
			case 3 : coin = Operation.walk(coin);
			break;
			case 4: Operation.hearAnnounce();
			break;
			default: System.out.println("[アナウンス]: 申し訳ありません。聞き取れませんでした");
			break;
			}
			
		}while(inJasco);
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("[アナウンス]: さようなら。今度はもっとうまくやりましょうね");
	}
	
	
}
