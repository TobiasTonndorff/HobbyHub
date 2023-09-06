package org.HobbyHub;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.DAO.AddressDAO;
import org.HobbyHub.DAO.HobbyDAO;
import org.HobbyHub.DAO.PhoneDAO;
import org.HobbyHub.DAO.UserDAO;
import org.HobbyHub.config.HibernateConfig;
import org.HobbyHub.dto.UserDTO;
import org.HobbyHub.dto.UserDataDTO;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.Phone;
import org.HobbyHub.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobbyhub");

//        createUser(emf);

//        countAllUsersInAllHobbies(emf);

//        countAllUsersInAllHobby(emf);

//        getAllUsersByHobbyId(emf);

        getFullUser(emf);

        //getPhonesByUserId(emf);

    }

    private static void addPhoneToUser(EntityManagerFactory emf) {
        PhoneDAO phoneDAO = PhoneDAO.getInstance(emf);
        User user = UserDAO.getInstance(emf).getUserById(1);
        Phone phone = new Phone("+45 12345678");
        phone.setUser(user);
        phoneDAO.createPhone(phone);
        System.out.println(phone);

        Phone phone1 = new Phone("+45 43215678");
        phone1.setUser(user);
        phoneDAO.createPhone(phone1);
        System.out.println(phone1);
    }



    private static void getPhonesByUserId(EntityManagerFactory emf) {
        PhoneDAO phoneDAO = PhoneDAO.getInstance(emf);

        phoneDAO.findPhonesByUserId(1).forEach(System.out::println);
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



   /* private static void getAllUsersByCity(EntityManagerFactory emf){
        UserDAO userDAO = UserDAO.getInstance(emf);
        String targetCity = EntityManagerFactoryConfig.getEntityManagerFactoryConfig("hobbyhub");
    }*/


    public static void getAllUsersByCity(EntityManagerFactory emf){
        UserDAO userDAO = UserDAO.getInstance(emf);
        userDAO.getAllUsersInCity(emf, "Aulum").forEach(System.out::println);
        if(userDAO.getAllUsersInCity(emf, "Aulum").isEmpty()){
            System.out.println("No users in this city");
        }
    }
}