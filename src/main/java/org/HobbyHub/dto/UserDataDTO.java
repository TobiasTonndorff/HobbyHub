package org.HobbyHub.dto;

import lombok.Getter;
import lombok.ToString;
import org.HobbyHub.entities.Address;

import java.time.LocalDate;

@Getter
@ToString
public class UserDataDTO {

    private int id;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    private String email;

    private Address address;



    public UserDataDTO(String firstname, String lastname, LocalDate birthdate, String email, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.address = address;
    }
}
