package interfaces;

import dto.Track;
import dto.Tracks;
import exceptions.DeleteException;
import exceptions.InsertionException;
import exceptions.TrackException;

public interface ITrackDAO {

	Tracks getTracksInPlaylist(int playlistId) throws TrackException, TrackException;

	Tracks getAllTracksNotInPlaylist(int playlistId) throws TrackException;

	void removeTrackFromPlaylist(int playlistId, int trackId) throws DeleteException;

	void addTrackToPlaylist(int playlistId, Track dto) throws InsertionException;
}
