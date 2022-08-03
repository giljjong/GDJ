package quiz07_song;

import lombok.ToString;

@ToString
public class Song {
	
	private String title;
	private double playTime;

	public Song(String title, double playTime) {
		super();
		this.title = title;
		this.playTime = playTime;
	}
	

}
