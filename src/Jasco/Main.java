package Jasco;
//import Games.*;

public class Main {
	public static void main(String[] args) {
		int coin = 0; //初期の所持コイン
		boolean inJasco = true;
		int selectNum = 0; //選択肢用
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("[アナウンス]: ジャスコの100階へようこそ！夢のようなひとときをお過ごしください");
		System.out.println("-----------------------------------------------------------------");
		
		do {
			selectNum = Oparation.askCommand(coin);
			switch(selectNum) {
			case 0 :  inJasco = Oparation.goOut(coin);
			break;
			case 1 :  coin = Oparation.walk(coin);
			break;
			}
			
		}while(inJasco);
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("[アナウンス]: さようなら。今度はもっとうまくやりましょうね");
	}
	
	
}
