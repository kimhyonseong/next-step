package next.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(HomeServlet.class);

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.info("In to the home.jsp");
    RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
    rd.forward(request,response);
  }
}
