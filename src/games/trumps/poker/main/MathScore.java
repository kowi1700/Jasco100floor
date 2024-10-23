package games.poker.main;

import java.util.ArrayList;

public class MathScore {
	Execute execute = new Execute();

	public void scoreClearing(Player player, ArrayList<Card> hands) {
		int[] suitMatch = getSuitMatch(player.hands);
		int[] numMatch = getNumMatch(player.hands);

		if (getRoyalFlashCount(player) == 5) {
			player.comboScore = 900;
			return;
		}
		if (getStraightFlashCount(player) == 5) {
			player.comboScore = 800;
			return;
		}

		if (getMaxNum(numMatch) == 4) {
			player.comboScore = 700;
			manyGiftUsed(player, numMatch, 4, 4);
			manyGiftUsed(player, getNumMatch(player.hands), 1, 1);
			return;
		}

		if (searchFullHouse(numMatch)) {
			player.comboScore = 600;
			manyGiftUsed(player, numMatch, 3, 3);
			manyGiftUsed(player, getNumMatch(player.hands), 2, 2);
			return;
		}

		if (getMaxSuit(suitMatch) == 5) {
			player.comboScore = 550;
			manyGiftUsed(player, suitMatch, 5, 5);
			execute.sort(player.usedHands);
			return;
		}

		if (getRoyalCount(player) == 5) {
			player.comboScore = 510;
			return;
		}
		if (getStraightCount(player) == 5) {
			player.comboScore = 500;
			return;
		}
		player.comboScore = belowThreeAndUsed(numMatch, player);// 450,300,150
	}

	public int belowThreeAndUsed(int[] numMatch, Player player) {
		int matchCombo = 0;
		for (int i = 0; i < numMatch.length; i++) {
			switch (numMatch[i]) {
			case 3:
				matchCombo += 151;
				break;
			case 2:
				matchCombo += 75;
				break;
			case 1:
				matchCombo += 0;
				break;
			}

			if (matchCombo == 453) { // 3*3+2*6とかなる前に切り上げ
				break;
			}
			if (matchCombo == 300) { // 2*6とかなる前に切り上げ
				break;
			}
		}

		switch (matchCombo) {
		case 453:
			manyGiftUsed(player, numMatch, 3, 3);
			manyGiftUsed(player, getNumMatch(player.hands), 1, 2);
			return 450;

		case 300:
			manyGiftUsed(player, numMatch, 2, 4);
			execute.sort(player.usedHands);
			manyGiftUsed(player, getNumMatch(player.hands), 1, 1);
			return 300;

		case 150:
			manyGiftUsed(player, numMatch, 2, 2);
			manyGiftUsed(player, getNumMatch(player.hands), 1, 3);
			return 150;

		case 0:
			manyGiftUsed(player, numMatch, 1, 5);
			System.out.println(player.usedHands);
			return 0;

		default:
			return 0;

		}
	}

	public int getRoyalFlashCount(Player player) {
		int royalCount = 0;
		int needNum[] = { 13, 12, 11, 10, 1 };
		int needSuit;
		ArrayList<Integer> usedIndex = new ArrayList<Integer>();

		for (int k = 0; k < player.hands.size(); k++) {
			if (needNum[0] == player.hands.get(k).num) {
				needSuit = player.hands.get(k).suit;
				usedIndex.clear();
				usedIndex.add(k);
				for (int i = 1; i < needNum.length; i++) {
					for (int j = 0; j < player.hands.size(); j++) {
						if (needNum[i] == player.hands.get(j).num && needSuit == player.hands.get(j).suit) {
							royalCount++;
							usedIndex.add(j);
							if (royalCount == needNum.length) {
								for (int index : usedIndex) {
									player.giftUsed(index); // 成立したらユーズドに渡す
								}
								return royalCount;
							}
							break;
						}
					}
				}
			}
		}
		return royalCount;
	}

	public int getRoyalCount(Player player) {
		int royalCount = 0;
		int needNum[] = { 13, 12, 11, 10, 1 };
		ArrayList<Integer> usedIndex = new ArrayList<Integer>();

		for (int i = 0; i < needNum.length; i++) {
			usedIndex.clear();
			usedIndex.add(i);
			for (int j = 0; j < player.hands.size(); j++) {
				if (needNum[i] == player.hands.get(j).num) {
					royalCount++;
					usedIndex.add(j);
					if (royalCount == needNum.length) {
						for (int k = usedIndex.size() - 1; k >= 0; k--) {
							player.giftUsed(usedIndex.get(k)); // 成立したらユーズドに渡す
						}
						return royalCount;
					}
					break;
				}
			}
		}
		return royalCount;
	}

	public int[] getSuitMatch(Player player) {
		int[] suitMatch = new int[player.hands.size()];
		for (int i = 0; i < player.hands.size(); i++) {
			for (int j = 0; j < player.hands.size(); j++) {
				if (player.hands.get(i).suit == player.hands.get(j).suit) {
					suitMatch[i]++;
					if (suitMatch[i] == 5) {
						player.giftUsed(i);
					}
				}
			}
		}
		return suitMatch;
	}

	public int[] getSuitMatch(ArrayList<Card> hands) {
		int[] suitMatch = new int[hands.size()];
		for (int i = 0; i < hands.size(); i++) {
			for (int j = 0; j < hands.size(); j++) {
				if (hands.get(i).suit == hands.get(j).suit) {
					suitMatch[i]++;
				}
			}
		}
		return suitMatch;
	}

	public int[] getNumMatch(ArrayList<Card> hands) {
		int[] numMatch = new int[hands.size()];
		for (int i = 0; i < hands.size(); i++) {
			for (int j = 0; j < hands.size(); j++) {
				if (hands.get(i).num == hands.get(j).num) {
					numMatch[i]++;
				}
			}
		}
		return numMatch;
	}

	public int getMaxSuit(int[] suitMatch) {
		int max = 0;
		for (int i = 0; i < suitMatch.length; i++) {
			if (max < suitMatch[i]) {
				max = suitMatch[i];
			}
		}
		return max;
	}

	public int getMaxNum(int[] numMatch) {
		int max = 0;
		for (int i = 0; i < numMatch.length; i++) {
			if (max < numMatch[i]) {
				max = numMatch[i];
			}
		}
		return max;
	}

	public ArrayList<Integer> getMatchIndex(int[] matchs, int need, int needCount) {
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < matchs.length; i++) {
			if (matchs[i] == need) {
				indexs.add(i);
				count++;
				if (count == needCount) {
					return indexs;
				}
			}
		}
		return indexs;
	}

	public boolean searchFullHouse(int[] numMatch) {
		boolean three = false;
		boolean two = false;
		for (int i = 0; i < numMatch.length; i++) {
			if (numMatch[i] == 3) {
				three = true;
			}
			if (numMatch[i] == 2) {
				two = true;
			}
		}
		if (three && two) {
			return true;
		}
		return false;
	}

	public int getStraightFlashCount(Player player) {
		int straightFlashCount = 0;
		int max = 0;
		ArrayList<Integer> usedIndex = new ArrayList<Integer>();

		for (int i = 0; i < player.hands.size() - 4; i++) {
			straightFlashCount = 1;
			usedIndex.clear();
			usedIndex.add(i);

			for (int j = i + 1; j < player.hands.size(); j++) {
				if (player.hands.get(j - 1).num == player.hands.get(j).num + 1
						&& player.hands.get(j - 1).suit == player.hands.get(j).suit) {
					usedIndex.add(j);
					straightFlashCount++;

					if (straightFlashCount == 5) {
						for (int k = usedIndex.size() - 1; k >= 0; k--) {
							player.giftUsed(usedIndex.get(k)); // 成立したらユーズドに渡す
						}
						player.numScore = i;
						execute.sort(player.usedHands);
						return straightFlashCount;
					}
					if (max < straightFlashCount) {
						max = straightFlashCount;
					}

				} else if (player.hands.get(j - 1).num > player.hands.get(j).num + 1) {
					break;
				}
			}
		}
		return max;
	}

	public int getStraightCount(Player player) {
		int straightCount = 0;
		int max = 0;
		ArrayList<Integer> usedIndex = new ArrayList<Integer>();

		for (int i = 0; i < player.hands.size() - 4; i++) {
			straightCount = 1;
			usedIndex.clear();
			usedIndex.add(i);

			for (int j = i + 1; j < player.hands.size(); j++) {
				if (player.hands.get(j - 1).num == player.hands.get(j).num + 1) {
					usedIndex.add((j));
					straightCount++;

					if (straightCount == 5) {
						for (int k = usedIndex.size() - 1; k >= 0; k--) {
							player.giftUsed(usedIndex.get(k)); // 成立したらユーズドに渡す
						}
						player.numScore = i;
						execute.sort(player.usedHands);
						return straightCount;
					}
					if (max < straightCount) {
						max = straightCount;
					}

				} else if (player.hands.get(j - 1).num > player.hands.get(j).num + 1) {
					break;
				}
			}
		}
		return max;
	}

	public void manyGiftUsed(Player player, int[] numMatch, int needMatch, int needCount) {
		for (int i = getMatchIndex(numMatch, needMatch, needCount).size() - 1; i >= 0; i--) {
			int index = getMatchIndex(numMatch, needMatch, needCount).get(i);
			player.giftUsed(index);
		}
	}
}