package dto;

import java.util.List;

public class AddPlayListDTO {
	private int id;
	private String name;
	private User owner;
	private List<Track> tracks;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public AddPlayListDTO(int id, String name, User owner, List<Track> tracks) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.tracks = tracks;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
