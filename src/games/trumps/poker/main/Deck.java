package games.poker.main;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<Card> deckcards;

	public Deck() {
		deckcards = new ArrayList<>();
		int[] suits = { 1, 2, 3, 4 };
		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

		for (int suit : suits) {
			for (int num : nums) {
				deckcards.add(new Card(suit, num));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deckcards);
	}

	public Card drawCard() {
		return deckcards.remove(deckcards.size() -1);
	}
}
