package seongmo.problem2017;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
//셔틀버스 문제
public class Bus {
	public static String solution(int n, int t, int m, String[] timetable) {
		Map<String, Integer> time_map = new HashMap<>();
		PriorityQueue<String> time_priority = new PriorityQueue<>();

		for (String time : timetable) {
			if (!time_map.containsKey(time)) {
				time_map.put(time, 1);
				time_priority.offer(time);
			} else {
				time_map.put(time, time_map.get(time) + 1);
			}
		}
		//일단 n-1대에 대해서만 다 보낸다.
		for (int i = 0; i < n - 1; i++) {
			int limit = m; // 탑승 가능 인원
			while (!time_priority.isEmpty() && limit > 0) { //아직 대기자가 있고 인원 제한보다 덜 탔으면
				String time = time_priority.poll();
				if(time.compareTo(makeTime( 9 + (i * t) / 60, (i * t) % 60)) > 0) { 
                    time_priority.offer(time); //큐 맨 앞에 사람이 가장 빠른 시간인데 그게 버스시간보다 늦으면 다 못타니까 큐에 다시 넣고 다음버스로
                    break;
                }
					
				int people = time_map.get(time);

				if (people <= limit) { //현재 시간 인원이 남은 탑승 가능 인원 이하면 그 인원만큼 빼줌
					limit -= people;
				} else { // 아니면 그 시간데 인원 중 태울수 있는 만큼만 태우고 탑승가능인원을 0으로함
					time_map.put(time, people - limit);
					limit = 0;
					time_priority.offer(time);
				}
			}
		}

		// 여기까지 오면 버스 1대 남았다.
		int last_hour = 9 + ((n-1) * t) / 60;
		int last_minute = ((n-1) * t) % 60;
		String answer = makeTime(last_hour, last_minute);
		while (!time_priority.isEmpty()) { //이제 큐에서 빼면서 탑승 제한보다 더 빼지게 되면 그 시간보다 1분 앞 시간을 리턴
			String time = time_priority.poll();
			if(time.compareTo(answer) > 0) //마지막 버스시간보다 뒷시간이 나오면 중지
				break;
			m -= time_map.get(time);
			if (m <= 0) {
				return getTime(time);
			}
		}
		
		return answer; //위에서 리턴안되고 여기까지 오면 탑승인원이 남았다는 뜻으로 마지막 버스시간에 타면됨.
	}

	public static String getTime(String time) {
		String[] hour_minute = time.split(":");
		int hour = Integer.parseInt(hour_minute[0]);
		int minute = Integer.parseInt(hour_minute[1]);
		if (minute == 0) {
			hour -= 1;
			minute = 59;
		} else {
			minute -= 1;
		}
		return makeTime(hour, minute);
	}
	public static String makeTime(int hour, int minute) {
		StringBuilder sb = new StringBuilder();
		if (hour < 10) {
			sb.append("0");
		}
		sb.append(hour).append(":");
		if (minute < 10) {
			sb.append("0");
		}
		sb.append(minute);

		return sb.toString();
	}
	public static void main(String[] args) {
		String[] time = {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
		System.out.println(solution(10, 60, 45, time));
	}
}

