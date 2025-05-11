package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
  @Test
  void testBatchStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = "INSERT INTO comments(email, comment) VALUES ('yaz@mail.com', 'Hi')";

    for (int i = 0; i < 1000; i++) {
      statement.addBatch(sql);
    }

    statement.executeBatch();

    statement.close();
    connection.close();
  }

  @Test
  void testBatchPreparedStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);


    for (int i = 0; i < 1000; i++) {
      prepareStatement.clearParameters();

      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.addBatch();
    }

    prepareStatement.executeBatch();

    prepareStatement.close();
    connection.close();
  }
}
