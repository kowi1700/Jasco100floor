package blackjack;
import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Card> hand;
	
	//コンストラクタ
	public Player() {
		hand = new ArrayList<>();
	}
	
	//メソッド
	//カードを引く
	public void drawCard(Deck deck) {
		hand.add(deck.draw());
	}
	//手札の数値合計を取得
	public int getHandValue() {
		int total = 0;
		int aceCount = 0;
		
		for(Card card : hand) {
			total += card.getValue();
			if(card.getValue() == 11) { //Aだった場合
				aceCount++;
			}
		}
		
		while(total > 21 && aceCount > 0) {
			total -= 10; //バースト時はAを1と判断
			aceCount--;
		}
		
		return total;
	}
	//バーストの判断
	public boolean isBusted() {
		return getHandValue() > 21;
	}
	
	//手札の取得
	public String toString() {
		return hand.toString();
	}
	
}
