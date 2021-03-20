package interfaces;

import dto.AddPlayListDTO;
import dto.PlaylistDTO;
import dto.PlaylistsDTO;

public interface IPlaylist {
    PlaylistsDTO getAllPlaylists(int userId);
    boolean deletePlaylist(int playlistId);
    AddPlayListDTO addPlaylist (AddPlayListDTO playlist);
    PlaylistDTO editPlaylist(PlaylistDTO playlist, int playlistId);
}
