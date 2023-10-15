package ELEC5619.Mon09.Group7.UniX.User;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHelper {
  private static final String DB_NAME = "user.db";

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
  public UserDatabaseHelper() throws SQLException {
    connect();
    ensureUserTable();
  }

  /*
   * Change the data type of phone number type to char
   */
  private void ensureUserTable() throws SQLException {
    String sql = """
        CREATE TABLE IF NOT EXISTS user (
        UserID int PRIMARY KEY,
        Email varchar(50) NOT NUll,
        Username varchar(20) NOT NULL,
        Password varchar(20) NOT NULL,
        Phone char(10)
        );
        """;
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Delete User table
   */
  private void deleteUserTable() throws SQLException {
    String sql = "DROP TABLE IF EXISTS user";
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Add new user to the database
   */
  public void addUser(int userID, String email, String userName, String passWord,
      boolean isAdmin, String phone) throws SQLException {
    String sql = "INSERT INTO user (UserID, Email, Username, Password, Phone) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, userID);
      if (email != null)
        preparedStatement.setString(2, email);
      if (userName != null)
        preparedStatement.setString(3, userName);
      if (passWord != null)
        preparedStatement.setString(4, passWord);
      preparedStatement.setBoolean(5, isAdmin);
      if (phone != null)
        preparedStatement.setString(6, phone);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
  }

  /*
   * Get all users from the database
   */
  public List<User> getUser() {
    String sql = "SELECT * FROM user";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      List<User> userList = new ArrayList<User>();
      while (resultSet.next()) {
        int userID = resultSet.getInt("UserID");
        String username = resultSet.getString("Username");
        String email = resultSet.getString("Email");
        String password = resultSet.getString("Password");
        String phone = resultSet.getString("Phone");
        User user = new User(userID, username, email, password, phone);
        userList.add(user);
      }
      return userList;
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
    return null;
  }

}
