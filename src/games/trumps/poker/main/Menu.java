package games.poker.main;

public class Menu {

	public void opening() {
		System.out.println("---------------------");
		System.out.println("	tramps");
	}

	public void mainMenu() {
		Option option = new Option();

		System.out.println("---------------------");
		System.out.print("1.Poker　＞");
		int input = new java.util.Scanner(System.in).nextInt();
		System.out.println();
		switch (input) {
		case 1:
			PokerManager pokerManager = new PokerManager(option);
			pokerManager.runPoker();
		}
	}
}
