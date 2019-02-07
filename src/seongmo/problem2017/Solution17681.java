package seongmo.problem2017;

//보물찾기 문제
public class Solution17681 {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] board = new String[n];
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp = arr1[i] | arr2[i];
			StringBuilder s = new StringBuilder();
			for (int j = n - 1; j >= 0; j--) {
				if ((tmp & (1 << j)) != 0) {
					s.append("#");
				} else {
					s.append(" ");
				}
			}
			board[i] = s.toString();
		}

		return board;
	}
}
