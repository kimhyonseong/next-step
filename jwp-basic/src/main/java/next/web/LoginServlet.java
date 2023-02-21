package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Object user = session.getAttribute("user");

    if (user == null) {
      RequestDispatcher rd = request.getRequestDispatcher("/user/login.jsp");
      rd.forward(request,response);
    } else {
      response.sendRedirect("/");
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    User user = DataBase.findUserById(request.getParameter("userId"));
    HttpSession session = request.getSession();
    session.setAttribute("user",user);
    response.sendRedirect("/index.html");
    log.info("login user : {}",user);
  }
}
