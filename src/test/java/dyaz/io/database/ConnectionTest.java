package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Assertions; // Mengimpor Assertions untuk validasi dalam pengujian
import org.junit.jupiter.api.BeforeAll; // Mengimpor anotasi BeforeAll untuk eksekusi sebelum semua pengujian
import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit

import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.Driver; // Mengimpor kelas Driver untuk mengelola driver database
import java.sql.DriverManager; // Mengimpor DriverManager untuk mengelola koneksi database
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL

public class ConnectionTest {

  // Metode yang dijalankan sebelum semua pengujian untuk mendaftarkan driver MySQL
  @BeforeAll
  static void beforeAll() {
    try {
      // Membuat instance driver MySQL
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();

      // Mendaftarkan driver ke DriverManager
      DriverManager.registerDriver(mysqlDriver);
    } catch (SQLException e) {
      // Jika terjadi kesalahan, lemparkan RuntimeException
      throw new RuntimeException(e);
    }
  }

  // Metode pengujian untuk menguji koneksi ke database
  @Test
  void testConnection() {
    // URL JDBC untuk koneksi ke database MySQL
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";

    try {
      // Membuat koneksi ke database
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

      // Menampilkan pesan jika koneksi berhasil
      System.out.println("Connection Success!!");
    } catch (SQLException e) {
      // Jika terjadi kesalahan, gagal dalam pengujian dengan menampilkan exception
      Assertions.fail(e);
    }
  }

  // Metode pengujian untuk menguji koneksi dan penutupan koneksi secara otomatis
  @Test
  void testConnectionClose() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";

    // Menggunakan try-with-resources untuk memastikan koneksi ditutup secara otomatis
    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
      System.out.println("Connection Success!!");
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }
}