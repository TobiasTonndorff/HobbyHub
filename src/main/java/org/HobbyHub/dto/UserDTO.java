package org.HobbyHub.dto;
import lombok.Getter;
import lombok.ToString;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.User;

import java.time.LocalDate;

@Getter
@ToString
public class UserDTO {

    private int id;
    private String firstName;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private Address address;

    public UserDTO(int id, String firstName, String surname, LocalDate birthdate, String email, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.address = address;
    }



}
