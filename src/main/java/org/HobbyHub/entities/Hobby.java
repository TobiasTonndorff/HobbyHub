package org.HobbyHub.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "hobby")
@NamedQueries({
        @NamedQuery(name = "Hobby.HobbiesUserCountDTO", query = "SELECT new org.HobbyHub.dto.HobbyUserCountDTO(h.id, h.name, CAST(COUNT(u) AS int) ) FROM Hobby h JOIN h.users u GROUP BY h.id"),
        @NamedQuery(name = "Hobby.HobbyUserCountDTO", query = "SELECT new org.HobbyHub.dto.HobbyUserCountDTO(h.id, h.name, CAST(COUNT(u) AS int) ) FROM Hobby h JOIN h.users u WHERE h.id = :id GROUP BY h.id" ),
        @NamedQuery(name = "Hobby.deleteAllHobbies", query = "DELETE FROM Hobby h")
})
public class Hobby {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "wiki_link")
    private String wikiLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private HobbyType type;

    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public Hobby(String name, String wikiLink, Category category, HobbyType type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public void addUser(User user) {
        users.add(user);
        user.addHobby(this);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeUser(User user) {
        users.remove(user);
        user.removeHobby(this);
    }

    public enum HobbyType {
        INDOOR("Indendørs"),
        CONCERT("Konkurrence"),
        COMBO("Indendørs/Udendørs"),
        OBSERVATION("Observation"),
        COLLECTING("Samler hobbyer"),
        EDUCATIONAL("Educational hobbies"),
        OUTDOOR("Udendørs"),
        NA("---");

        private final String typeString;

        private HobbyType(String typeString) {
            this.typeString = typeString;
        }
    }

    public enum Category {
        GENERAL("Generel"),
        EDUCATIONAL("Educational hobbies"),
        COLLECTING("Samler hobbyer"),
        CONCERT("Konkurrence"),
        OBSERVATION("Observation");

        private final String categoryString;

        private Category(String categoryString) {
            this.categoryString = categoryString;
        }


    }
}
