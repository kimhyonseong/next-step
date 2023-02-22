package next.web;

import core.db.DataBase;
import next.model.User;
import next.utils.Utils;
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

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(UserProfileServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    request.setAttribute("user",user);

    if (user == null || DataBase.findUserById(user.getUserId()) == null) {
      log.error("user is NULL");
      Utils.getInstance().goLogin(response);
      return;
    }

    RequestDispatcher rd = request.getRequestDispatcher("/user/profile.jsp");
    rd.forward(request,response);
  }
}
