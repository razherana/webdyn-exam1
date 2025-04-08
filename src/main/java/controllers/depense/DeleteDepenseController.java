package controllers.depense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import helpers.DbHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Depense;

public class DeleteDepenseController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    int idDepense = 0;

    if (id == null) {
      resp.sendRedirect(req.getContextPath() + "/list.depense?error=La depense n'existe pas (null)");
      return;
    }

    try {
      idDepense = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/list.depense?error=La depense n'existe pas (not int)");
      return;
    }

    try (Connection connection = DbHelper.getConnection(req)) {
      Depense depense = new Depense().find(idDepense, connection);

      if (depense == null) {
        resp.sendRedirect(req.getContextPath() + "/list.depense?error=La depense n'existe pas (find)");
        return;
      }

      depense.delete(connection);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    resp.sendRedirect(req.getContextPath() + "/list.depense");
  }
}
