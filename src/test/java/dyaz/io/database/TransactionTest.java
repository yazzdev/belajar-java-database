package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

  @Test
  void testCommit() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

    for (int i = 0; i < 100; i++) {
      PreparedStatement prepareStatement = connection.prepareStatement(sql);
      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.executeUpdate();
      prepareStatement.close();
    }

    connection.commit();
    connection.close();
  }

  @Test
  void testRollback() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

    for (int i = 0; i < 100; i++) {
      PreparedStatement prepareStatement = connection.prepareStatement(sql);
      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.executeUpdate();
      prepareStatement.close();
    }

    connection.rollback();
    connection.close();
  }

}
