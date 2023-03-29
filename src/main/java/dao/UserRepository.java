package dao;

import dto.User;
import exceptions.UserNotAuthorizedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserRepository {

    private EntityManager entityManager;

    public UserRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public User create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    public User getById(int id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    public User getByToken(String token) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, token);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }





    public User getUserByUsernameAndPassword(String username, String password) throws UserNotAuthorizedException {

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, username);
        entityManager.getTransaction().commit();
        entityManager.close();
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotAuthorizedException("User not found");
    }



    public User getUserWithCriteriaBuilder(String username, String password) {
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);

        User user = entityManager.createQuery(criteriaQuery).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }


    //build a method with criteariabuilder to get  user by username and password


}
