package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ClientsDao;
import dao.ConnectionManager;
import dao.DbSchema;
import model.Client;

/**
 * Created by arahis on 4/27/17.
 */
public class ClientsDaoImpl implements ClientsDao {
    @Override public void addClient(Client client) throws SQLException {
        String insertSQL = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?)",
                DbSchema.Clients.TABLE_NAME,
                DbSchema.Clients.NAME_COL,
                DbSchema.Clients.PHONE_NUM_COL
        );
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, client.getName());
            ps.setString(2, client.getPhoneNumber());
            ps.execute();
        }
    }

    @Override public Client getByPhoneNum(String phoneNum) throws SQLException {
        String selectSQL = String.format("SELECT * FROM %s WHERE %s=?",
                DbSchema.Clients.TABLE_NAME,
                DbSchema.Clients.PHONE_NUM_COL
        );
        Client client = null;
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(selectSQL);
            ps.setString(1, phoneNum);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setPhoneNumber(rs.getString(3));
            }
        }
        return client;
    }
}
