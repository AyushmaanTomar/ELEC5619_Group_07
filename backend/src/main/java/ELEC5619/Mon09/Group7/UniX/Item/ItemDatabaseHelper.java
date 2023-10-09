package ELEC5619.Mon09.Group7.UniX.Item;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.Math;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ItemDatabaseHelper {

    private static final String DB_NAME = "Item.db";

    private Connection connection;

    private void connect()  throws SQLException {
        connection = DriverManager.getConnection(DB_NAME, DB_NAME, DB_NAME);
    }

    public ItemDatabaseHelper() throws SQLException {
        connect();
    
    }

    // Note that there is not date type in sqlliet hence we used 
    // Text datatype
    private void ensureItemTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Item (
                ItemID int PRIMARY KEY,
                CategoryID int NOT NULL,
                UserID int NOT NULL,
                Name varchar(20),
                Price decimal(5, 2),
                Qty int,
                LikeAmount int,
                Description varchar(200),
                ListsingDate TEXT,
                isSold boolean,
                FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
                FOREIGN KEY (UserID) REFERENCES User(UserID),
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    public void addItem(int ItemID, int CategoryID, int userId, String name, 
        float price, int qty, int likeAmount, String description,
        Date listsingDate, boolean isSold) {
        String sql = """
            INSERT INTO Item (ItemID, CategoryID, UserID,Name, Price, Qty, LikeAmount, 
            Description, ListsingDate, isSold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ItemID);
            preparedStatement.setInt(2, CategoryID);
            preparedStatement.setInt(3, userId);
            
            if (name != null) preparedStatement.setString(4, name);

            double newPrice = (double) Math.round(price * 100) / 100;
            preparedStatement.setDouble(5, newPrice);

            preparedStatement.setInt(6, qty);
            preparedStatement.setInt(7, likeAmount);
            preparedStatement.setString(8, description);
            
            if (listsingDate != null) {
                preparedStatement.setString(9, listsingDate.toString());
            }

            preparedStatement.setBoolean(10, isSold);

            preparedStatement.executeUpdate();

        } catch (SQLException e ) {
            System.out.println(e);
            System.exit(-1);
        }

    }


    public List<Item> getItemList() throws SQLException {
        String sql = "select * from Item";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Item> items = new ArrayList<Item>();

            while (resultSet.next()) {
                int itemID = resultSet.getInt("ItemID");
                int categoryId = resultSet.getInt("CategoryID");
                int userID = resultSet.getInt("UserID");
                String name = resultSet.getString("Name");
                Double price = resultSet.getDouble("Price");
                int qty = resultSet.getInt("Qty");
                int likeAmount = resultSet.getInt("LikeAmount");
                String description = resultSet.getNString("Description");
                Date listDate = resultSet.getDate("ListsingDate");
                Boolean isSold = resultSet.getBoolean("isSold");
                Item item = new Item(itemID, categoryId, userID, name, userID, description, qty, listDate, likeAmount, isSold);
                items.add(item);
            }

            return items;
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(-1);
        }

        return null;
    }


    
}
