package next.controller.user;

import core.db.DataBase;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = new User(request.getParameter("userId"),request.getParameter("password"),
            request.getParameter("name"),request.getParameter("email"));
    log.debug("param user : {}", user);
    DataBase.modifyUser(user);

    return new JspView("redirect:/");
  }
}
