package dto.playlist;

import domain.Playlist;

import javax.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;

public class GetAllPlaylistsResponseDTO {
    @JsonbProperty("playlists")
    private ArrayList<Playlist> playlists;
    @JsonbProperty("length")
    private double length;


    public GetAllPlaylistsResponseDTO(ArrayList<Playlist> playlists, double length) {
        this.playlists = playlists;
        this.length = length;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public double getLength() {
        return length;
    }


}
