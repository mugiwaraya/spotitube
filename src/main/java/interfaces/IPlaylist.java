package interfaces;

import domain.Playlist;

import java.util.List;

public interface IPlaylist {
    List<Playlist> getAllPlaylists(int userId);
    Playlist getPlaylist (int playlistId);
    boolean deletePlaylist(int playlistId);
    Playlist addPlaylist (Playlist playlist);
}
