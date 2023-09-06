package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.HobbyDAO;
import org.HobbyHub.DAO.PhoneDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        deleteDB();

    }

    @BeforeEach
    void setUp() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    private static void deleteDB() {
        em.getTransaction().begin();
        em.createNamedQuery("user.deleteAllUsers").executeUpdate();
        em.createNamedQuery("Hobby.deleteAllHobbies").executeUpdate();
        em.createNamedQuery("Address.deleteAllAddresses").executeUpdate();
        em.createNamedQuery("phone.deleteAllPhones").executeUpdate();
        em.createNamedQuery("ZipCode.deleteAllZipCodes").executeUpdate();
        em.getTransaction().commit();
    }


    @Test
    void saveUser() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe1.com", null);
        userDAO.saveUser(user);
        assertNotNull(user.getId());
        System.out.println("User id: " + user.getId());
    }

    @Test
    void updateUser() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe2.com", null);
        userDAO.saveUser(user);
        user.setFirstname("Jane");
        userDAO.updateUser(user);
        assertEquals("Jane", user.getFirstname());
        System.out.println("User updated firstname: " + user.getFirstname());
    }

    @Test
    void getUserById() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe3.com", null);
        userDAO.saveUser(user);
        assertNotNull(userDAO.getUserById(user.getId()));
        System.out.println("User id: " + user.getId());
    }

    @Test
    void deleteUser() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe4.com", null);
        userDAO.saveUser(user);
        System.out.println("user id: " + user.getId());
        userDAO.deleteUser(user.getId());
        assertNull(userDAO.getUserById(user.getId()));
        System.out.println("User deleted: " + user.getId());
    }


    @Test
    void createUser() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe5.com", null);
        userDAO.createUser(user);
        assertNotNull(user.getId());
        System.out.println("user firstname: " + user.getFirstname());
        System.out.println("user surname: " + user.getSurname());

    }

    @Test
    void getUserByEmail() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe6.com", null);
        userDAO.createUser(user);
        assertNotNull(userDAO.getUserByEmail(user.getEmail()));
        System.out.println("user email: " + user.getEmail());
    }

    @Test
    void getUserByAddressStreetName() {
        Address address = new Address("Hovedgaden", "1");
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe7.com", address);
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        userDAO.createUser(user);
        assertNotNull(userDAO.getUserByAddressStreetName(address.getStreetName()));
        System.out.println("user address streetname: " + address.getStreetName());
    }

    @Test
    void getAllUsersHobbyById() {
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe8.com", null);
        userDAO.createUser(user);
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);
    }
}