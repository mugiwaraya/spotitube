package repository;

import dto.Playlist;
import dto.User;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;

import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.NotAuthorizedException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository {
    private final static Logger LOGGER = Logger.getLogger(PlaylistRepository.class.getName());
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    private int userCounter = 0;

    public UserRepository() {
        this.emf = Persistence.createEntityManagerFactory("SpotitubePU");
        this.entityManager = this.emf.createEntityManager();
    }

    public User addUser(User user) {
        if (userCounter < 1) {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(user);
            this.entityManager.getTransaction().commit();
            close();
            userCounter += 1;
            return user;
        }
        return null;
    }

    public User loginUser(String username, String password) throws NotAuthorizedException {
//        addUser(new User("test", "test", "test", "test", "test", Collections.emptyList()));
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u where u.username LIKE '%" + username + "%'");
            User user = (User) query.getSingleResult();
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            LOGGER.warning("User not authorized");
            throw new NotAuthorizedException("User not authorized");
        } catch (NoResultException e) {
            LOGGER.warning("User not authorized");
            throw new NotAuthorizedException("User not authorized");
        }
    }


    public User getUserByToken(String token) throws UserNotFoundByTokenException {
        Query query = entityManager.createQuery("SELECT u FROM User u where u.token LIKE '%" + token + "%'");
        try {
            User foundUser = (User) query.getSingleResult();
            return foundUser;
        } catch (NoResultException e) {
            throw new UserNotFoundByTokenException();
        }
    }

    public User insertToken(User user, String token) {
        this.entityManager.getTransaction().begin();
        User userToUpdate = (User) this.entityManager.find(User.class, user.getId());
        userToUpdate.setToken(token);
        this.entityManager.getTransaction().commit();
        close();
        return userToUpdate;
    }

//    public List<Playlist> getPlaylistsByUser(User user) {
//        try {
//            this.entityManager.getTransaction().begin();
//            List<Playlist> playlists = user.getPlaylists();
//            this.entityManager.getTransaction().commit();
//            this.entityManager.clear();
//            return playlists;
//        } catch (Exception e) {
//            LOGGER.info("Error getting playlists");
//            return null;
//        }
//    }


    public void close() {
        this.entityManager.close();
    }


}
