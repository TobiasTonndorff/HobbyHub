package entities;
import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class HobbyTest {


    private static EntityManagerFactory emf;
    private static EntityManager em;


    @BeforeAll
   static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        deleteDB();
    }

        private  static void deleteDB() {
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
        public void getHobby(){

        Hobby hobby = new Hobby("Football", "https://en.wikipedia.org/wiki/American_football", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        em.close();
        System.out.println("hobby" + hobby);
        assertEquals("Football", hobby.getName());

    }

    @Test
    public void createHobby(){
        Hobby hobby = new Hobby("Football", "https://en.wikipedia.org/wiki/American_football", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        ArrayList arrayList = new ArrayList();
        arrayList.add(hobby);
        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        em.close();
        System.out.println("hobby" + hobby);
        List actual = arrayList;
        int expected =1;
        assertEquals(actual.size(), expected);
    }

@Test
public void getHobbyById(){
        Hobby hobby = new Hobby("Football", "https://en.wikipedia.org/wiki/American_football", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        Hobby hobby2 = new Hobby("Football", "https://en.wikipedia.org/wiki/American_football", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        em.getTransaction().begin();
        em.persist(hobby);
        em.persist(hobby2);
        em.getTransaction().commit();
        em.close();
        System.out.println("hobby" + hobby);
        assertEquals(2, hobby2.getId());
}


}











