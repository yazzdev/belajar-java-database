package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.PreparedStatement; // Mengimpor PreparedStatement untuk eksekusi SQL dengan parameter
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL
import java.sql.Statement; // Mengimpor Statement untuk eksekusi SQL tanpa parameter

public class BatchTest {

  // Pengujian batch menggunakan Statement
  @Test
  void testBatchStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // SQL untuk memasukkan data ke dalam tabel 'comments'
    String sql = "INSERT INTO comments(email, comment) VALUES ('yaz@mail.com', 'Hi')";

    // Menambahkan perintah SQL ke dalam batch sebanyak 1000 kali
    for (int i = 0; i < 1000; i++) {
      statement.addBatch(sql);
    }

    // Menjalankan semua perintah dalam batch sekaligus
    statement.executeBatch();

    // Menutup statement dan koneksi
    statement.close();
    connection.close();
  }

  // Pengujian batch menggunakan PreparedStatement
  @Test
  void testBatchPreparedStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    // SQL dengan parameter untuk memasukkan data ke dalam tabel 'comments'
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);

    // Menambahkan perintah SQL ke dalam batch sebanyak 1000 kali dengan parameter
    for (int i = 0; i < 1000; i++) {
      prepareStatement.clearParameters(); // Membersihkan parameter sebelum menetapkan nilai baru

      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.addBatch(); // Menambahkan ke batch
    }

    // Menjalankan semua perintah dalam batch sekaligus
    prepareStatement.executeBatch();

    // Menutup statement dan koneksi
    prepareStatement.close();
    connection.close();
  }
}