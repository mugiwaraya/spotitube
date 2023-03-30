package dto;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class PlaylistsDTO {
	@JsonbProperty
	private List<Playlist> playlists;
	@JsonbProperty
	private long length;

	public PlaylistsDTO(List<Playlist> playlists, long length) {
		this.playlists = playlists;
		this.length = length;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public long getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}