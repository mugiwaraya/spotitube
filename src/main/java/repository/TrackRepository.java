package repository;

import dto.Playlist;
import dto.Track;
import dto.Tracks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            Track track = this.entityManager.find(Track.class, trackId);
            playlist.addTrack(track);
            this.entityManager.persist(playlist);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.info("Error adding track to playlist");
        }
    }

    public Tracks getAllTracksNotInPlaylists(int playlistId) {
        try {
            Playlist playlist = this.entityManager.find(Playlist.class, playlistId);
            List<Track> tracks = this.entityManager.createQuery("SELECT t FROM Track t WHERE t.id not in (SELECT t.id FROM Track t JOIN t.playlists p WHERE p.id = :playlistId)", Track.class).setParameter("playlistId", playlistId).getResultList();
            Tracks trackList = new Tracks(tracks);

            return trackList;
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
}
