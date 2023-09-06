package org.HobbyHub.entities;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "zipcode")
@NamedQueries({
        @NamedQuery(name = "ZipCode.getAllZipCodesAndCities", query = "SELECT new org.HobbyHub.dto.ZipCityDTO(z.zip, z.cityName) FROM ZipCode z"),

        @NamedQuery(name = "ZipCode.deleteAllZipCodes", query = "DELETE FROM ZipCode z"),
})
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


    public ZipCode(String cityName, String regionName, String municipalityName, Set<Address> adresses) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
        this.adresses = adresses;
    }

    public ZipCode(String cityName, String regionName, String municipalityName) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }

    public ZipCode(int zip, String cityName, String regionName, String municipalityName) {
        this.zip = zip;
        this.cityName = cityName;
        this.regionName = regionName;
        this.municipalityName = municipalityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setAddress(Address address){
        this.adresses.add(address);
        if(address != null){
            address.setZipCode(this);
        }
    }


}
