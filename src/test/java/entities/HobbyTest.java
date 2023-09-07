package entities;
import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class HobbyTest {


    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;


    @BeforeEach
    void setUp() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();

    }
@Test
    public void testAddUserToHobby(){
        Hobby hobby = new Hobby();
        Address address = new Address("østre paradisvej", "32");

        User user = new User("drake", "jake",
                java.time.LocalDate.now(), "chrissy@gmail.com",address);

        hobby.addUser(user);


        em.persist(hobby);
        em.persist(user);
        tx.commit();


        Hobby retriveHobby = em.find(Hobby.class, hobby.getId());
        assertNotNull(retriveHobby);
        assertEquals(1, retriveHobby.getUsers().size());
        assertTrue(retriveHobby.getUsers().contains(user));


}










}
