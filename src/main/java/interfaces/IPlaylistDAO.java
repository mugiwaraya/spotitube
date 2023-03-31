package interfaces;

import dto.AddPlayListDTO;
import dto.Playlist;
import dto.PlaylistsDTO;
import dto.User;

public interface IPlaylistDAO {
	PlaylistsDTO getAllPlaylists(User user);

	PlaylistsDTO getAllPlaylistsForUser(User user);

	PlaylistsDTO getAllPlaylists();

	boolean deletePlaylist(User user, int playlistId);

	AddPlayListDTO addPlaylist(AddPlayListDTO playlist);

	Playlist editPlaylist(Playlist playlist, int playlistId);
}
