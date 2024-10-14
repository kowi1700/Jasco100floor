package Jasco;

public class Oparation {
	
	//選択肢を尋ねるメソッド
	public static int askCommand(int coin) {
		int selectNum = -1;
		
		do {
			System.out.printf("[ 所持金: %d coin ]  なにをする？%n", coin);
			System.out.println("[0]: 出ていく [1]: 歩く [2]: 「ダイスポーカー」 [3]: (故障中) [4]: (故障中)");
			String input = new java.util.Scanner(System.in).nextLine();
			try {
				selectNum = Integer.parseInt(input);
				return selectNum;
			} catch(NumberFormatException e){
				System.out.println("[アナウンス]: 申し訳ありません。どうか数字でお話ください");
				return selectNum;
			}
		}while(selectNum < 0);
	}
	
	//出ていくメソッド
	public static boolean goOut(int coin) {
		boolean inJasco = true;
		int selectNum = -1;
		
		System.out.printf("[ 所持金: %d coin ]  おカネをぜんぶ捨てて、ほんとうに出ていく？%n", coin);
		System.out.println("[0]: 出ていく [1]: ひきかえす");
		String input = new java.util.Scanner(System.in).nextLine();
		try {
			selectNum = Integer.parseInt(input);
			switch(selectNum) {
			case 0 : 
				if(coin > 0) {
					System.out.printf("%d coin を失った。%n", coin);
				}else if(coin <= 0) {
					System.out.println("捨てるおカネもなくて、恥ずかしい");
				}
				inJasco = false;
			break;	
			case 1 : 
				System.out.println("[アナウンス]: おかえりなさい！夢のようなひとときをお過ごしください");
			break;
			default : System.out.println("[アナウンス]: 申し訳ありません。聞き取れませんでした");
			break;
			}
			
		} catch(NumberFormatException e) {
			System.out.println("[アナウンス]: 申し訳ありません。どうか数字でお話ください");
			return inJasco;
		}
		return inJasco;
		
	}
	
	//歩くメソッド
	public static int walk(int coin) {
		System.out.printf("[ 所持金: %d coin ]  何歩くらい、あるいてみようか？%n", coin);
		String input = new java.util.Scanner(System.in).nextLine();
		try {
			long steps = System.currentTimeMillis() + Integer.parseInt(input);
			do {
                System.out.printf("うろ%n");
            } while (System.currentTimeMillis() < steps);
			if(steps % 2 == 0) {
				System.out.println();
				System.out.println("あ、1coin ひろった！");
				coin++;
				return coin;
			}else {
				System.out.println();
				System.out.println("同じところ、もどってきたみたい");
				return coin;
			}
			
		} catch(NumberFormatException e) {
			System.out.println("そんなに、あるけないよ");
			return coin;
		}
	}
}
