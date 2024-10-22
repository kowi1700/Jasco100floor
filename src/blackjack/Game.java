package blackjack;

public class Game {
	Deck deck;
	Player player;
	Player dealer;
	
	//コンストラクタ
	public Game() {
		deck = new Deck();
		player = new Player();
		dealer = new Player();
	}
	
	//メソッド
	//本編
	public void start() {
		player.drawCard(deck);
		player.drawCard(deck);
		dealer.drawCard(deck);
		dealer.drawCard(deck);
		
		System.out.println("プレイヤーの手札: " + player);
		System.out.println("ディーラーの手札: " + dealer.toString());
		
		//プレイヤーのターン
		while(player.getHandValue() < 21) {
			System.out.println("ヒットまたはスタンドしますか? (h/s)");
			String action = new java.util.Scanner(System.in).nextLine();
			if(action.equals("h")) {
				player.drawCard(deck);
				System.out.println("プレイヤーの手札: " + player);
			}else {
				break;
			}
		}
		//バーストだったら負け
		if(player.isBusted()) {
			System.out.println("バースト！残念でした");
			return;
		}
		
		//ディーラーのターン
		while(dealer.getHandValue() < 17) { 
			dealer.drawCard(deck);
		}
		
		if(dealer.isBusted() || player.getHandValue() > dealer.getHandValue()) {
			System.out.println("あなたの 勝ち！");
		}else if(player.getHandValue() == dealer.getHandValue()){
			System.out.println("引き分けだよ");
		}else {
			System.out.println("ディーラーの 勝ち");
		}
	}
}
