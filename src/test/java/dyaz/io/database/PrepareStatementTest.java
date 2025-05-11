package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO Solutions For SQL Injection
public class PrepareStatementTest {
  @Test
  void testPrepareStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String username = "admin'; #";
    String password = "admin123";

    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      // Success
      System.out.println("Login Success: Hello " + resultSet.getString("username") + "!!");
    } else {
      // Fail
      System.out.println("Username or Password Incorrect!!");
    }

    preparedStatement.close();
    connection.close();
  }
}

// Prepare Statement should be used when there is user input
// If there is no user input, You are able to Statement as usual
// But, You can use this for all conditions
