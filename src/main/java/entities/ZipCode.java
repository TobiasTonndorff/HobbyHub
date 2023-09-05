package entities;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

    @OneToMany(mappedBy = "zipCode")
    private Set<Address> adresses = new HashSet<>();

       public ZipCode() {
        }
    public ZipCode(String cityName, String regionName, String municipalityName, Set<Address> adresses) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
        this.adresses = adresses;
    }
}
