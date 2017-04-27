package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.ConnectionManager;
import dao.DbSchema;
import dao.OrdersDao;
import model.Order;

/**
 * Created by arahis on 4/27/17.
 */
public class OrdersDaoImpl implements OrdersDao {
    @Override public void addOrder(Order order) throws SQLException {
        String insertSQL = String.format("INSERT INTO %s (%s, %s, %s) VALUES(?, ?, ?)",
                DbSchema.Orders.TABLE_NAME,
                DbSchema.Orders.DATE_COL,
                DbSchema.Orders.CLIENT_ID_COL,
                DbSchema.Orders.PRODUCT_ID_COL
        );
        try (Connection conn = ConnectionManager.getConnection()) {
            Timestamp timestamp = new Timestamp(order.getDate().getTime());
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setTimestamp(1, timestamp);
            ps.setInt(2, order.getClientId());
            ps.setInt(3, order.getOrderId());
            ps.execute();
        }
    }
}
