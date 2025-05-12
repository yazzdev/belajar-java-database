package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {
  @Test
  void testDate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String sql = """
            INSERT INTO sample_time (sample_date, sample_time, sample_timestamp)
            VALUES (?, ?, ?)
            """;
    PreparedStatement prepareStatement = connection.prepareStatement(sql);

    prepareStatement.setDate(1, new Date(System.currentTimeMillis()));
    prepareStatement.setTime(2, new Time(System.currentTimeMillis()));
    prepareStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

    prepareStatement.executeUpdate();

    prepareStatement.close();
    connection.close();
  }

  @Test
  void testDataQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    String sql = "SELECT * FROM sample_time";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    ResultSet resultSet = prepareStatement.executeQuery();

    while (resultSet.next()) {
      Date sampleDate = resultSet.getDate("sample_date");
      Time sampleTime = resultSet.getTime("sample_time");
      Timestamp sampleTimestamp = resultSet.getTimestamp("sample_timestamp");

      System.out.println("Date      : " + sampleDate);
      System.out.println("Time      : " + sampleTime);
      System.out.println("Timestamp : " + sampleTimestamp);

    }

    resultSet.close();
    prepareStatement.close();
    connection.close();
  }
}
