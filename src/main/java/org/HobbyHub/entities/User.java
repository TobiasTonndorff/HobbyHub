package org.HobbyHub.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Table(name = "user")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToMany(mappedBy = "user")
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "user")
    private Set<Phone> phones = new HashSet<>();

    @ManyToOne
    @Column(name = "address", nullable = false)
    @JoinColumn(name = "address")
    private Address address;

    public User(String firstname, String surname, LocalDate birthdate, String email, Address address) {
        this.firstname = firstname;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.address = address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.addUser(this);
        }


    }

}
