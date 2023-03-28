package dto;

import javax.persistence.*;
import java.util.List;

public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "owner", nullable = false, length = 255)
    private boolean owner;
	@OneToMany(targetEntity = Track.class, cascade = CascadeType.ALL)
    private List<Track> tracks;

    public Playlist(int id, String name, boolean owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public Playlist() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
