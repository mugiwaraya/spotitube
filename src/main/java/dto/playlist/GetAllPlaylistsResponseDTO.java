package dto.playlist;

import domain.Playlist;

import javax.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;

public class GetAllPlaylistsResponseDTO {
    @JsonbProperty("playlists")
    private List<Playlist> playlists;
    @JsonbProperty("length")
    private double length;


    public GetAllPlaylistsResponseDTO(List<Playlist> playlists) {
        this.playlists = playlists;
        this.length = getLength();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        int length = 0;
        for(Playlist playlist : playlists)
        {
            length += playlist.getLength();
        }
        return length;
    }


}
