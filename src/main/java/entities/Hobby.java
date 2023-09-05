package entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "hobby")
public class Hobby {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "wikilink")
    private String wikiLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private HobbyType type;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    public Hobby(String name, String wikiLink, Category category, HobbyType type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public void addUser(User user) {
        users.add(user);
    }






    public enum HobbyType {
        INDENDØRS("Indendørs"),
        KONKURRENCE("Konkurrence"),
        KOMBI("Indendørs/Udendørs"),
        OBSERVATION("Observation"),
        SAMLER("Samler hobbyer"),
        UDENDØRS("Udendørs"),
        NA("---");

        private final String typeString;

        private HobbyType(String typeString) {
            this.typeString = typeString;
        }
    }

    public enum Category {
        GERNEREL("Generel"),
        EDUCATIONAL("Educational hobbies"),
        SAMLINGER("Samler hobbyer"),
        KONKURRENCE("Konkurrence"),
        OBSERVATION("Observation");

        private final String categoryString;

        private Category(String categoryString) {
            this.categoryString = categoryString;
        }


    }
}
