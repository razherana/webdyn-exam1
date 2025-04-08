package controllers.depense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import helpers.DbHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Depense;

public class ListDepenseController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (Connection connection = DbHelper.getConnection(req)) {
      ArrayList<Depense> depenses = new Depense().addEagerLoad("prevision").all(connection);

      if (req.getParameter("filterLibelle") != null && !req.getParameter("filterLibelle").isBlank()) {
        final String lib = req.getParameter("filterLibelle").strip().toLowerCase();
        depenses = new ArrayList<>(
            depenses.stream().filter((e) -> e.getLibelle().toLowerCase().contains(lib)).toList());
      }

      if (req.getParameter("filterStartDate") != null && !req.getParameter("filterStartDate").isBlank()) {
        LocalDateTime date1 = LocalDateTime.parse(req.getParameter("filterStartDate").strip().toLowerCase());
        depenses = new ArrayList<>(
            depenses.stream().filter((e) -> e.getCreatedAt().isEqual(date1) || e.getCreatedAt().isAfter(date1))
                .toList());
      }

      if (req.getParameter("filterEndDate") != null && !req.getParameter("filterEndDate").isBlank()) {
        LocalDateTime date2 = LocalDateTime.parse(req.getParameter("filterEndDate").strip().toLowerCase());
        depenses = new ArrayList<>(
            depenses.stream().filter((e) -> e.getCreatedAt().isEqual(date2) || e.getCreatedAt().isBefore(date2))
                .toList());
      }

      depenses.sort((a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()));
      req.setAttribute("depenses", depenses);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.getRequestDispatcher("/WEB-INF/views/depense/list.jsp").forward(req, resp);
  }
}
