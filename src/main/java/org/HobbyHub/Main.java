package org.HobbyHub;

import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.HobbyDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.config.HibernateConfig;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.Phone;
import org.HobbyHub.entities.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobbyhub");

//        createUser(emf);

//        countAllUsersInAllHobbies(emf);

//        countAllUsersInAllHobby(emf);

//        getAllUsersByHobbyId(emf);

        getFullUser(emf);


    }

    private static void getFullUser(EntityManagerFactory emf) {
        UserDAO userDAO = UserDAO.getInstance(emf);

        var res = userDAO.getFullUser("+45 12345678");
        System.out.println(res);
    }

    private static void getAllUsersByHobbyId(EntityManagerFactory emf) {
        UserDAO userDAO = UserDAO.getInstance(emf);

        userDAO.getAllUsersByHobbyId(1).forEach(System.out::println);
    }

    private static void countAllUsersInAllHobby(EntityManagerFactory emf) {
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);

        var res = hobbyDAO.getHobbyUserCount(1);

        System.out.println(res);
    }


    private static void countAllUsersInAllHobbies(EntityManagerFactory emf) {
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);

        hobbyDAO.getHobbiesUserCount().forEach(System.out::println);
    }

    private static void createUser(EntityManagerFactory emf) {
        UserDAO userDAO = UserDAO.getInstance(emf);
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);
        AddressDAO addressDAO = AddressDAO.getInstance(emf);

        Hobby hobby = hobbyDAO.getHobbyById(1);
        Address address = new Address("Hovedgaden", "1");


        addressDAO.createAddress(address);

        Phone phone = new Phone("+45 12345678");


        User steve = new User("Steve", "Jobs", LocalDate.of(1955, 2, 24), "email", address);
        steve.addPhone(phone);

        hobby.addUser(steve);
        userDAO.saveUser(steve);

        hobbyDAO.updateHobby(hobby);
    }
}