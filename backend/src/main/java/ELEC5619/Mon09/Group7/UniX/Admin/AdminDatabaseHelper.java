package ELEC5619.Mon09.Group7.UniX.Admin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDatabaseHelper {
  private static final String DB_NAME = "admin.db";

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
  public AdminDatabaseHelper() throws SQLException {
    connect();
    ensureAdminTable();
  }

  /*
   * Change the data type of phone number type to char
   */
  private void ensureAdminTable() throws SQLException {
    String sql = """
        CREATE TABLE IF NOT EXISTS admin (
        AdminID int PRIMARY KEY,
        Username varchar(20) NOT NULL,
        Password varchar(20) NOT NULL,
        );
        """;
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Delete User table
   */
  private void deleteAdminTable() throws SQLException {
    String sql = "DROP TABLE IF EXISTS admin";
    try (Statement statement = connection.createStatement()) {
      statement.execute(sql);
    }
  }

  /*
   * Add new admin to the database
   */
  public void addAdmin(int adminId, String username, String password) throws SQLException {
    String sql = "INSERT INTO admin (AdminID, Email, UserName, Password) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, adminId);
      if (username != null)
        preparedStatement.setString(2, username);
      if (password != null)
        preparedStatement.setString(4, password);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
  }

  /*
   * Get all admin from the database
   */

  public List<Admin> getAdmin() throws SQLException {
    String sql = "SELECT * FROM admin";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Admin> adminList = new ArrayList<Admin>();
      while (resultSet.next()) {
        int adminId = resultSet.getInt("AdminID");
        String username = resultSet.getString("Username");
        String password = resultSet.getString("Password");
        Admin admin = new Admin(adminId, username, password);
        adminList.add(admin);
      }
      return adminList;
    } catch (SQLException e) {
      System.out.println(e);
      System.exit(-1);
    }
    return null;
  }
}
