package domain;

import javax.sound.midi.Track;
import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private int ownerId;
    private ArrayList<Track> tracks;

    public Playlist(int id, String name, int ownerId) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }
}
