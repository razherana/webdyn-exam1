package filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpSession session = request.getSession();

    Object user = session.getAttribute("user");

    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/login?to=" + request.getRequestURI());
      return;
    }

    chain.doFilter(request, response);
  }
}
