package org.HobbyHub.entities;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Table(name = "'user'")
@Entity
@NamedQueries({
        @NamedQuery(name = "user.GetAllUserData", query = "SELECT new org.HobbyHub.dto.UserDataDTO(u.firstname, u.surname, u.birthdate, u.email, u.address) FROM User u WHERE u.id = :id"),
})
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_hobby",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private Set<Hobby> hobbies = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Phone> phones = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
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
    }

    public void addHobby(Hobby hobby) {
        hobbies.add(hobby);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }

}
