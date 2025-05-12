package dyaz.io.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {

  @Test
  void testMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    DatabaseMetaData metaData = connection.getMetaData();

    System.out.println(metaData.getDatabaseProductName());
    System.out.println(metaData.getDatabaseProductVersion());

    ResultSet resultSet = metaData.getTables("belajar_java_database", null, null, null);

    while (resultSet.next()){
      String tableName = resultSet.getString("TABLE_NAME");
      System.out.println("Table : "+tableName);
    }

    connection.close();
  }

  @Test
  void testParameterMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments (email, comment) VALUES (?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

    System.out.println(parameterMetaData.getParameterCount());
    // System.out.println(parameterMetaData.getParameterType(1)); // ERROR

    preparedStatement.close();
    connection.close();
  }

  @Test
  void testResultSetMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

    ResultSetMetaData metaData = resultSet.getMetaData();

    for (int i = 1; i <= metaData.getColumnCount() ; i++) {
      System.out.println();
      System.out.println("Name      : "+ metaData.getColumnName(i));
      System.out.println("Type      : "+ metaData.getColumnType(i));
      System.out.println("Type Name : "+ metaData.getColumnTypeName(i));

      if (metaData.getColumnType(i) == Types.INTEGER) {
        System.out.println("INI INTEGER");
      }
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}
