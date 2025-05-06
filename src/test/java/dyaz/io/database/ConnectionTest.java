package dyaz.io.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

  //TODO  1st
  @BeforeAll
  static void beforeAll() {
    try {
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(mysqlDriver);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  //TODO 2nd
  @Test
  void testConnection() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("Connection Success!!");
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }

  //TODO Percobaan Close
  @Test
  void testConnectionClose() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
      System.out.println("Connection Success!!");
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}
