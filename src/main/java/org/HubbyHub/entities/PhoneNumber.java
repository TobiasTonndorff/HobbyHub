package org.HubbyHub.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", nullable = false)
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }
    
    @ManyToOne
    private User user;

}
