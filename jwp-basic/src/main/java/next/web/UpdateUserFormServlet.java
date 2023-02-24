package next.web;

import core.db.DataBase;
import next.dao.UserDao;
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
import java.sql.SQLException;

//@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet{
  private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Object value = session.getAttribute("user");
    log.info("value : {}",value);

    if (value == null) {
      Utils.getInstance().goLogin(resp);
    } else {
      User user = (User) value;
      req.setAttribute("user", user);

      RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
      rd.forward(req,resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = new User(req.getParameter("userId"),req.getParameter("password"),req.getParameter("name"),req.getParameter("email"));
    log.debug("param user : {}", user);
    DataBase.modifyUser(user);

    resp.sendRedirect("/user/list");
  }
}
