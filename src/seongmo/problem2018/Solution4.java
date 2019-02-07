package seongmo.problem2018;
import java.util.Arrays;
import java.util.Comparator;

public class Solution4 {
	public static int solution(int[] food_times, long k) {

		Food[] food_time = new Food[food_times.length];
		for (int i = 0; i < food_times.length; i++) {
			food_time[i] = new Food(i + 1, food_times[i]);
		}
		int end = food_time.length;
		int start = 0; // 시간초가 가장 적게 남은 인덱스로 같은 시간을 가진 음식이 나올때 까지 증가됨
		int startTime = 0; // start중 가장 첫번째 인덱스
		int lastTime = 0; //전 사이클까지 먹는데 소비된 시간(현재 음식의 소비시간에서 이걸 뺀 시간만큼이 그 음식을 먹는데 남은 시간임)
		int eatingTime = 0; // 시간초가 가장 적게 남은 음식을 먹는데 걸리는 시간
		long eatPossible = 0; // 음식을 한번에 먹을수 있는 시간
		Arrays.sort(food_time);
		while (start < end) {
			eatingTime = food_time[start].remain_time;
			startTime = start;
			while (start + 1 < end && food_time[start + 1].remain_time == eatingTime) {
				start++;
			}
			eatPossible = (long) (eatingTime - lastTime) * (long) (end - startTime);
			if (eatPossible <= k) {
				k -= eatPossible;
				lastTime = eatingTime;
				start++;
			} else {
				Arrays.sort(food_time, startTime, end, new Comparator<Food>() {
					@Override
					public int compare(Food o1, Food o2) {
						if (o1.order > o2.order)
							return 1;
						else if (o1.order < o2.order)
							return -1;
						else
							return 0;
					}
				});
				return food_time[startTime + (int) (k % (end - startTime))].order;
			}
		}
		return -1;

//		ArrayList<Food> food_time = new ArrayList<>();
//		for (int i = 0; i < food_times.length; i++) {
//			food_time.add(new Food(i + 1, food_times[i]));
//		}
//		int same = 0;
//		int lastTime = 0;
//		Collections.sort(food_time);
//		while (!food_time.isEmpty()) {
//			same = 0;
//
//			while (same + 1 < food_time.size() && food_time.get(same + 1).remain_time == food_time.get(0).remain_time) {
//				same++;
//			}
//			if ((food_time.get(0).remain_time - lastTime) * food_time.size() <= k) {
//				k -= (food_time.get(0).remain_time - lastTime) * food_time.size();
//				lastTime = food_time.get(0).remain_time;
//				for (int i = 0; i <= same; i++) {
//					food_time.remove(i);
//				}
//			} else {
//				Collections.sort(food_time, new Comparator<Food>() {
//					@Override
//					public int compare(Food o1, Food o2) {
//						if (o1.order > o2.order)
//							return 1;
//						else if (o1.order < o2.order)
//							return -1;
//						else
//							return 0;
//					}
//				});
//				return food_time.get((int)(k % food_time.size())).order;
//			}
//		}
//		return -1;

	}

	public static void main(String[] args) {
		int arr[] = { 3, 1, 2 };
		System.out.println(solution(arr, 5));
	}

	static class Food implements Comparable<Food> {
		int order, remain_time;

		public Food(int order, int remain_time) {
			this.order = order;
			this.remain_time = remain_time;
		}

		@Override
		public int compareTo(Food o) {
			if (this.remain_time > o.remain_time)
				return 1;
			else if (this.remain_time < o.remain_time)
				return -1;
			else {
				return 0;
			}
		}

	}
}