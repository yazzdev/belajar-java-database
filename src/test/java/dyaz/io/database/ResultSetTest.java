package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.ResultSet; // Mengimpor ResultSet untuk menangani hasil query SQL
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL
import java.sql.Statement; // Mengimpor Statement untuk eksekusi SQL tanpa parameter

public class ResultSetTest {

  // Pengujian eksekusi perintah SELECT menggunakan Statement
  @Test
  void testExecuteQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // SQL untuk mengambil semua data dari tabel 'customers'
    String sql = """  
            SELECT * FROM customers;
            """;
    ResultSet resultSet = statement.executeQuery(sql); // Menjalankan perintah SQL

    // Iterasi hasil query untuk membaca setiap baris
    while (resultSet.next()) {
      String id = resultSet.getString("id");
      String name = resultSet.getString("name");
      String email = resultSet.getString("email");

      // Menampilkan hasil dalam format "id, name, email"
      System.out.println(String.join(", ", id, name, email));
    }

    // Menutup resultSet, statement, dan koneksi
    resultSet.close();
    statement.close();
    connection.close();
  }
}