package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

  @Test
  void tesetCreateStatement() throws SQLException {

    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    statement.close();
    connection.close();

  }

  @Test
  void tesetExecuteUpdate() throws SQLException {

    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
            INSERT INTO customers(id, name, email) VALUES
            ('123', 'Dyaz', 'dyaz@email.com'),
            ('124', 'Amrullah', 'amrullah@email.com'),
            ('125', 'Nikmah', 'nikmah@email.com'),
            ('126', 'Ramadhani', 'ramadhani@email.com'),
            ('127', 'Nira', 'nira@email.com')
            """;
    int update = statement.executeUpdate(sql);
    System.out.println(update);


    statement.close();
    connection.close();

  }

  @Test
  void tesetExecuteDelete() throws SQLException {

    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
            DELETE FROM customers;
            """;
    int update = statement.executeUpdate(sql);
    System.out.println(update);


    statement.close();
    connection.close();

  }

  @Test
  void testExecuteQuery() throws SQLException {

    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
            SELECT * FROM customers;
            """;
    ResultSet resultSet = statement.executeQuery(sql);

    resultSet.close();
    statement.close();
    connection.close();

  }
}
