package org.HobbyHub.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserByHobbyDTO {

    private String hobby;

    private String firstName;

    private String surname;

    private String email;

    public UserByHobbyDTO(String hobby, String firstName, String surname, String email) {
        this.hobby = hobby;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }
}
