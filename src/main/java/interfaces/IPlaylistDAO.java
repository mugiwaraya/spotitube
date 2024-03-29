package interfaces;

import dto.AddPlayListDTO;
import dto.Playlist;
import dto.Playlists;

public interface IPlaylistDAO {
	Playlists getAllPlaylists(int userId);

	boolean deletePlaylist(int playlistId);

	AddPlayListDTO addPlaylist(AddPlayListDTO playlist);

	Playlist editPlaylist(Playlist playlist, int playlistId);
}
