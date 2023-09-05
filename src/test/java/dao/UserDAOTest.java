package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.PhoneDAO;
import org.HobbyHub.DAO.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static UserDAO userDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        userDAO = UserDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }

    @Test
    void getInstance() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void testDeleteUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getUserByAddressStreetName() {
    }
}