package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

  @Test
  void testSqlInjection() throws SQLException {

    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String username = "admin'; #";
    String password = "admin123";

    String sql = "SELECT * FROM admin WHERE username ='" + username + "' AND password ='" + password + "' ";
    ResultSet resultSet = statement.executeQuery(sql);

    if (resultSet.next()) {
      // Success
      System.out.println("Login Success!! -> Hello " + resultSet.getString("username") +"!!");
    } else {
      // Fail
      System.out.println("Login Fail!!");
    }

    resultSet.close();
    statement.close();
    connection.close();

  }

}
