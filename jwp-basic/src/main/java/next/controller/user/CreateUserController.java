package next.controller.user;

import core.db.DataBase;
import core.exception.DataAccessException;
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

public class CreateUserController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = new User(request.getParameter("userId"), request.getParameter("password"),
            request.getParameter("name"), request.getParameter("email"));

    UserDao userDao = new UserDao();
    try {
      userDao.insert(user);
    } catch (DataAccessException e) {
      log.error("Sql exception",e);
    }
    DataBase.addUser(user);
    return jspView("redirect:/");
  }
}
