package helpers;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import mg.razherana.database.DatabaseConnection;

public class DbHelper {
  public static Connection getConnection(HttpServletRequest request) {
    Connection res = null;
    try {
      res = DatabaseConnection.fromDotEnv(request.getServletContext().getRealPath("WEB-INF/database.xml"))
          .getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }
}
