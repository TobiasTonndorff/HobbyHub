package entities;
import jakarta.persistence.*;

@Entity
@Table(name = "zipcode")
public class ZipCode {

    @Id
    private int zip;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "municipality_name")
    private String municipalityName;



}
