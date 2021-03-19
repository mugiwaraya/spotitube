package dto.playlist;

import domain.Track;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class GetTracksInPlaylistDTO {
	@JsonbProperty
	private List<Track> tracks;

	public GetTracksInPlaylistDTO(List<Track> trackList) {
		this.tracks = trackList;
	}

	public List<Track> getTrackList() {
		return tracks;
	}

	public void setTrackList(List<Track> trackList) {
		this.tracks = trackList;
	}
}
