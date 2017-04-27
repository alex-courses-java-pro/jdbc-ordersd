package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionManager;
import dao.DbSchema;
import dao.ProductsDao;
import model.Client;
import model.Product;

/**
 * Created by arahis on 4/27/17.
 */
public class ProductsDaoImpl implements ProductsDao {
    @Override public void addProduct(Product product) throws SQLException {
        String insertSQL = String.format("INSERT INTO %s (%s, %s) VALUES(?, ?)",
                DbSchema.Products.TABLE_NAME,
                DbSchema.Products.NAME_COL,
                DbSchema.Products.PRICE_COL
        );
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.execute();
        }
    }

    @Override public Product getByName(String name) throws SQLException {
        String selectSQL = String.format("SELECT * FROM %s WHERE %s=?",
                DbSchema.Products.TABLE_NAME,
                DbSchema.Products.NAME_COL
        );
        Product product = null;
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(selectSQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(3));
            }
        }
        return product;
    }
}
