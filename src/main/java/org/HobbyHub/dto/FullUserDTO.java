package org.HobbyHub.dto;

import lombok.Getter;
import lombok.ToString;
import org.HobbyHub.entities.Address;
import org.HobbyHub.entities.Hobby;
import org.HobbyHub.entities.Phone;
import org.HobbyHub.entities.User;

import java.util.List;

@Getter
@ToString
public class FullUserDTO {

    private User user;

    private List<Hobby> hobbiesList;

    private List<Phone> phonesList;

    private Address address;

    public FullUserDTO(User user, List<Hobby> hobbiesList, List<Phone> phonesList, Address address) {
        this.user = user;
        this.hobbiesList = hobbiesList;
        this.phonesList = phonesList;
        this.address = address;
    }
}
