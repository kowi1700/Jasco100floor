package blackjack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> cards;
	
	//コンストラクタ
	public Deck() {
		cards = new ArrayList<>();
		String[] suits = {"♡", "♢", "♧", "♤"};
		for(String suit : suits) {
			for(int i = 1; i <= 13; i++) {
				cards.add(new Card(suit, i));
			}
		}
		shuffle();
	}
	
	//メソッド
	//シャッフル
	public void shuffle() {
		Collections.shuffle(cards);
	}
	//カードを引く
	public Card draw() {
		return cards.remove(0);
	}
	//空かどうか調べる
	public boolean isEmpty() {
		return cards.isEmpty();
	}
}
