package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.PreparedStatement; // Mengimpor PreparedStatement untuk eksekusi SQL dengan parameter
import java.sql.ResultSet; // Mengimpor ResultSet untuk menangani hasil query SQL
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL

// Solusi untuk mencegah SQL Injection menggunakan PreparedStatement
public class PrepareStatementTest {

  @Test
  void testPrepareStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    // Simulasi input pengguna
    String username = "admin'; #";
    String password = "admin123";

    // SQL yang aman menggunakan parameterized query
    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

    // Menggunakan PreparedStatement untuk menghindari SQL Injection
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();

    // Mengecek apakah ada hasil dari query
    if (resultSet.next()) {
      // Jika ada hasil, login berhasil
      System.out.println("Login Success: Hello " + resultSet.getString("username") + "!!");
    } else {
      // Jika tidak ada hasil, login gagal
      System.out.println("Username or Password Incorrect!!");
    }

    // Menutup preparedStatement dan koneksi
    preparedStatement.close();
    connection.close();
  }
}

// Prepare Statement should be used when there is user input
// If there is no user input, You are able to Statement as usual
// But, You can use this for all conditions
