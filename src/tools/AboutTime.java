package tools;

public class AboutTime {
	
	//setTime秒になると知らせるタイマー
	public static void timer(int setTime) {
		int milliTime = setTime * 1000; 
		
		System.out.println(setTime + "秒を計測します");
		System.out.println("開始するにはキーを押してください");
		new java.util.Scanner(System.in).nextLine();
		
		final long START_TIME = System.currentTimeMillis();
		
		System.out.println("START!");
		
		while(true) {
			long nowTime = System.currentTimeMillis();
			long elapsedTime = nowTime - START_TIME;
			
			if(elapsedTime >= milliTime) {
				System.out.println(setTime + "秒が経ちました");
				break;
			}
		}
		System.out.println("END");
	}
	
}
