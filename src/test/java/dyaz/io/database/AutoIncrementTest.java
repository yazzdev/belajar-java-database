package dyaz.io.database;

import org.junit.jupiter.api.Test;
import java.sql.*;

public class AutoIncrementTest {
  @Test
  void testAutoIncrement() throws SQLException {
    // Membuka koneksi ke database menggunakan utilitas koneksi
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    // Menyiapkan pernyataan SQL untuk memasukkan data ke dalam tabel 'comments'
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    // Menetapkan nilai parameter untuk email dan komentar
    prepareStatement.setString(1, "dyaz@mail.com");
    prepareStatement.setString(2, "Hello");

    // Menjalankan pernyataan SQL untuk memasukkan data ke dalam database
    prepareStatement.executeUpdate();

    // Mengambil kunci yang dihasilkan secara otomatis (auto-increment ID)
    ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
    if (generatedKeys.next()) {
      // Menampilkan ID komentar yang baru dibuat
      System.out.println("Id Comment: " + generatedKeys.getInt(1));
    }

    // Menutup objek ResultSet, PreparedStatement, dan koneksi untuk menghindari kebocoran sumber daya
    generatedKeys.close();
    prepareStatement.close();
    connection.close();
  }
}