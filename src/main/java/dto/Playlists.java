package dto;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class Playlists {
	@JsonbProperty
	private List<Playlist> playlists;
	@JsonbProperty
	private int length;

	public Playlists(List<Playlist> playlists, int length) {
		this.playlists = playlists;
		this.length = length;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}