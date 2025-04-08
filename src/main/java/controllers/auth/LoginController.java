package controllers.auth;

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
import models.User;

public class LoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if (username == null || password == null) {
      resp.sendRedirect(req.getContextPath() + "/login?error=Invalid credentials");
      return;
    }

    try (Connection connection = DbHelper.getConnection(req)) {
      ArrayList<User> users = new User().where(WhereBuilder.create().add("username", username, "=").then(), connection);
      if (users.isEmpty()) {
        resp.sendRedirect(req.getContextPath() + "/login?error=Invalid credentials");
        return;
      }
      User user = users.get(0);
      if (!user.getPassword().equals(password)) {
        resp.sendRedirect(req.getContextPath() + "/login?error=Invalid credentials");
        return;
      }
      req.getSession().setAttribute("user", user);

      if (req.getParameter("to") == null)
        resp.sendRedirect(req.getContextPath() + "/home");
      else
        resp.sendRedirect(req.getParameter("to"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
