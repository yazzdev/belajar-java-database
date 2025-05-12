package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.Connection; // Mengimpor kelas Connection untuk mengelola koneksi database
import java.sql.ResultSet; // Mengimpor ResultSet untuk menangani hasil query SQL
import java.sql.SQLException; // Mengimpor SQLException untuk menangani kesalahan SQL
import java.sql.Statement; // Mengimpor Statement untuk eksekusi SQL tanpa parameter

public class StatementTest {

  // Pengujian pembuatan Statement
  @Test
  void tesetCreateStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // Menutup statement dan koneksi
    statement.close();
    connection.close();
  }

  // Pengujian eksekusi perintah INSERT menggunakan Statement
  @Test
  void tesetExecuteUpdate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // SQL untuk memasukkan data ke dalam tabel 'customers'
    String sql = """  
            INSERT INTO customers(id, name, email) VALUES
            ('123', 'Dyaz', 'dyaz@email.com'),
            ('124', 'Amrullah', 'amrullah@email.com'),
            ('125', 'Nikmah', 'nikmah@email.com'),
            ('126', 'Ramadhani', 'ramadhani@email.com'),
            ('127', 'Nira', 'nira@email.com')
            """;
    int update = statement.executeUpdate(sql); // Menjalankan perintah SQL
    System.out.println(update); // Menampilkan jumlah baris yang terpengaruh

    // Menutup statement dan koneksi
    statement.close();
    connection.close();
  }

  // Pengujian eksekusi perintah DELETE menggunakan Statement
  @Test
  void tesetExecuteDelete() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // SQL untuk menghapus semua data dari tabel 'customers'
    String sql = """  
            DELETE FROM customers;
            """;
    int update = statement.executeUpdate(sql); // Menjalankan perintah SQL
    System.out.println(update); // Menampilkan jumlah baris yang terpengaruh

    // Menutup statement dan koneksi
    statement.close();
    connection.close();
  }

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

    // Menutup resultSet, statement, dan koneksi
    resultSet.close();
    statement.close();
    connection.close();
  }
}