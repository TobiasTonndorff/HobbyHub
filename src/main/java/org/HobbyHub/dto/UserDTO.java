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

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstname();
        this.surname = user.getSurname();
        this.birthdate = user.getBirthdate();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }



}
