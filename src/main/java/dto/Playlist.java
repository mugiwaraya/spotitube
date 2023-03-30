package dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "length", nullable = false, length = 255)
    private int length;
    @OneToMany(targetEntity = Track.class, cascade = CascadeType.ALL)
    private List<Track> tracks;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;

    public Playlist(String name, int length, List<Track> tracks, User user) {
        this.name = name;
        this.length = length;
        this.tracks = tracks;
        this.user = user;
    }


    //	public Playlist(int id, String name, int owner, List<Track> tracks) {
//		this.id = id;
//		this.name = name;
//		this.tracks = tracks;
//	}
//
//	public Playlist() {
//	}


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


    public long getLength() {
        long totalLength = 0;
        for (Track track : this.tracks) {
            totalLength += track.getDuration();
        }
        return totalLength;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }
}