/**
 * Kelas ini digunakan untuk menguji metadata database, termasuk informasi tentang tabel dan parameter SQL.
 *
 * **Kapan digunakan?**
 * - Ketika ingin mendapatkan informasi tentang database, seperti nama produk, versi, dan daftar tabel.
 * - Saat ingin mengetahui jumlah parameter dalam query SQL.
 * - Saat ingin mengetahui metadata dari hasil query (ResultSet).
 *
 * **Initial State (IS):**
 * - Database dalam keadaan normal tanpa perubahan.
 * - Metadata tersedia untuk diakses.
 *
 * **Final State (FS):**
 * - Tidak ada perubahan pada database, hanya membaca metadata.
 */

package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.*; // Mengimpor kelas-kelas SQL yang diperlukan untuk bekerja dengan database

public class MetaDataTest {

  // Pengujian metadata database
  @Test
  void testMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    DatabaseMetaData metaData = connection.getMetaData();

    // Menampilkan informasi tentang database
    System.out.println(metaData.getDatabaseProductName());
    System.out.println(metaData.getDatabaseProductVersion());

    // Mengambil daftar tabel dalam database
    ResultSet resultSet = metaData.getTables("belajar_java_database", null, null, null);

    while (resultSet.next()) {
      String tableName = resultSet.getString("TABLE_NAME");
      System.out.println("Table : " + tableName);
    }

    connection.close();
  }

  // Pengujian metadata parameter dalam query SQL
  @Test
  void testParameterMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments (email, comment) VALUES (?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

    // Menampilkan jumlah parameter dalam query SQL
    System.out.println(parameterMetaData.getParameterCount());
    // System.out.println(parameterMetaData.getParameterType(1)); // ERROR

    preparedStatement.close();
    connection.close();
  }

  // Pengujian metadata hasil query (ResultSet)
  @Test
  void testResultSetMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

    ResultSetMetaData metaData = resultSet.getMetaData();

    // Iterasi untuk mendapatkan informasi tentang setiap kolom dalam hasil query
    for (int i = 1; i <= metaData.getColumnCount(); i++) {
      System.out.println();
      System.out.println("Name      : " + metaData.getColumnName(i));
      System.out.println("Type      : " + metaData.getColumnType(i));
      System.out.println("Type Name : " + metaData.getColumnTypeName(i));

      // Menampilkan pesan jika tipe data adalah INTEGER
      if (metaData.getColumnType(i) == Types.INTEGER) {
        System.out.println("INI INTEGER");
      }
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}