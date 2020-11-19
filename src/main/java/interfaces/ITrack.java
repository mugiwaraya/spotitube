package interfaces;

import domain.Track;

import java.util.List;

public interface ITrack {
    boolean exists(int trackId);
    List<Track> getTracksInPlaylist(int playlistId);
    List<Track> getTracksNotInPlaylist(int playlistId);
    void addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable);
    void deleteTrackFromPlaylist(int playlistId, int trackId);
}
