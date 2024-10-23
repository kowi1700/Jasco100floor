package games.poker.main;

public class Board {
	Option option;
	Deck deck;
	Grave grave;
	Player[] players;
	Cpu[] cpus;
	Player[] allPlayers;
	int totalPlayers;
	Execute execute = new Execute();

	public Board(Option option) {
		this.option = option;
		deck = new Deck();
		grave = new Grave();
		players = new Player[option.getMaxPlayer()];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(option,i);
		}
		
		cpus = new Cpu[option.getMaxDealer()];
		for (int i = 0; i < cpus.length; i++) {
			cpus[i] = new Cpu(option,i);
		}
		allPlayers =new Player[players.length+cpus.length];
		for(int i = 0 ; i < players.length;i++) {
			allPlayers[i]= players[i];
		}
		for(int i = 0 ; i < cpus.length;i++) {
			allPlayers[players.length+i]= cpus[i];
		}
		
		
	}

	public void debug() {
		for (int i = 0; i < option.getMaxPlayer(); i++) {
			for (int j = 0; j < option.getMaxHands(); j++) {
				System.out.print(players[i].hands.get(j)+" ");
			}
			System.out.println();
		}
		for (int i = 0; i < option.getMaxPlayer(); i++) {
			for (int j = 0; j < option.getMaxHands(); j++) {
				System.out.print(cpus[i].hands.get(j)+" ");
			}
			System.out.println();
		}
		
	}
}
