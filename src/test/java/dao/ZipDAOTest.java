package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.ZipDAO;
import org.HobbyHub.entities.ZipCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        ZipCode zip = new ZipCode(1, "1234", "test", "test");
        zipDAO.saveZip(zip);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    @Test
    void saveZip() {
        var res = zipDAO.getZipByCode(1);
        assertEquals(1, res.getZip());
    }

    @Test
    void updateZip() {
        var res = zipDAO.getZipByCode(1);
        res.setCityName("test2");
        zipDAO.updateZip(res);
        var res2 = zipDAO.getZipByCode(1);
        assertEquals("test2", res2.getCityName());
    }

    @Test
    void getZipByCode() {
        var res = zipDAO.getZipByCode(1);
        assertEquals(1, res.getZip());
    }

    @Test
    void deleteZip() {
        var res = zipDAO.getZipByCode(1);
        zipDAO.deleteZip(res);
        var res2 = zipDAO.getZipByCode(1);
        assertNull(res2);
    }

    @Test
    void getAllZipCodes() {
        var res = zipDAO.getAllZipCodes();
        assertEquals(1, res.size());
    }
}