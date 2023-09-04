package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "wikilink")
    private String wikiLink;




}
