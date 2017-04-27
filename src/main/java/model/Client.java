package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by arahis on 4/27/17.
 */
@NoArgsConstructor
public @Data class Client {
    private int id;
    private String name;
    private String phoneNumber;

    public Client(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
