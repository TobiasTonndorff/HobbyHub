package org.HobbyHub.dto;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ZipCityDTO {
    private int zip;
    private String city;

    public ZipCityDTO(int zip, String city) {
        this.zip = zip;
        this.city = city;
    }
}
