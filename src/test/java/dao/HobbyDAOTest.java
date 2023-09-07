package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.HobbyDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.DAO.ZipDAO;
import org.HobbyHub.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @BeforeEach
    void setUp() {
        Hobby hobby = new Hobby("test", "link", Hobby.Category.GENERAL, Hobby.HobbyType.INDOOR);
        hobbyDAO.createHobby(hobby);

        ZipDAO zipDAO = ZipDAO.getInstance(emf);

        ZipCode zipCode = new ZipCode("1234", "test", "test");

        zipDAO.saveZip(zipCode);


        UserDAO userDAO = UserDAO.getInstance(emf);
        AddressDAO addressDAO = AddressDAO.getInstance(emf);

        Address address = new Address("Hovedgaden", "1");

        address.setZipCode(zipCode);

        addressDAO.createAddress(address);


        User steve = new User("Steve", "Jobs", LocalDate.of(1955, 2, 24), "email", address);
        hobby.addUser(steve);
        userDAO.saveUser(steve);

        hobbyDAO.updateHobby(hobby);

    }

    @Test
    void getHobbyById() {
        var hobby = hobbyDAO.getHobbyById(1);
        assertEquals(1, hobby.getId());
    }

    @Test
    void updateHobby() {
        var hobby = hobbyDAO.getHobbyById(1);
        hobby.setName("test2");
        hobbyDAO.updateHobby(hobby);
        var hobbyActual = hobbyDAO.getHobbyById(1);
        assertEquals("test2", hobbyActual.getName());
    }
////////////////////////////
    //den her driller
    @Test
    void deleteHobby() {

        UserDAO userDAO = UserDAO.getInstance(emf);
        userDAO.deleteUser(1);
        hobbyDAO.deleteHobby(1);
        var hobby = hobbyDAO.getHobbyById(1);
        assertNull(hobby);
    }
//////////////////////////////
    @Test
    void getHobbyUserCount() {
        var res = hobbyDAO.getHobbyUserCount(1);
        assertEquals(1, res.getCount());
    }

    @Test
    void getHobbiesUserCount() {
        var res = hobbyDAO.getHobbiesUserCount();
        assertEquals(1, res.size());
    }
}