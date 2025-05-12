package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import com.zaxxer.hikari.HikariConfig; // Mengimpor HikariConfig untuk konfigurasi koneksi database
import com.zaxxer.hikari.HikariDataSource; // Mengimpor HikariDataSource untuk manajemen koneksi database

// Kelas utilitas untuk mengelola koneksi database menggunakan HikariCP
public class ConnectionUtil {

  // Variabel statis untuk menyimpan instance HikariDataSource
  private static HikariDataSource dataSource;

  // Blok statis untuk menginisialisasi konfigurasi koneksi database
  static {
    HikariConfig config = new HikariConfig();

    // Menentukan driver yang digunakan
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");

    // Menentukan URL JDBC untuk koneksi ke database
    config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
    config.setUsername("root");
    config.setPassword("");

    // Konfigurasi connection pool
    config.setMaximumPoolSize(10); // Maksimum jumlah koneksi dalam pool
    config.setMinimumIdle(5); // Minimum jumlah koneksi yang tetap aktif
    config.setIdleTimeout(60_000); // Waktu tunggu sebelum koneksi idle ditutup (dalam milidetik)
    config.setMaxLifetime(10 * 60_000); // Maksimum umur koneksi sebelum di-refresh (dalam milidetik)

    // Membuat instance HikariDataSource dengan konfigurasi yang telah ditentukan
    dataSource = new HikariDataSource(config);
  }

  // Metode untuk mendapatkan instance HikariDataSource
  public static HikariDataSource getDataSource() {
    return dataSource;
  }
}