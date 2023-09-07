package entities;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Phone;
import org.HobbyHub.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneTest {


    private static EntityManagerFactory emf;
    private static EntityManager em;
    Address address = new Address();
    User user = new User();
    Phone phone = new Phone();

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
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

    @BeforeEach
    void setUp() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @Test
    void setUser() {
       User user = new User("john", "doe", null, null, null);
         phone.setUser(user);
            assertEquals(user, phone.getUser());
    }

    @Test
    void setNumber(){
        phone.setNumber("12345678");
        assertEquals("12345678", phone.getNumber());

    }

}
