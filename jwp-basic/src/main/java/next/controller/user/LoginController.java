package next.controller.user;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(LoginController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    log.info("input : {}/{}",request.getParameter("userId"),request.getParameter("password"));

    User user = null;
    UserDao userDao = new UserDao();

    try {
      user = userDao.findByUserId(request.getParameter("userId"));
    } catch (SQLException e) {
      log.error("Sql Exception",e);
    }

    if (user == null) {
      log.error("User is null");
      return jspView("/user/login.jsp").addObject("error","error");
    }

    if (user.getPassword().equals(request.getParameter("password"))) {
      HttpSession session = request.getSession();
      session.setAttribute("user", user);

      log.info("login user : {}", user);
      log.info("session attribute : {}",session.getAttribute("user"));

      return jspView("redirect:/");
    } else {
      log.error("matching error");
      return jspView("/user/login.jsp").addObject("error","error");
    }
  }
}
