package controllers.nochanges;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import helpers.DbHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Depense;
import models.Prevision;

public class HomeController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try (Connection connection = DbHelper.getConnection(req)) {
      ArrayList<Prevision> previsions = new Prevision().addEagerLoad("depenses").all(connection);

      HashMap<Integer, Double> previsionDepenses = new HashMap<>();
      for (Prevision prevision : previsions) {
        int id = prevision.getId();
        double sum = 0;
        for (Depense depense : prevision.getDepenses(null))
          sum += depense.getMontant();
        previsionDepenses.put(id, sum);
      }

      req.setAttribute("previsions", previsions);
      req.setAttribute("previsionDepenses", previsionDepenses);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.getRequestDispatcher("/WEB-INF/views/static/home.jsp").forward(req, resp);
  }
}
