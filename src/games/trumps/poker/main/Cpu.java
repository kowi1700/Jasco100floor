package games.poker.main;

public class Cpu extends Player {

	public Cpu(Option option, int index) {
		super(option, index);
		this.name = "cpu" + (option.getMaxPlayer() + index + 1);

	}
	
	@Override
	public void rerollinput(Player player) {
		input = cpuRerollinput(player);
		if(input == 0) {
			System.out.print(player.name+"はholdします。 please enter＞");
		}else {
			System.out.print(player.name+"はrerollします。 please enter＞");
		}
		
		new java.util.Scanner(System.in).nextLine();
	}
	
	public int cpuRerollinput(Player player) {
		return 0;
	}
}
