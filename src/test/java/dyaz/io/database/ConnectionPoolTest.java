package dyaz.io.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

  @Test
  void testHikariCP() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
    config.setUsername("root");
    config.setPassword("");

    // Config Pool
    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);
    config.setIdleTimeout(60_000);
    config.setMaxLifetime(10 * 60_000);

    try {
      HikariDataSource dataSource = new HikariDataSource(config);
      Connection connection = dataSource.getConnection();
      connection.close();
      dataSource.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  // Connection From main -> Connection Util
  @Test
  void testConnectionUtil() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
  }
}

//TODO Note:
// HikariCP untuk mengatur koneksi pool nya
// https://github.com/brettwooldridge/HikariCP
