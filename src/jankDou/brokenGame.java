package jankDou;

public class brokenGame {
	public static int brokenDisplay(int coin) {
		boolean isError = false;
		
		System.out.println("故障 してるみたい");
		System.out.println("");
		System.out.println("");
		System.out.print("______________");
		isError = printError();
		System.out.println("_____________");
		System.out.println("");
		System.out.println("");
		
		if(isError) {
			coin += 100;
			System.out.println("画面のしたに100coin おいてある...!!");
		}
		return coin;
	}
	
	public static boolean printError() {
		String chrs = "e";
		for(int i = 0; i < 4; i++) {
			int randNum = new java.util.Random().nextInt(2);
			switch(randNum) {
			case 0 : chrs += "r";
			break;
			case 1 : chrs += "o";
			break;
			}
		}
		System.out.print(chrs);
		
		if(chrs.equals("error")) return true;
		return false;
	}
}
