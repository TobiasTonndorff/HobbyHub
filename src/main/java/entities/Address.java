package entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode


@Entity
@Table(name = "address")

public class Address {

    // primary key og generation strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "street_name", nullable = false, length = 255)
    private String  streetName;


    @Column(name = "street_number", nullable = false, length = 255)
    private String streetNumber;



    // mange til en relation med ZipCode
    @ManyToOne
    private ZipCode zipCode;


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

    public Address(String streetName, String streetNumber) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }
}
