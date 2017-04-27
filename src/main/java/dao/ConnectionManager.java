package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by arahis on 4/27/17.
 */
public class ConnectionManager {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/ordersdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

        if (connection == null) throw new RuntimeException("cannot get connection");
        return connection;
    }

    public static void dbInit() throws SQLException {
        clientsInit();
        ordersInit();
        productsInit();
    }

    private static void productsInit() throws SQLException {
        executeSql("DROP TABLE IF EXISTS " + DbSchema.Products.TABLE_NAME);
        executeSql("CREATE TABLE " + DbSchema.Products.TABLE_NAME
                + "("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + DbSchema.Products.NAME_COL + " VARCHAR(30) NOT NULL UNIQUE, "
                + DbSchema.Products.PRICE_COL + " INT"
                + ")"
        );
    }

    private static void ordersInit() throws SQLException {
        executeSql("DROP TABLE IF EXISTS " + DbSchema.Orders.TABLE_NAME);
        executeSql("CREATE TABLE " + DbSchema.Orders.TABLE_NAME
                + "("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + DbSchema.Orders.DATE_COL + " TIMESTAMP NOT NULL, "
                + DbSchema.Orders.CLIENT_ID_COL + " INT NOT NULL, "
                + DbSchema.Orders.PRODUCT_ID_COL + " INT NOT NULL"
                + ")"
        );
    }

    private static void clientsInit() throws SQLException {
        executeSql("DROP TABLE IF EXISTS " + DbSchema.Clients.TABLE_NAME);
        executeSql("CREATE TABLE " + DbSchema.Clients.TABLE_NAME
                + "("
                + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                + DbSchema.Clients.NAME_COL + " VARCHAR(20), "
                + DbSchema.Clients.PHONE_NUM_COL + " VARCHAR(13) NOT NULL UNIQUE"
                + ")"
        );
    }

    private static void executeSql(String sqlQuery) throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement
                         = connection.prepareStatement(sqlQuery)) {
                statement.execute();
            }
        }
    }
}
