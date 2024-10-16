package tools;

public class ErrorChecker {
	
	//桁数をチェックするメソッド
	public static boolean digitErrorCheck(int digit, int target) {
		//targetの桁数がdigitを超過していたらtrueで返します。
		int count = 0;
		while (target != 0) {
			target /= 10;
			count++;
			if (count > digit)
				return true;	//trueでループ継続のほうが書きやすかったのでエラーはtrueで返してみてます。
		}
		return false;
	}
	
	//maxとminの間にある要素を配列に入れて返すメソッド。
	public static int[] getBetweenNum(int max, int min) {
		int index = 0;
		int[] nums = new int[index];
		if(max > min) {
			index = max - min - 1;
			int[] newNums = new int[index];
			nums = newNums;
			for (int i = 0; i < nums.length; i++) {
				nums[i] = max - i - 1;
			}
		}else {
//			System.out.println("エラー: 無効な引数(max, min)");	//もらった配列をnumsErrorCheckに入れて使ってたので一旦offにします！
		}
		for(int value :nums) {
			System.out.println(value); //確認用
		}
		return nums;
	}
	
	//targetがnum[]のどれかだったらtrueで返すメソッド
	public static boolean numsErrorCheck(int[] num, int target) {
		for (int i = 0; i < num.length; i++) {
			if (target == num[i]) {
				return true;
			}
		}
		return false;
	}
	
	//targetがnumだったらtrueで返すメソッド
	public static boolean numErrorCheck(int num, int target) {
			if (target == num) {
				return true;
			}
		return false;
	}
	
	
}
