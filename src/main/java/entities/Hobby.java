package entities;

import ENUM.Category;
import ENUM.Type;
import jakarta.persistence.*;

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

    @Column(name = "category")
    private Category category;

    @Column(name = "type")
    private Type type;




}
