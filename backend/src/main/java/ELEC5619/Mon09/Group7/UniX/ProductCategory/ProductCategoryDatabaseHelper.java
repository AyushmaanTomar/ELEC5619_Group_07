package ELEC5619.Mon09.Group7.UniX.ProductCategory;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ELEC5619.Mon09.Group7.UniX.ShoppingSession.ShoppingSession;

public class ProductCategoryDatabaseHelper {
    private static final String DB_NAME = "ProductCategory.db";
    
    private Connection connection;

    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
    }

    public ProductCategoryDatabaseHelper() throws SQLException {
        connect();
        ensureProductCategoryTable();
    }

    private void ensureProductCategoryTable() throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS ProductCategory (
            CategoryID int PRIMARY KEY,
            Name varchar(20) NOT NULL,
            Description varchar(200),
            );
            """;
        try (Statement statement = connection.createStatement()) {
          statement.execute(sql);
        }
    }

    public void addProductCategory(int categoryID, String name, String description) throws SQLException {
        String sql = """
                INSERT INTO ProductCategory (CategoryID, Name, Description) values (?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryID);
            if (name != null)
                preparedStatement.setString(2, name);
            if (description != null)
                preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }


    public List<ProductCategory> getProductCategory() throws SQLException {
        String sql = "SELECT * FROM admin";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
            while (resultSet.next()) {
                int categoryID = resultSet.getInt("CategoryID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                ProductCategory productCategory = new ProductCategory(categoryID, name, description);
                productCategories.add(productCategory);           
            }
            return productCategories;
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(-1);
        }
        return null;
    }

}
