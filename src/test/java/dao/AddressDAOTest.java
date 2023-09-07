package dao;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.ZipDAO;
import org.HobbyHub.entities.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static AddressDAO addressDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        addressDAO = AddressDAO.getInstance(emf);
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    @Test
    void createAddress() {
        Address address = new Address("Hovedgaden", "1");
        addressDAO.createAddress(address);
        assertNotNull(address.getId());
    }

    @Test
    void getAddressById() {
        Address address = new Address("Hovedgaden", "1");
        addressDAO.createAddress(address);
        assertNotNull(addressDAO.getAddressById(address.getId()));
    }

    @Test
    void updateAddress() {
        Address address = new Address("Hovedgaden", "1");
        addressDAO.createAddress(address);
        address.setStreetName("Hovedgaden");
        addressDAO.updateAddress(address);
        assertEquals("Hovedgaden", address.getStreetName());
    }

    @Test
    void deleteAddress() {
        Address address = new Address("Hovedgaden", "1");
        addressDAO.createAddress(address);
        addressDAO.deleteAddress(address);
        assertNull(addressDAO.getAddressById(address.getId()));
    }
}