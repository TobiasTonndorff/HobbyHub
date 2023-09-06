package org.HobbyHub.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.HobbyHub.entities.Phone;

import java.util.List;

public class PhoneDAO {

    private static EntityManagerFactory emf;

    private static PhoneDAO instance;

    public static PhoneDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneDAO();
        }
        return instance;
    }

    public void createPhone(Phone phone) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            em.close();
        }
    }

    public Phone updatePhone(Phone phone) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Phone updatedPhone = em.merge(phone);
            em.getTransaction().commit();
            em.close();
            return updatedPhone;
        }
    }

    public void deletePhone(int id) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Phone phone = findPhoneById(id);
            if (phone != null) {
                em.remove(phone);
            }
            em.getTransaction().commit();
            em.close();
        }
    }

    public Phone findPhoneById(int id) {
        try(var em = emf.createEntityManager()) {
            Phone foundPhone = em.find(Phone.class, id);
            em.close();
            return foundPhone;
        }
    }

    public List<Phone> findPhonesByUserId(int userid) {
        try(var em = emf.createEntityManager()) {
            TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p WHERE p.user.id = :id", Phone.class);
            query.setParameter("id", userid);
            List<Phone> phones = query.getResultList();
            em.close();
            return phones;
        }
    }





    
}
