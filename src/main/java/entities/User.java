package entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Table(name = "users")
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

    @Column(name = "zip", nullable = false)
    private String zip;

    @ManyToMany(mappedBy = "users")
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "phone")
    private List<PhoneNumber> phones;

    @ManyToOne
    private Address address;

    //add date attributes for: created, lastEdited, birthday

    //add address to user entity
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
