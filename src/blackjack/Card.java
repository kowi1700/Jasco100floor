package blackjack;

public class Card {
	String suit;
	int value;
	
	//コンストラクタ
	public Card(String suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	//メソッド
	//絵柄を取得
	public String getSuit() {
		return suit;
	}
	
	//数値を取得
	public int getValue() {
		if(value == 1) { //1はデフォルトで11
			return 11;
		}else if(value > 10) { //10以上は10の扱い
			return 10;
		}
		return value;
	}
	
	//数値と絵柄を取得
	public String toString() {
		return suit + " " + value;
	}
}
