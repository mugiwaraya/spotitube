package dto;

import javax.persistence.*;

@Entity
@Table(name="tracks")
public class Track {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	@Column(name = "performer", nullable = false, length = 255)
	private String performer;
	@Column(name = "duration", nullable = false, length = 255)
	private long duration;
	@Column(name = "album", nullable = false, length = 255)
	private String album;
	@Column(name = "playcount", nullable = false, length = 255)
	private int playcount;
	@Column(name = "publicationDate", nullable = false, length = 255)
	private String publicationDate;
	@Column(name = "description", nullable = false, length = 255)
	private String description;
	@Column(name = "offlineAvailable", nullable = false, length = 255)
	private boolean offlineAvailable;

	public Track(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable) {
		this.id = id;
		this.title = title;
		this.performer = performer;
		this.duration = duration;
		this.album = album;
		this.playcount = playcount;
		this.publicationDate = publicationDate;
		this.description = description;
		this.offlineAvailable = offlineAvailable;
	}

	//	public Track(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, boolean offlineAvailable) {
//		this.id = id;
//		this.title = title;
//		this.performer = performer;
//		this.duration = duration;
//		this.album = album;
//		this.playcount = playcount;
//		this.publicationDate = publicationDate;
//		this.description = description;
//		this.offlineAvailable = offlineAvailable;
//	}
//
//	public Track() {
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getPlaycount() {
		return playcount;
	}

	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOfflineAvailable() {
		return offlineAvailable;
	}

	public void setOfflineAvailable(boolean offlineAvailable) {
		this.offlineAvailable = offlineAvailable;
	}
}
