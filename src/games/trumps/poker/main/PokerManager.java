package games.poker.main;

import java.util.ArrayList;

public class PokerManager {
	Option option;
	Board board;
	Execute execute;
	UI ui;
	MathScore mathScore;
	MathWinners mathWinners;
	User user;
	int bet;

	public PokerManager(Option option) {
		this.option = option;
		board = new Board(option);
		execute = new Execute();
		ui = new UI();
		mathScore = new MathScore();
		mathWinners = new MathWinners(board);
		user = new User();
	}

	public void runPoker() {
		ui.start(board);
		bet = ui.openingBet(user);
		board.deck.shuffle();
		execute.OpeningDraw(board, option);
		while (execute.allHold(board.allPlayers) == false) {
			for (int i = 0; i < board.allPlayers.length; i++) {
				if (board.allPlayers[i].hold) {
					continue;
				}
				ui.turnOpening(board.allPlayers[i]);
				ui.handsDisplay(board.allPlayers[i]);
				board.allPlayers[i].rerollinput(board.allPlayers[i]);
				if (board.allPlayers[i].input == 0) {
					board.allPlayers[i].hold = true;
					System.out.println();
					continue;
				}
				ArrayList<Integer> indexs = execute.getIndexFromDigit(board.allPlayers[i].input);
				board.allPlayers[i].turnReroll(board, indexs);
				ui.afterDisplay(board.allPlayers[i], indexs);
				System.out.println();
			}
		}
		ui.allTurnEnd();
		execute.allPlayersSort(board);
		mathWinners.searchWinner(mathScore, ui);
		ui.winnerDisplay(mathWinners.winners);

	}

	// public boolean checkWinner(ArrayList<Player> winners) {
	// mathScore.mathComboWinner(winners);
	// if (winners.size() == 1) {
	// ui.winnerDisplay(winners);
	// return false;
	// }
	// mathScore.mathNumWinner(winners);
	// if (winners.size() == 1) {
	// ui.winnerDisplay(winners);
	// return false;
	// }
	//
	// execute.handsOutIndex(winners);
	// if (winners.get(0).hands.length == 0) {
	// ui.tiedDisplay(winners);
	// winners.clear();
	// return false;
	// }
	//
	// for (Player tiedWinners : winners) {
	// mathScore.scoreClearing(tiedWinners, tiedWinners.hands);
	// }
	// return true;
	//
	// }

	// mathScore.winnerJudge();
	// do {
	// mathScore.getComboWinner(board.allPlayers);
	// }while()
	// winnerJudge();
	// result();
	// retrySelect();

	// board.debug();

}
