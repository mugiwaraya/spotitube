package repository;

import dto.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class PlaylistRepository {
    private final static Logger LOGGER = Logger.getLogger(PlaylistRepository.class.getName());
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public PlaylistRepository() {
        this.emf = Persistence.createEntityManagerFactory("SpotitubePU");
        this.entityManager = this.emf.createEntityManager();
    }

    public AddPlayListDTO addPlaylist(AddPlayListDTO playlist) {

        try {
            User usr = this.entityManager.find(User.class, playlist.getOwner().getId());
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(new Playlist(playlist.getName(), 0, Collections.<Track>emptyList(), usr));
            this.entityManager.getTransaction().commit();
            return playlist;

        } catch (Exception e) {
            LOGGER.info("Error adding playlist");
            return null;
        }
    }

    public PlaylistsDTO getAllPlaylists(User user) {

        try {
            List<Playlist> playlistsOfUser = this.entityManager.createQuery("SELECT p FROM Playlist p WHERE p.user = :user", Playlist.class).setParameter("user", user).getResultList();
            long totalLength = 0;
            for (Playlist pl : playlistsOfUser) {
                totalLength += pl.getLength();
            }
            return new PlaylistsDTO(playlistsOfUser, totalLength);
        } catch (Exception e) {
            LOGGER.info("Error getting playlists");
            return null;
        }

    }


    public boolean deletePlaylist(int playlistId) {
        return false;
    }




//    public void close() {
//        this.entityManager.close();
//        this.emf.close();
//    }


    public Playlist editPlaylist(Playlist playlist, int playlistId) {
        return null;
    }
}
