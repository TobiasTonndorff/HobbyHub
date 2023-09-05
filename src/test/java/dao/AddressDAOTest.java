package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.ZipDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static AddressDAO addressDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        addressDAO = AddressDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    @Test
    void getInstance() {
    }

    @Test
    void createAddress() {
    }

    @Test
    void getAddressById() {
    }

    @Test
    void updateAddress() {
    }

    @Test
    void deleteAddress() {
    }
}