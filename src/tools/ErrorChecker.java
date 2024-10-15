package tools;

public class ErrorChecker {
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

	public static int[] getBetweenNum(int max, int min) {
		//間にある数を配列に入れて返します。maxは含まれますがminは含まれません。
		int[] num = new int[max - min];
		for (int i = 0; i < num.length; i++) {
			num[i] = max - i;
		}
		for(int value :num)
		System.out.println(value);
		return num;
	}
	
	public static boolean numErrorCheck(int[] num, int target) {
		//targetがnum[]のどれかだったらtrueで返します。
		for (int i = 0; i < num.length; i++) {
			if (target == num[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean numErrorCheck(int num, int target) {
		//targetがnumだったらtrueで返します。
			if (target == num) {
				return true;
			}
		return false;
	}
	
	
}
