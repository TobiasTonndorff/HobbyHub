package org.HobbyHub.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity

@Table(name = "address")

public class Address {

    // primary key and generation strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "street_name", nullable = false, length = 255)
    private String  streetName;


    @Column(name = "street_number", nullable = false, length = 255)
    private String streetNumber;



    // many to one relation with ZipCode
    @ManyToOne
    private ZipCode zipCode;


    //one to many relation with User

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Address> addresses = new HashSet<>();



    // bi-directional update

    public void addAddress(Address address){
        this.addresses.add(address);
        if(address != null){
            address.setAddress(this);
        }
    }

    private void setAddress(Address address) {
        this.addresses = (Set<Address>) address;
    }



    // constructor
    public Address(String streetName, String streetNumber) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }


}
