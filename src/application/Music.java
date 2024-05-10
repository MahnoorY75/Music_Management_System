package application;

public class Music {
	private String Musicsrc;
	
	public String getMusicsrc() {
		return Musicsrc;
	}
	public void setMusicsrc(String musicsrc) {
		Musicsrc = musicsrc;
	}
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	private int id;
	private String genre;
	private String duration;


}
