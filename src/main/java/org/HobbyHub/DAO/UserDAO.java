package org.HobbyHub.DAO;
import jakarta.persistence.*;
import org.HobbyHub.dto.UserDTO;
import org.HobbyHub.dto.UserDataDTO;
import org.HobbyHub.entities.User;

import java.util.List;

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

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public User getUserById(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find(User.class, id);
        }
    }

    public void deleteUser(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.find(User.class, id));
            em.getTransaction().commit();
        }
    }

    public void deleteUser(User user) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }

    public User createUser(User user) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }
    }

    public User getUserByEmail(String email) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        }
    }

    public List<User> getUserByAddressStreetName(String streetName) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.address.streetName = :streetName", User.class);
            query.setParameter("streetName", streetName);
            return query.getResultList();
        }
    }

    public UserDataDTO seeUserData(int id) {
        try (var em = emf.createEntityManager()) {

            return (UserDataDTO) em.createNamedQuery("user.GetAllUserData", UserDataDTO.class).setParameter("id", id).getResultList();
        }


    }
}
