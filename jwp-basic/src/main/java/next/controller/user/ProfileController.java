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

public class ProfileController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    UserDao userDao = new UserDao();
    try {
      if (user == null || userDao.findByUserId(user.getUserId()) == null) {
        return jspView("redirect:/user/loginForm");
      }
    } catch (SQLException e) {
      log.error("Sql Exception",e);
    }

    return jspView("/user/profile.jsp").addObject("user",user);
  }
}
