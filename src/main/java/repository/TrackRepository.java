package repository;

import dto.Playlist;
import dto.Track;
import dto.Tracks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrackRepository {
    private final static Logger LOGGER = Logger.getLogger(PlaylistRepository.class.getName());
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TrackRepository() {
        this.emf = Persistence.createEntityManagerFactory("SpotitubePU");
        this.entityManager = this.emf.createEntityManager();
    }

    public void addTrackToPlaylist(int playlistId, int trackId) {
        try {
            this.entityManager.getTransaction().begin();
            Playlist playlist = this.entityManager.find(Playlist.class, playlistId);
            this.entityManager.refresh(playlist);
            Track track = this.entityManager.find(Track.class, trackId);
            playlist.addTrack(track);
            playlist.setLength(playlist.getLength() + track.getDuration());
            this.entityManager.persist(playlist);
            this.entityManager.getTransaction().commit();


        } catch (Exception e) {
            LOGGER.info("Error adding track to playlist");
        }
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try {
            this.entityManager.getTransaction().begin();
            Playlist playlist = this.entityManager.find(Playlist.class, playlistId);
            this.entityManager.refresh(playlist);
            Track track = this.entityManager.find(Track.class, trackId);
            playlist.removeTrack(track);
            playlist.setLength(playlist.getLength() - track.getDuration());
            this.entityManager.persist(playlist);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.info("Error deleting track from playlist");
        }
    }

    public Tracks getAllTracksNotInPlaylists(int playlistId) {
        try {

            this.entityManager.getTransaction().begin();
            List<Track> allTracks = (List<Track>) this.entityManager.createQuery("SELECT t FROM Track t").getResultList();
            List<Track> allTracksModifiable = new ArrayList<Track>(allTracks);

            Playlist playlist = this.entityManager.find(Playlist.class, playlistId);
            this.entityManager.refresh(playlist);
            List<Track> tracksInPlaylist = playlist.getTracks();

            allTracksModifiable.removeAll(tracksInPlaylist);

            this.entityManager.getTransaction().commit();


            return new Tracks(allTracksModifiable);
        } catch (Exception e) {
            LOGGER.info("Error getting tracks not in playlist");
            return null;
        }
    }

    public Tracks getTracksInPlaylist(int playlistId) {
        try {
            Playlist playlist = this.entityManager.find(Playlist.class, playlistId);
            Tracks trackList = new Tracks(playlist.getTracks());

            return trackList;
        } catch (Exception e) {
            LOGGER.info("Error getting tracks in playlist");
            return null;
        }

    }

    public void close() {
        this.entityManager.close();
    }


}
