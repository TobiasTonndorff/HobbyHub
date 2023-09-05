package org.HobbyHub;

import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.HobbyDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.config.HibernateConfig;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.User;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobbyhub");

//        createUser(emf);

        countAllUsersInAllHobbies(emf);
    }

    private static void countAllUsersInAllHobbies(EntityManagerFactory emf) {
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);

        hobbyDAO.getHobbyUserCount().forEach(System.out::println);
    }

    private static void createUser(EntityManagerFactory emf) {
        UserDAO userDAO = UserDAO.getInstance(emf);
        HobbyDAO hobbyDAO = HobbyDAO.getInstance(emf);
        AddressDAO addressDAO = AddressDAO.getInstance(emf);

        Hobby hobby = hobbyDAO.getHobbyById(1);
        Address address = new Address("Hovedgaden", "1");

        addressDAO.createAddress(address);

        User steve = new User("Steve", "Jobs", LocalDate.of(1955, 2, 24), "email", address);


        hobby.addUser(steve);
        userDAO.saveUser(steve);

        hobbyDAO.updateHobby(hobby);
    }
}