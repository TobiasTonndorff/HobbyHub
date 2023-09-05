package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
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

    @Column(name = "zip", nullable = false)
    private String zip;

    @ManyToMany(mappedBy = "users")
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "phone")
    private List<PhoneNumber> phones;

    @ManyToOne
    private Address address;
    
    public User(String firstname, String surname, LocalDate birthdate, String email, LocalDate createdAt, LocalDate updatedAt, String zip) {
        this.firstname = firstname;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.zip = zip;
    }
}
