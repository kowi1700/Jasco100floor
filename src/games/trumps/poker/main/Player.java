package games.poker.main;

import java.util.ArrayList;

public class Player {
	Option option;

	String name;
	int reroll;
	boolean hold = false;
	int input;

	int comboScore;
	int numScore;
	ArrayList<Card> hands = new ArrayList<Card>();
	ArrayList<Card> usedHands = new ArrayList<Card>();
	ArrayList<Card> tiedHands = new ArrayList<Card>();
	int royalCount = 0;
	int flashCount = 0;
	int straightCount = 0;
	ArrayList<Integer> numMatch = new ArrayList<Integer>();
	ArrayList<Integer> suitMatch = new ArrayList<Integer>();

	public Player(Option option, int index) {
		this.option = option;
		this.name = "player" + (index + 1);
		this.reroll = option.getMaxReroll();
		if (reroll == 0)
			this.hold = true;
	}

	public ArrayList<Card> getHands() {
		return hands;
	}

	public String getName() {
		return name;
	}

	public void drawCard(Deck deck, int index) {
		hands.add(deck.drawCard());
	}

	public void rerollCard(Board board, int index) {
		board.grave.addGrave(hands.get(index));
		hands.set(index, board.deck.drawCard());
	}

	public void giftUsed(int index) {
		usedHands.add(hands.remove(index));
	}

	public void allUsedToTied() {
		for(int i = 0 ; i< usedHands.size();i++) {
			tiedHands.add(usedHands.remove(i));
		}
	}

	public void openingDraw(Deck deck) {
		for (int i = 0; i < option.getMaxHands(); i++) {
			drawCard(deck, i);
		}
	}

	public void turnReroll(Board board, ArrayList<Integer> indexs) {
		for (int index : indexs)
			rerollCard(board, index);
		reroll--;
		if (reroll == 0)
			hold = true;
	}

	public void rerollinput(Player player) {
		System.out.print("　1~" + (option.getMaxHands() ) + ".reroll(複数選択可) 0.hold ＞");
		input = new java.util.Scanner(System.in).nextInt();
	}

}
