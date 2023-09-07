package entities;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.User;
import org.HobbyHub.entities.ZipCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipCodeTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    Address address = new Address();
    User user = new User();
    ZipCode zipCode = new ZipCode();

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
    void setCityName(){
        zipCode.setCityName("Copenhagen");
        assertEquals("Copenhagen", zipCode.getCityName());
    }



}
