package org.HobbyHub.DAO;
import jakarta.persistence.*;
import org.HobbyHub.dto.FullUserDTO;
import org.HobbyHub.dto.UserByHobbyDTO;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.Phone;
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

    public User getUserById(int id){
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

    public User createUser(User user){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }
    }

    public User getUserByEmail(String email){
        try(var em = emf.createEntityManager()){
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        }
    }

    public User getUserByAddressStreetName(String streetName){
        try(var em = emf.createEntityManager()){
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.address.streetName = :streetName", User.class);
            query.setParameter("streetName", streetName);
            return query.getSingleResult();
        }
    }

    public List<UserByHobbyDTO> getAllUsersByHobbyId(int id){
        try(var em = emf.createEntityManager()){
            TypedQuery<UserByHobbyDTO> query = em.createNamedQuery("User.getAllUsersByHobbyDTO", UserByHobbyDTO.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    public FullUserDTO getFullUser(String phone) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u JOIN u.phones p WHERE p.number = :phone",
                    User.class
            );
            query.setParameter("phone", phone);
            User user = query.getSingleResult();

            TypedQuery<Hobby> hobbyQuery = em.createQuery(
                    "SELECT h FROM Hobby h JOIN h.users u WHERE u.id = :id", Hobby.class
            );
            hobbyQuery.setParameter("id", user.getId());
            List<Hobby> hobbies = hobbyQuery.getResultList();


            // get address via user
            TypedQuery<Address> addressQuery = em.createQuery(
                    "SELECT a FROM Address a JOIN a.users u WHERE u.id = :id", Address.class
            );
            addressQuery.setParameter("id", user.getId());
            Address address = addressQuery.getSingleResult();


            // get phones for user
            TypedQuery<Phone> phoneQuery = em.createQuery(
                    "select p from Phone p where p.user.id = :id", Phone.class
            );
            phoneQuery.setParameter("id", user.getId());
            List<Phone> phones = phoneQuery.getResultList();

            FullUserDTO fullUserDTO = new FullUserDTO(user, hobbies, phones, address);
            return fullUserDTO;

        }

    }





}
