package dto;

import java.util.List;

public class AddPlayListDTO {
	private int id;
	private String name;
	private int ownerId;
	private List<TrackDTO> tracks;

	public AddPlayListDTO(int id, String name, int ownerId, List<TrackDTO> tracks) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.tracks = tracks;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
