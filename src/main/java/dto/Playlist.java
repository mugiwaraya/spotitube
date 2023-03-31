package dto;

import javax.json.bind.annotation.JsonbProperty;
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
    @Column(name = "length", nullable = false)
    private long length;
    @OneToMany(targetEntity = Track.class)
    private List<Track> tracks;
    @ManyToOne(targetEntity = User.class)
    private User ownerUser;
    @JsonbProperty
    @Transient
    private boolean owner = false;

    public void setLength(long length) {
        this.length = length;
    }

    public Playlist(String name, List<Track> tracks, User user) {
        this.name = name;
        this.tracks = tracks;
        this.ownerUser = user;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public boolean setIsOwner() {
        owner = true;
        return true;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
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

    public void removeTrack(Track track) {
        this.tracks.remove(track);
    }
}