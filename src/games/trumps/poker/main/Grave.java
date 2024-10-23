package games.poker.main;

import java.util.ArrayList;

public class Grave {
	ArrayList<Card> gravecards;
	public Grave() {
		gravecards = new ArrayList<>();
	}
	
	public void addGrave(Card card) {
		gravecards.add(card);
	}
	
	
}
