package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import com.zaxxer.hikari.HikariConfig; // Mengimpor HikariConfig untuk konfigurasi koneksi database
import com.zaxxer.hikari.HikariDataSource; // Mengimpor HikariDataSource untuk manajemen koneksi database
import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL

public class ConnectionPoolTest {

  // Pengujian koneksi menggunakan HikariCP
  @Test
  void testHikariCP() {
    HikariConfig config = new HikariConfig();

    // Konfigurasi koneksi database
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
    config.setUsername("root");
    config.setPassword("");

    // Konfigurasi connection pool
    config.setMaximumPoolSize(10); // Maksimum jumlah koneksi dalam pool
    config.setMinimumIdle(5); // Minimum jumlah koneksi yang tetap aktif
    config.setIdleTimeout(60_000); // Waktu tunggu sebelum koneksi idle ditutup (dalam milidetik)
    config.setMaxLifetime(10 * 60_000); // Maksimum umur koneksi sebelum di-refresh (dalam milidetik)

    try {
      // Membuat instance HikariDataSource dengan konfigurasi yang telah ditentukan
      HikariDataSource dataSource = new HikariDataSource(config);

      // Mengambil koneksi dari pool dan langsung menutupnya
      Connection connection = dataSource.getConnection();
      connection.close();
      dataSource.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  // Pengujian koneksi menggunakan ConnectionUtil
  @Test
  void testConnectionUtil() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
  }
}

//TODO Note:
// HikariCP untuk mengatur koneksi pool nya
// https://github.com/brettwooldridge/HikariCP
