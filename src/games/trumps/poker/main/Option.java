package games.poker.main;

public class Option {
	int maxPlayer = 2;
	int maxCpu = 0;
	int maxReroll =5;
	int maxHands=8;

	public int getMaxPlayer() {
		return maxPlayer;
	}
	
	public int getMaxDealer() {
		return maxCpu;
	}

	public int getMaxReroll() {
		return maxReroll;
	}
	
	public int getMaxHands() {
		return maxHands;
	}
}
