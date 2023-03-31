package dao;

import dto.AddPlayListDTO;
import dto.Playlist;
import dto.PlaylistsDTO;
import dto.User;
import interfaces.IPlaylistDAO;
import repository.PlaylistRepository;

import javax.inject.Inject;

public class PlaylistDAO implements IPlaylistDAO {

    private PlaylistRepository playlistRepository;

    @Inject
    public PlaylistDAO() {
        this.playlistRepository = new PlaylistRepository();
    }


    @Override
    public PlaylistsDTO getAllPlaylists(User user) {
        return playlistRepository.getAllPlaylists(user.getId());
    }

    @Override
    public PlaylistsDTO getAllPlaylistsForUser(User user) {
        return null;
    }

    @Override
    public PlaylistsDTO getAllPlaylists() {
        return null;
    }


    @Override
    public boolean deletePlaylist(User user, int playlistId) {
        return playlistRepository.deletePlaylist(user, playlistId);
    }

    @Override
    public AddPlayListDTO addPlaylist(AddPlayListDTO playlist) {
        playlist = playlistRepository.addPlaylist(playlist);
        return playlist;
    }

    @Override
    public Playlist editPlaylist(Playlist playlist, int playlistId) {
        playlist = playlistRepository.editPlaylist(playlist, playlistId);
        return playlist;
    }
