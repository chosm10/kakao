package ayoung.problem2017;
import java.text.Format;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Time {
	int h, m;

	Time(int h, int m) {
		this.h = h;
		this.m = m;
	}

	@Override
	public String toString() {
		String s ="";
		if(h < 10) {
			s += "0" + h+":";
		} else s+=h+":";
		if(m < 10) {
			s += "0" + m;
		} else s+=m;
		
		return s;
	}

}

public class Bus {

	public static Time[] getLimitTime(int n, int t) {

		Time[] limitTime = new Time[n];
		limitTime[0] = new Time(9, 0);
		for (int i = 1; i < n; i++) {
			int temp_time = t + limitTime[i - 1].m;
			int limith = limitTime[i - 1].h + (temp_time >= 60 ? 1 : 0);
			int limitm = temp_time % 60;
			limitTime[i] = new Time(limith, limitm);
		}
		return limitTime;
	}

	public static boolean timeCompare(Time a, Time b) {
		if (a.h > b.h)
			return true;
		else if (a.h == b.h) {
			if (a.m >= b.m)
				return true;
		}
		return false;
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		int timeLength = timetable.length;
		Time[] timetableChange = new Time[timeLength];
		for (int i = 0; i < timeLength; i++) {
			String[] temp = timetable[i].split(":");
			timetableChange[i] = new Time(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		Arrays.sort(timetableChange, new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				// TODO Auto-generated method stub
				if (o1.h == o2.h) {
					return o1.m - o2.m;
				} else {
					return o1.h - o2.h;
				}
			}
		});

		Time[] limitTime = getLimitTime(n, t);
	
		int peopleIndex = 0;
		int mCount = 0;
		for (int i = 0; i < n - 1;) {
			if (timeCompare(limitTime[i], timetableChange[peopleIndex]) && mCount < m) {
				peopleIndex++;
				mCount++;
			} else {
				i++;
				mCount = 0;
			}
		}
		mCount = 0;
		for (int i = peopleIndex; i < timeLength; i++) {
			if (timeCompare(limitTime[n - 1], timetableChange[i])) {
				mCount++;
				if (mCount == m) {
					timetableChange[i].m--;
					if (timetableChange[i].m < 0) {
						timetableChange[i].h--;
						timetableChange[i].m=59;
					}

					return timetableChange[i].toString();
				}
			}
		}
		return limitTime[n-1].toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		int t = 1;
		int m = 2;
		String[] timetable = {"09:00", "09:00", "09:00", "09:00"};
		
		solution(n, t, m, timetable);
//		Time[] timetableChange = new Time[timetable.length];
//		for (int i = 0; i < timetable.length; i++) {
//			String[] temp = timetable[i].split(":");
//			timetableChange[i] = new Time(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
//		}

	}

}
