package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
	private int id;
	private String name;
	private int ownerId;


	private int length;
	private ArrayList<domain.Track> tracks;

	public Playlist(int id, String name, int ownerId) {
		this.id = id;
		this.ownerId = ownerId;
		this.name = name;
		this.length = getLength();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public int getLength() {
		int length = 0;
		if (tracks != null) {
			for (domain.Track track : tracks) {
				length += track.getDuration();
			}
		}
		return length;
	}

	public String getName() {
		return name;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public List<Track> getTracks() {
		if (this.tracks != null) {
			return tracks;
		}
		return Collections.<Track>emptyList();
	}

	public void setTracks(ArrayList<Track> tracks) {
		this.tracks = tracks;
	}

}
