package dao;

import domain.Track;
import interfaces.ITrack;

import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITrack {

    @Override
    public boolean exists(int trackId) {
        return false;
    }

    @Override
    public List<Track> getTracksInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();
        String query = "SELECT t.*, tp.offlineAvailable FROM tracks t INNER JOIN tracksinplaylist tp ON t.id = tip.trackId WHERE tip.playlistId = " + playlistId;
        return null;
    }

    @Override
    public List<Track> getTracksNotInPlaylist(int playlistId) {
        return null;
    }

    @Override
    public void addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable) {

    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {

    }

}
