package games.poker.main;

import java.util.ArrayList;

public class MathWinners {
	ArrayList<Player> winners = new ArrayList<Player>();
	boolean winnerIsMany = true;

	public MathWinners(Board board) {
		for (Player player : board.allPlayers) {
			winners.add(player);
		}
	}

	public void searchWinner(MathScore mathScore, UI ui) {
		int tiedCount = 0;
		while (winnerIsMany) {
			for (Player player : winners) {
				mathScore.scoreClearing(player, player.hands);
			}
			ui.allWinnersOpen(winners, tiedCount);
			winnerIsMany = mathComboWinner();
			if (winnerIsMany = false) {
				return;
			}
			winnerIsMany = mathNumWinner();
			tiedCount++;
			for (int i = 0; i < winners.size(); i++) {
				winners.get(i).allUsedToTied();
			}
		}
	}

	public boolean mathComboWinner() {
		int maxComboScore = 0;
		ArrayList<Player> newWinners = new ArrayList<Player>();
		for (int i = 0; i < winners.size(); i++) {
			if (maxComboScore < winners.get(i).comboScore) {
				maxComboScore = winners.get(i).comboScore; // 最大スコア保存
				newWinners.clear(); // 最大スコアが更新されたら旧同点1位はリセット
				newWinners.add(winners.get(i)); // 最大スコア更新で、1位のプレイヤーも更新
			} else if (maxComboScore == winners.get(i).comboScore) { // 最大スコアと同点だったら
				newWinners.add(winners.get(i)); // 同点1位のプレイヤーとして保存
			}
		}
		winners.clear();
		for (Player newWinner : newWinners) {
			winners.add(newWinner);
		}
		if (winners.size() == 1) {
			return false;
		}
		return true;
	}

	public boolean mathNumWinner() {
		int maxNumScore = 0;
		ArrayList<Player> newWinners = new ArrayList<Player>();
		for (int j = 0; j < winners.get(0).usedHands.size(); j++) {
			for (int i = 0; i < winners.size(); i++) {
				if (maxNumScore < winners.get(i).usedHands.get(j).num) {
					maxNumScore = winners.get(i).numScore; // 最大スコア保存
					newWinners.clear(); // 最大スコアが更新されたら旧同点1位はリセット
					newWinners.add(winners.get(i)); // 最大スコア更新で、1位のプレイヤーも更新
				} else if (maxNumScore == winners.get(i).numScore) { // 最大スコアと同点だったら
					newWinners.add(winners.get(i)); // 同点1位のプレイヤーとして保存
				}

			}
			winners.clear();
			for (Player newWinner : newWinners) {
				winners.add(newWinner);
			}
			if (winners.size() == 1) {
				return false;
			}
		}
		return true;
	}
}
