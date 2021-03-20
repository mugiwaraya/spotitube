package dto;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class PlaylistsDTO {
	@JsonbProperty
	private List<PlaylistDTO> playlists;
	@JsonbProperty
	private int length;

	public PlaylistsDTO(List<PlaylistDTO> playlists, int length) {
		this.playlists = playlists;
		this.length = length;
	}

	public List<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistDTO> playlists) {
		this.playlists = playlists;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}