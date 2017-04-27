package dao;

import java.sql.SQLException;

import model.Product;

/**
 * Created by arahis on 4/27/17.
 */
public interface ProductsDao {

    void addProduct(Product product) throws SQLException;

    Product getByName(String name) throws  SQLException;
}
