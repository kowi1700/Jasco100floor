package games;

public class JascoQuiz{
	public static void main(String[] args) {
		
		while(true) {
			//クイズの生成
			char[] answer = makeQuiz();
			//正解の判定
			boolean isCollect = judgeAnswer(answer);
			
			if(isCollect) {
				System.out.println("正解！");
				break;
			}else {
				System.out.println("ぶーっ");
			}
		}
		
	}
	
	//クイズの生成
	public static char[] makeQuiz() {
		char[] jasco = {'J', 'A', 'S', 'C', 'O'};
		boolean[] answerMask = new boolean[5];
		char[] chrAnswer = new char[5]; //ここがバグの原因
		int random = new java.util.Random().nextInt(3) + 1; //1~3
		
		System.out.println("クイズ: ");
		for(int i = 0; i < random; i++) {
			int randomIndex = new java.util.Random().nextInt(5);
			answerMask[randomIndex] = true;
			System.out.print(jasco[randomIndex]);
		}
		System.out.println();
		
		for(int i = 0; i < answerMask.length; i++) {
			if(!answerMask[i]) {
				chrAnswer[i] = jasco[i];
			}else {
				chrAnswer[i] = 0;
			}
		}
		
		return chrAnswer;
	}
	
	//正解の判定
	public static boolean judgeAnswer(char[] answer) {
		int count = 0;
		int correct = 0;
		char[] userInputArray = new char[5];
		
		System.out.println();
		System.out.println("足りないのは？");
		String input = new java.util.Scanner(System.in).nextLine();
		
		//inputが長すぎる場合のキャッチを書く
		
		//正解数を取得
		for(int i = 0; i < answer.length; i++) {
			if(answer[i] != 0) {
				correct++;
			}
		}
		//正解を調べる
		for(int i = 0; i < input.length(); i++) {
			userInputArray[i] = input.charAt(i);
			for(int j = 0; j < answer.length; j++) {
				if(userInputArray[i] == answer[j]) {
					count++;
					answer[j] = 0; //重複を避けるため
				}
			}
		}
		
		return correct == count;
	}
}
