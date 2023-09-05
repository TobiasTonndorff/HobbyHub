package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString


@Entity

public class Address {
@Id
    private int id;
    private String  Address;
    private String StreetNumber;


    //En til mange relation med User

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    // bi-directional update
    public void addUser(User user){
        this.users.add(user);
        if(user != null){
            user.setAddress(this);

        }
    }

}
