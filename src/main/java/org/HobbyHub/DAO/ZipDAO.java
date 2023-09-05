package org.HobbyHub.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.dto.ZipCityDTO;
import org.HobbyHub.entities.ZipCode;

import java.util.List;

public class ZipDAO {

    private static EntityManagerFactory emf;

    private static ZipDAO instance;

    public static ZipDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZipDAO();
        }
        return instance;
    }


    public void saveZip(ZipCode zip) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(zip);
            em.getTransaction().commit();
        }

    }

    public void updateZip(ZipCode zip) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(zip);
            em.getTransaction().commit();
        }
    }

    public ZipCode getZipByCode(String code) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(ZipCode.class, code);
        }
    }

    public void deleteZip(ZipCode zip) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(zip);
            em.getTransaction().commit();
        }
    }

    public List<ZipCityDTO> getAllZipCodes() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createNamedQuery("ZipCode.getAllZipCodesAndCities", ZipCityDTO.class).getResultList();
        }
    }
}
