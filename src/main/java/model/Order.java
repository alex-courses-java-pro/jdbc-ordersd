package model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by arahis on 4/27/17.
 */
public @Data class Order {
    private int id;
    private Date date;
    private int clientId;
    private int orderId;

    public Order(Date date, int clientId, int orderId) {
        this.date = date;
        this.clientId = clientId;
        this.orderId = orderId;
    }
}
