package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Assertions; // Mengimpor Assertions untuk melakukan validasi dalam pengujian
import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit

import java.sql.Driver; // Mengimpor kelas Driver untuk mengelola koneksi database
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL

// Kelas untuk menguji pendaftaran driver MySQL
public class DriverTest {

  @Test
  void testRegister() {
    try {
      // Membuat instance driver MySQL
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
    } catch (SQLException exception) {
      // Jika terjadi kesalahan, gagal dalam pengujian dengan menampilkan exception
      Assertions.fail(exception);
    }
  }
}
