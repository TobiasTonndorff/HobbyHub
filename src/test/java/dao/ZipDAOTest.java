package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.ZipDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZipDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static ZipDAO zipDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        zipDAO = ZipDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }



    @Test
    void getInstance() {
    }

    @Test
    void saveZip() {
    }

    @Test
    void updateZip() {
    }

    @Test
    void getZipByCode() {
    }

    @Test
    void deleteZip() {
    }

    @Test
    void getAllZipCodes() {
    }
}