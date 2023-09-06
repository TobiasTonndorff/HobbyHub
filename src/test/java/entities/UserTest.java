package entities;

import config.HibernateTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.Phone;
import org.HobbyHub.entities.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    User user = new User();
    Address address = new Address();
    Set<Hobby> hobbies;
    Set<Phone> phones;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig("hobbyhub_test");
        em = emf.createEntityManager();
        deleteDB();
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
    void setAddress() {
        Address address = new Address("ahornvænget", "5");
        user.setAddress(address);
        assertEquals(address, user.getAddress());
        System.out.println("TestUser address: " + user.getAddress());

    }




    @Test
    void addHobby() {

        Hobby hobby = new Hobby("fodbold", "https://en.wikipedia.org/wiki/Soccer", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        user.addHobby(hobby);
        assertTrue(user.getHobbies().contains(hobby));
        System.out.println("TestUser hobbies: " + user.getHobbies());



    }

    @Test
    void onCreate() {
    User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test", null);
    em.getTransaction().begin();
    em.persist(user1);
    em.getTransaction().commit();
    em.close();
    System.out.println("TestUser created at: " + user1.getCreatedAt());
    System.out.println("TestUser updated at: " + user1.getUpdatedAt());
    assertEquals(user1.getCreatedAt(), user1.getUpdatedAt());

    }

    @Test

    void onUpdate() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test1", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser created at: " + user1.getCreatedAt());
        System.out.println("TestUser updated at: " + user1.getUpdatedAt());
        assertEquals(user1.getCreatedAt(), user1.getUpdatedAt());
        user1.setFirstname("test2");
        user1.setCreatedAt(LocalDate.of(1999, 11,3));
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser created at: " + user1.getCreatedAt());
        System.out.println("TestUser updated at: " + user1.getUpdatedAt());
        assertNotEquals(user1.getCreatedAt(), user1.getUpdatedAt());


    }

    @Test
    void getId() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test2", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser id: " + user1.getId());
        assertNotNull(user1.getId());
    }

    @Test
    void getFirstname() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test3", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser firstname: " + user1.getFirstname());
        assertEquals("test", user1.getFirstname());
    }

    @Test
    void getSurname() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test4", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser surname: " + user1.getSurname());
        assertEquals("test", user1.getSurname());
    }

    @Test
    void getBirthdate() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test5", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser birthdate: " + user1.getBirthdate());
        assertEquals(LocalDate.of(1999, 11,3), user1.getBirthdate());

    }

    @Test
    void getEmail() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test6", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser email: " + user1.getEmail());
        assertEquals("test6", user1.getEmail());
    }

    @Test
    void getCreatedAt() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test7", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser created at: " + user1.getCreatedAt());
        assertEquals(LocalDate.now(), user1.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test8", null);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        em.close();
        System.out.println("TestUser updated at: " + user1.getUpdatedAt());
        assertEquals(LocalDate.now(), user1.getUpdatedAt());
    }

    @Test
    void getHobbies() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test9", null);
        Hobby hobby = new Hobby("fodbold", "https://en.wikipedia.org/wiki/Soccer", Hobby.Category.GENERAL, Hobby.HobbyType.OUTDOOR );
        em.getTransaction().begin();
        em.persist(user1);
        em.persist(hobby);
        em.getTransaction().commit();
        em.close();
        user1.addHobby(hobby);
        System.out.println("TestUser hobbies: " + user1.getHobbies());
        assertTrue(user1.getHobbies().contains(hobby));
    }

    @Test
    void getPhones() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test10", null);
        Phone phone = new Phone("12345678");
        em.getTransaction().begin();
        em.persist(user1);
        em.persist(phone);
        em.getTransaction().commit();
        em.close();
        phone.setUser(user1);
        user1.addPhone(phone);
        phone.getUser();
        user1.getPhones();
        System.out.println("TestUser phones: " + user1.getPhones().toString());
        assertTrue(user1.getPhones().contains(phone));


    }

    @Test
    void getAddress() {
        User user1 = new User("test", "test", LocalDate.of(1999, 11,3), "test11", null);
        Address address = new Address("ahornvænget", "5");
        em.getTransaction().begin();
        em.persist(user1);
        em.persist(address);
        em.getTransaction().commit();
        em.close();
        user1.setAddress(address);
        System.out.println("TestUser address: " + user1.getAddress());
        assertEquals(address, user1.getAddress());

    }
}