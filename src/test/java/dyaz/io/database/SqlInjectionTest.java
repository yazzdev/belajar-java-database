package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.ResultSet; // Mengimpor ResultSet untuk menangani hasil query SQL
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL
import java.sql.Statement; // Mengimpor Statement untuk eksekusi SQL tanpa parameter

public class SqlInjectionTest {

  // Pengujian SQL Injection menggunakan Statement (tidak aman)
  @Test
  void testSqlInjection() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // Simulasi input pengguna yang berpotensi menyebabkan SQL Injection
    String username = "admin'; #";
    String password = "admin123";

    // SQL yang tidak aman karena langsung menggabungkan input pengguna
    String sql = "SELECT * FROM admin WHERE username ='" + username + "' AND password ='" + password + "' ";
    ResultSet resultSet = statement.executeQuery(sql);

    // Mengecek apakah ada hasil dari query
    if (resultSet.next()) {
      // Jika ada hasil, login berhasil
      System.out.println("Login Success!! -> Hello " + resultSet.getString("username") + "!!");
    } else {
      // Jika tidak ada hasil, login gagal
      System.out.println("Login Fail!!");
    }

    // Menutup resultSet, statement, dan koneksi
    resultSet.close();
    statement.close();
    connection.close();
  }
}