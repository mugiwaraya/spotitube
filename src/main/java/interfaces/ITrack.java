package interfaces;

import dto.TrackDTO;
import dto.TracksDTO;
import exceptions.DeleteException;
import exceptions.InsertionException;
import exceptions.TrackException;

public interface ITrack {

    TracksDTO getTracksInPlaylist(int playlistId) throws TrackException, TrackException;
    TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException;
    void removeTrackFromPlaylist(int playlistId, int trackId) throws DeleteException;
    void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException;
}
