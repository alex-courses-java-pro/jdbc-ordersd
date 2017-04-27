package dao;

import java.sql.SQLException;

import model.Order;

/**
 * Created by arahis on 4/27/17.
 */
public interface OrdersDao {

    void addOrder(Order order) throws SQLException;
}
