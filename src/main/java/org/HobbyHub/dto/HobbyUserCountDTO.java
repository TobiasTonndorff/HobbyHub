package org.HobbyHub.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HobbyUserCountDTO {

    private int id;

    private String hobbyName;

    private int count;

    public HobbyUserCountDTO(int id, String hobbyName, int count) {
        this.id = id;
        this.hobbyName = hobbyName;
        this.count = count;
    }
}
