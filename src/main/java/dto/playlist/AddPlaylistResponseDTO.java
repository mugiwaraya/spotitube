package dto.playlist;

import com.sun.org.apache.xpath.internal.objects.XString;
import domain.Playlist;
import domain.Track;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class AddPlaylistResponseDTO {
	@JsonbProperty
	private List<Playlist> playlists;
	@JsonbProperty
	private int length;

	public AddPlaylistResponseDTO(List<Playlist> playlists, int length) {
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
