/**
 * Kelas ini digunakan untuk menguji transaksi database menggunakan commit dan rollback.
 *
 * **Kapan digunakan?**
 * - Ketika ingin memastikan bahwa serangkaian operasi SQL dieksekusi secara atomik.
 * - Jika terjadi kesalahan, perubahan dapat dibatalkan menggunakan rollback.
 * - Jika semua operasi berhasil, perubahan dapat disimpan menggunakan commit.
 *
 * **Initial State (IS):**
 * - Database dalam keadaan sebelum transaksi dimulai.
 * - Tidak ada perubahan yang dilakukan pada tabel `comments`.
 *
 * **Final State (FS):**
 * - Jika menggunakan `commit()`, data akan tersimpan di database.
 * - Jika menggunakan `rollback()`, data tidak akan tersimpan.
 */

package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.PreparedStatement; // Mengimpor PreparedStatement untuk eksekusi SQL dengan parameter
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL

public class TransactionTest {

  // Pengujian transaksi dengan commit (data akan tersimpan)
  @Test
  void testCommit() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false); // Menonaktifkan auto-commit agar transaksi bisa dikontrol  

    // SQL untuk memasukkan data ke dalam tabel 'comments'
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

    // Menambahkan 100 data ke dalam tabel
    for (int i = 0; i < 100; i++) {
      PreparedStatement prepareStatement = connection.prepareStatement(sql);
      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.executeUpdate();
      prepareStatement.close();
    }

    connection.commit(); // Menyimpan semua perubahan ke database  
    connection.close();
  }

  // Pengujian transaksi dengan rollback (data tidak akan tersimpan)
  @Test
  void testRollback() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false); // Menonaktifkan auto-commit agar transaksi bisa dikontrol  

    // SQL untuk memasukkan data ke dalam tabel 'comments'
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

    // Menambahkan 100 data ke dalam tabel
    for (int i = 0; i < 100; i++) {
      PreparedStatement prepareStatement = connection.prepareStatement(sql);
      prepareStatement.setString(1, "dyaz@mail.com");
      prepareStatement.setString(2, "Hello");

      prepareStatement.executeUpdate();
      prepareStatement.close();
    }

    connection.rollback(); // Membatalkan semua perubahan yang dilakukan dalam transaksi  
    connection.close();
  }
}