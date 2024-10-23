package games.poker.main;

public class Card {
	int suit;
	int num;

	public Card(int suit, int num) {
		this.suit = suit;
		this.num = num;
	}
	 @Override
	public String toString() {
		String suitName;
		switch (this.suit) {
		case 1:
			suitName = "♠";
			break;
		case 2:
			suitName = "♡";
			break;
		case 3:
			suitName = "♦";
			break;
		case 4:
			suitName = "♧";
			break;
		default:
			suitName = "ErrorSuit";
			break;
		}

		String numName;
		switch (this.num) {
		case 1:
			numName = "A";
			break;
		case 11:
			numName = "J";
			break;
		case 12:
			numName = "Q";
			break;
		case 13:
			numName = "K";
			break;
		default:
			numName = String.valueOf(this.num);
		}
		return suitName + numName;
	}
}
