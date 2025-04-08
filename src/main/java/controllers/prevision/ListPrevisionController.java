package controllers.prevision;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import helpers.DbHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Prevision;

public class ListPrevisionController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (Connection connection = DbHelper.getConnection(req)) {
      ArrayList<Prevision> previsions = new Prevision().addEagerLoad("depenses").all(connection);

      if (req.getParameter("filterLibelle") != null && !req.getParameter("filterLibelle").isBlank()) {
        final String lib = req.getParameter("filterLibelle").strip().toLowerCase();
        previsions = new ArrayList<>(
            previsions.stream().filter((e) -> e.getLibelle().toLowerCase().contains(lib)).toList());
      }

      previsions.sort((a, b) -> b.getLibelle().toLowerCase().compareTo(a.getLibelle().toLowerCase()));

      req.setAttribute("previsions", previsions);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.getRequestDispatcher("/WEB-INF/views/prevision/list.jsp").forward(req, resp);
  }
}
