package dao;

import dto.Playlist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PlaylistRepository {

    private EntityManager entityManager;

    public PlaylistRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Playlist createPlaylist(Playlist playlist) {
        entityManager.getTransaction().begin();
        entityManager.persist(playlist);
        entityManager.getTransaction().commit();
        entityManager.close();
        return playlist;
    }
}
