package controllers.auth;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {
  private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.getSession().invalidate();
    req.getSession().setAttribute("user", null);
    resp.sendRedirect(req.getContextPath() + "/login");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    logout(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    logout(req, resp);
  }
}
