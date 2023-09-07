package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.PhoneDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.DAO.ZipDAO;
import org.HobbyHub.entities.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
        deleteDB();
    }

    @BeforeEach
    void setUp() {

        ZipDAO zipDAO = ZipDAO.getInstance(emf);

        ZipCode zipCode = new ZipCode(1,"1234", "test", "test");

        zipDAO.saveZip(zipCode);


        UserDAO userDAO = UserDAO.getInstance(emf);
        AddressDAO addressDAO = AddressDAO.getInstance(emf);

        Address address = new Address("Hovedgaden", "1");

        address.setZipCode(zipCode);

        addressDAO.createAddress(address);


        User steve = new User("Steve", "Jobs", LocalDate.of(1955, 2, 24), "email", address);
        userDAO.saveUser(steve);

    }

    @AfterAll
    static void tearDownAll() {
        em.close();
    }

    @AfterEach
    void tearDownEach() {
        deleteDB();
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
    void createPhone() {
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        var res = phoneDAO.findPhoneById(1);
        assertNotNull(res);
    }

    @Test
    void updatePhone() {
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        phone.setPhoneNumber("87654321");
        phoneDAO.updatePhone(phone);
        assertEquals("87654321", phone.getNumber());
    }

    @Test
    void deletePhone() {
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        phoneDAO.deletePhone(1);
        assertNull(phoneDAO.findPhoneById(1));

    }

    @Test
    void findPhoneById() {
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        var res = phoneDAO.findPhoneById(1);
        System.out.println(res);
        assertEquals(1, res.getId());
    }

    @Test
    void findPhonesByUserId() {
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        assertNotNull(phoneDAO.findPhonesByUserId(1));
    }

    @Test
    void addPhoneToUser(){
        Phone phone = new Phone("12345678");
        phoneDAO.createPhone(phone);
        UserDAO userDAO = UserDAO.getInstance(emf);
        User user = userDAO.getUserById(1);
        user.addPhone(phone);
        userDAO.updateUser(user);
        assertEquals(1, user.getPhones().size());
    }
}