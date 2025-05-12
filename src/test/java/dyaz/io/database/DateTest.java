package dyaz.io.database; // Mendefinisikan paket tempat kelas ini berada

import org.junit.jupiter.api.Test; // Mengimpor anotasi Test dari JUnit untuk pengujian unit
import java.sql.*; // Mengimpor kelas-kelas SQL yang diperlukan untuk bekerja dengan database

public class DateTest {

  // Pengujian untuk menyisipkan data tanggal, waktu, dan timestamp ke dalam database
  @Test
  void testDate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    // SQL untuk memasukkan data ke dalam tabel 'sample_time'
    String sql = """  
            INSERT INTO sample_time (sample_date, sample_time, sample_timestamp)
            VALUES (?, ?, ?)
            """;
    PreparedStatement prepareStatement = connection.prepareStatement(sql);

    // Menetapkan nilai parameter dengan waktu saat ini
    prepareStatement.setDate(1, new Date(System.currentTimeMillis())); // Menyisipkan tanggal
    prepareStatement.setTime(2, new Time(System.currentTimeMillis())); // Menyisipkan waktu
    prepareStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Menyisipkan timestamp

    // Menjalankan perintah SQL untuk memasukkan data
    prepareStatement.executeUpdate();

    // Menutup statement dan koneksi
    prepareStatement.close();
    connection.close();
  }

  // Pengujian untuk mengambil dan membaca data tanggal, waktu, dan timestamp dari database
  @Test
  void testDataQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();

    // SQL untuk mengambil semua data dari tabel 'sample_time'
    String sql = "SELECT * FROM sample_time";
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    ResultSet resultSet = prepareStatement.executeQuery();

    // Iterasi hasil query untuk membaca setiap baris
    while (resultSet.next()) {
      Date sampleDate = resultSet.getDate("sample_date"); // Mengambil nilai kolom 'sample_date'
      Time sampleTime = resultSet.getTime("sample_time"); // Mengambil nilai kolom 'sample_time'
      Timestamp sampleTimestamp = resultSet.getTimestamp("sample_timestamp"); // Mengambil nilai kolom 'sample_timestamp'

      // Menampilkan hasil dalam format yang mudah dibaca
      System.out.println("Date      : " + sampleDate);
      System.out.println("Time      : " + sampleTime);
      System.out.println("Timestamp : " + sampleTimestamp);
    }

    // Menutup resultSet, statement, dan koneksi
    resultSet.close();
    prepareStatement.close();
    connection.close();
  }
}