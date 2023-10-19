package ELEC5619.Mon09.Group7.UniX.Like;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LikeDatabaseHelper {
  private static final String DB_NAME = "like.db";

  private Connection connection;

  /*
   * Connect SQL table Stuff
   */
  public static void removeDB() {
    // Just normal file IO
    File dbFile = new File(DB_NAME);
    if (dbFile.exists()) {
      boolean result = dbFile.delete();
      if (!result) {
        System.out.println("Couldn't delete existing db file");
        System.exit(-1);
      } else {
        System.out.println("Removed existing DB file.");
      }
    } else {
      System.out.println("No existing DB file.");
    }
  }

  private void connect() throws SQLException {
    connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
  }

  /*
   * Connect to the User Table
   */
  public LikeDatabaseHelper() throws SQLException {
    connect();
    ensureLikeTable();
  }

  /*
   * Change the data type of phone number type to char
   */
  private void ensureLikeTable() throws SQLException {
    String sql = """
        CREATE TABLE IF NOT EXISTS like (
        LikeID int PRIMARY KEY,
        ItemID int,
        UserID int,
        FOREIGN KEY(ItemID) REFERENCES Items(ItemID)
        FOREIGN KEY(UserID) REFERENCES Users(UserID)
        );
        """;
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Delete User table
   */
  private void deleteLikeTable() throws SQLException {
    String sql = "DROP TABLE IF EXISTS like";
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Add new like to the database
   */
  public void addLike(int LikeID, int ItemID, int UserID) throws SQLException {
    String sql = "INSERT INTO like (LikeID, ItemID, UserID) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, LikeID);
      preparedStatement.setInt(2, ItemID);
      preparedStatement.setInt(3, UserID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
  }

  /*
   * Get all Likes from the database
   */

  public List<Like> getLike() throws SQLException {
    String sql = "SELECT * FROM like";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Like> likeList = new ArrayList<Like>();
      while (resultSet.next()) {
        int likeID = resultSet.getInt("LikeID");
        int userID = resultSet.getInt("UserID");
        int itemID = resultSet.getInt("ItemID");
        Like like = new Like(likeID, userID, itemID);
        likeList.add(like);
      }
      return likeList;
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
    return null;
  }
}
