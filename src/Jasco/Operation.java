package Jasco;
import java.time.LocalTime;
import java.util.Random;

public class Operation {
	
	//選択肢を尋ねるメソッド
	public static int askCommand(int coin) {
		int selectNum = -1;
		
		do {
			System.out.printf("[ 所持金: %d coin ]  なにをする？%n", coin);
			System.out.println("[0]: 出ていく [1]: ゲームコーナー [2]: (未定) [3]: 歩く [4]: アナウンスを聞く");
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
	
	//アナウンスを聞くメソッド
	public static void hearAnnounce() {
		LocalTime currentTime = LocalTime.now();
		Random random = new Random();
		
		System.out.println("アナウンス、でも聞こう");
		int num = random.nextInt(4);
		switch(num) {
		case 0 : 
			System.out.println("[アナウンス]: 10000コイン貯めて、ハワイへ行こう！ジャスコでは、皆様への日頃の感謝のし");
			System.out.println("....ザザーーーーーーーーーーーーーーーーーーーー");
			System.out.println("これ以上、は聞き取れないよ");
		break;
		case 1 : 
			System.out.println("[アナウンス]: ただいまの時刻は" + currentTime + " です");
		break;
		case 2 : 
			System.out.println("[アナウンス]: おカネがないならカラダを使いましょう！アタマがないんですから");
		break;
		default : 
			System.out.println("..................................................");
			System.out.println("とおくで、小さなわらい声がした気がする");
		}
	}
}
