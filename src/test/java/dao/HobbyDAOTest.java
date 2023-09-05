package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.HobbyDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static HobbyDAO hobbyDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        hobbyDAO = HobbyDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    @Test
    void getInstance() {
    }

    @Test
    void getHobbyById() {
    }

    @Test
    void updateHobby() {
    }

    @Test
    void deleteHobby() {
    }

    @Test
    void getHobbyUserCount() {
    }

    @Test
    void getHobbiesUserCount() {
    }
}