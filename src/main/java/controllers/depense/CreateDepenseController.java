package controllers.depense;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import helpers.DbHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Depense;
import models.Prevision;

public class CreateDepenseController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (Connection connection = DbHelper.getConnection(req)) {
      ArrayList<Prevision> previsions = new Prevision().all(connection);
      req.setAttribute("previsions", previsions);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.getRequestDispatcher("/WEB-INF/views/depense/create.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String libelle = req.getParameter("libelle");
    String montant = req.getParameter("montant");
    String previsionStringId = req.getParameter("prevision");
    String createdAtString = req.getParameter("created_at");
    LocalDateTime createdAt = null;

    double montantValue = 0;
    int previsionId = 0;

    if (libelle == null || montant == null || previsionStringId == null) {
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=Prevision ou Libelle or Montant manquant");
      return;
    }

    System.out.println("createdAtString = " + createdAtString);

    // Check the montant
    try {
      montantValue = Double.parseDouble(montant);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=Montant invalide");
      return;
    }

    // Check the montant
    try {
      previsionId = Integer.parseInt(previsionStringId);
    } catch (NumberFormatException e) {
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=Prevision invalide");
      return;
    }

    // Check the date
    try {
      if (createdAtString == null || createdAtString.isBlank())
        createdAt = LocalDateTime.now();
      else
        createdAt = LocalDateTime.parse(createdAtString);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=Le format de la date est invalide");
      return;
    }

    if (montantValue <= 0) {
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=Le montant doit etre superieur a 0");
      return;
    }

    try (Connection connection = DbHelper.getConnection(req)) {
      // Check if the prevision exist

      Prevision prevision = new Prevision().find(previsionId, connection);
      if (prevision == null) {
        resp.sendRedirect(req.getContextPath() + "/create.depense?error=La prevision n'existe pas");
        return;
      }

      double totaldepense = prevision.getTotalDepenses(connection);

      if (totaldepense + montantValue > prevision.getMontant()) {
        resp.sendRedirect(
            req.getContextPath() + "/create.depense?error=Vous avez utiliser plus que la prevision. Prevision = "
                + prevision.getMontant() + ", Utilisee = " + totaldepense + ", Surplus = "
                + (prevision.getMontant() - (totaldepense + montantValue)));
        return;
      }

      Depense depense = new Depense();
      depense.setPrevisionid(previsionId);
      depense.setMontant(montantValue);
      depense.setLibelle(libelle);
      depense.setCreatedAt(createdAt);

      depense.save(connection);

    } catch (SQLException e) {
      resp.sendRedirect(req.getContextPath() + "/create.depense?error=" + e.getMessage());
    }

    resp.sendRedirect(req.getContextPath() + "/create.depense");
  }
}
