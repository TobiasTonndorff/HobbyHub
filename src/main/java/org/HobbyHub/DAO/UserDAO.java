package org.HobbyHub.DAO;
import jakarta.persistence.*;
import org.HobbyHub.entities.User;
public class UserDAO {

    private static EntityManagerFactory emf;
    private static UserDAO instance;

    public static UserDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserDAO();

        }
        return instance;
    }

    public void saveUser(User user) {

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        try(var em = emf.createEntityManager()){

        }
    }

    public User getUserById(int id){
        try(var em = emf.createEntityManager()){
            User user = em.find(User.class, id);
            return user;
        }
    }



}
