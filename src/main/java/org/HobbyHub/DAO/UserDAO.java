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
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    public User getUser(int id){
        try(var em = emf.createEntityManager()){
            return em.find(User.class, id);
        }
    }

    public void deleteUser(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(em.find(User.class, id));
            em.getTransaction().commit();
        }
    }

    public void deleteUser(User user){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }



}
