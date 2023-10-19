package ELEC5619.Mon09.Group7.UniX.ShoppingSession;

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

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;

import java.util.Date;

public class ShoppingSessionDatabaseHelper {
    private static final String DB_NAME = "ShoppingSession.db";

    private Connection connection;

    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
    }


    public ShoppingSessionDatabaseHelper() throws SQLException {
        connect();
        ensureShoppingSessionTable();
    }

    private void ensureShoppingSessionTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS ShoppingSession (
            SessionID int PRIMARY KEY,
            BuyerUserID int NOT NULL,
            ItemID int NOT NULL,
            SellerUserID int NOT NULL, 
            Total int,
            FOREIGN KEY (BuyerUserID) REFERENCES User(UserID),
            FOREIGN KEY (SellerUserID) REFERENCES User(UserID),
            FOREIGN KEY (ItemID) REFERENCES Item(ItemID),
            );
            """;
        try (Statement statement = connection.createStatement()) {
          statement.execute(sql);
        }
    }

    public void addShoppingSession(int sessionID, int buyerUserID, int sellerUserID, int itemID, int total) {
        String sql = """
            INSERT INTO ShoppingSession (SessionID, BuyerUserID, ItemID, SellerUserID, Total) VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, sessionID);
            preparedStatement.setInt(2, buyerUserID);
            preparedStatement.setInt(3, itemID);
            preparedStatement.setInt(4, sellerUserID);
            preparedStatement.setInt(5, total);
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }
    

    public List<ShoppingSession> getShoppingSessions() throws SQLException {
        String sql = "SELECT * FROM ShoppingSession";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ShoppingSession> shoppingSessions = new ArrayList<ShoppingSession>();

            while(resultSet.next()) {
                int sessionID = resultSet.getInt("SessionID");
                int buyerUserID = resultSet.getInt("BuyerUserID");
                int itemID = resultSet.getInt("ItemID");
                int sellerUserID = resultSet.getInt("SellerUserID");
                int total = resultSet.getInt("Total");

                ShoppingSession session = new ShoppingSession(sessionID, buyerUserID, itemID, total, sellerUserID);
                shoppingSessions.add(session);
            }

            return shoppingSessions;
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(-1);
        }

        return null;
    } 
}
