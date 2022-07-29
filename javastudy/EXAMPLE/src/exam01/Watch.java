package exam01;

public class Watch {
	
	private int hour;
	private int minute;
	static int second;
	
	Watch (int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int addHour(int addHour) {
		hour += addHour;
		return hour;
	}
	
	public int addMinute(int addMinute) {
		minute += addMinute;
		return minute;
	}
	
	public int addSecond(int addSecond) {
		second += addSecond;
		return second;
	}
	
	public void see() {
		int s = second % 60;
		int m = (minute + (second / 60)) % 60;
		int h = hour + ((minute + (second / 60)) / 60) - 24;
		System.out.println(h + "시 " + m + "분 " + s + "초");
	}
}
