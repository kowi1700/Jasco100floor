package games.poker.main;

import java.util.ArrayList;

public class Execute {

	public void OpeningDraw(Board board, Option option) {
		for (int i = 0; i < board.allPlayers.length; i++) {
			for (int j = 0; j < option.getMaxHands(); j++)
				board.allPlayers[i].drawCard(board.deck, j);
		}
	}

	public boolean allHold(Player[] allPlayers) {
		for (Player player : allPlayers) {
			if (!player.hold) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Integer> getIndexFromDigit(int input) {
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		while (input > 0) {
			indexs.add(input % 10 - 1);
			input /= 10;
		}
		return indexs;
	}

	public void sort(ArrayList<Card> hands) {
		for (int i = 0; i < hands.size() - 1; i++) {
			for (int j = i + 1; j < hands.size(); j++) {
				if (hands.get(i).num < hands.get(j).num) {
					Card temp = hands.get(i);
					hands.set(i, hands.get(j));
					hands.set(j, temp);
				}
			}
		}
	}

	public void allPlayersUsedSort(Board board) {
		for (Player player : board.allPlayers) {
			sort(player.usedHands);
		}
	}

	public void allPlayersSort(Board board) {
		for (Player player : board.allPlayers) {
			sort(player.hands);
		}
	}
}