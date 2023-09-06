package org.HobbyHub.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "phone_number")
public class Phone {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Phone(String number) {
        this.number = number;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setPhoneNumber(String number) {
        this.number = number;
    }
}
