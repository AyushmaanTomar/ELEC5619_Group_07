package ELEC5619.Mon09.Group7.UniX.User;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserDatabaseHelper {
    private static final String DB_NAME = "userdatabase.db";

    private Connection connection;

    public static void removeDB() {
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
}
