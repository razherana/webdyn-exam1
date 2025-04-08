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
import mg.razherana.lorm.queries.WhereBuilder;
import models.Prevision;

public class CreatePrevisionController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/prevision/create.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String libelle = req.getParameter("libelle");
    String montant = req.getParameter("montant");
    double montantValue = 0;

    if (libelle == null || montant == null) {
      resp.sendRedirect(req.getContextPath() + "/create.prevision?error=Libelle or Montant manquant");
      return;
    }

    // Check the montant
    try {
      montantValue = Double.parseDouble(montant);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/create.prevision?error=Montant invalide");
      return;
    }

    if (montantValue <= 0) {
      resp.sendRedirect(req.getContextPath() + "/create.prevision?error=Le montant doit etre superieur a 0");
      return;
    }

    try (Connection connection = DbHelper.getConnection(req)) {
      // Check if libelle exists

      ArrayList<Prevision> previsions = new Prevision().where(WhereBuilder.create().add("libelle", libelle, "=").then(),
          connection);

      if (previsions.size() > 0) {
        resp.sendRedirect(req.getContextPath() + "/create.prevision?error=Le libelle existe deja");
        return;
      }

      Prevision prevision = new Prevision();

      prevision.setLibelle(libelle);
      prevision.setMontant(montantValue);
      prevision.save(connection);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    resp.sendRedirect(req.getContextPath() + "/create.prevision");
  }
}
