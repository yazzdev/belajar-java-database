package dyaz.io.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.SQLException;

//TODO 1

public class DriverTest {

  @Test
  void testRegister() {

    try {
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }

  }
}
