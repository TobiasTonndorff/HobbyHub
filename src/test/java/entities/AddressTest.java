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

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    Address address = new Address();
    User user = new User();

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
    void addUser(){
        User user = new User("john", "doe", LocalDate.of(1990, 1, 1), "john@doe.com", null);
        address.addUser(user);
        assertEquals(1, address.getUsers().size());

    }

    @Test
    void setZipCode(){
        ZipCode zipCode = new ZipCode("testCity", "testRegion", "testMunicipality");
        address.setZipCode(zipCode);
        assertEquals(zipCode, address.getZipCode());
        System.out.println("TestAddress zipCode: " + address.getZipCode());
    }

    @Test
    void setStreetName(){
        address.setStreetName("testStreetName");
        assertEquals("testStreetName", address.getStreetName());
        System.out.println("TestAddress streetName: " + address.getStreetName());
    }



}
