package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.PhoneDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PhoneDAO phoneDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        phoneDAO = PhoneDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }

    @Test
    void getInstance() {
    }

    @Test
    void createPhone() {
    }

    @Test
    void updatePhone() {
    }

    @Test
    void deletePhone() {
    }

    @Test
    void findPhoneById() {
    }

    @Test
    void findPhoneByPhoneNumber() {
    }

    @Test
    void findPhonesByUserId() {
    }
}