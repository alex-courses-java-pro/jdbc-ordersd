package dao;

import java.sql.SQLException;

import model.Client;

/**
 * Created by arahis on 4/27/17.
 */
public interface ClientsDao {

    void addClient(Client client) throws SQLException;

    Client getByPhoneNum(String phoneNum) throws SQLException;
}
