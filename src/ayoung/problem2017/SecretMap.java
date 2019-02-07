package ayoung.problem2017;

public class SecretMap {


	public static int[][] change01(int n, int[] arr1) {
		int[][] arr3 = new int[n][n];
		for (int i = 0; i < arr1.length; i++) {
			int index = arr3.length - 1;
			while (arr1[i] >= 1) {
				arr3[i][index--] = arr1[i] % 2;
				arr1[i] = arr1[i] / 2;
			}
		}
		return arr3;
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		int[][] arr3;
		arr3 = change01(n, arr1);
		int[][] arr4;
		arr4 = change01(n, arr2);
		for(int i=0;i<arr3.length;i++) {
			answer[i]="";
			for(int j=0;j<arr3.length;j++) {
				if(arr3[i][j] == 0 && arr4[i][j] == 0)
					answer[i]+=" ";
				else answer[i] +="#";
			}
		}
		return answer;
	}

}
