package org.HubbyHub.entities;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
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


    private enum HobbyType {
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

    private enum Category {
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
