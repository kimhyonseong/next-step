package next.controller.user;

import core.mvc.Controller;
import next.model.User;
import next.utils.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserFormController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(UpdateUserFormController.class);
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = UserSessionUtils.getUserFromSession(request.getSession());

    if (user == null) {
      return "redirect:/user/login";
    }
    request.setAttribute("user",user);

    return "/user/update.jsp";
  }
}
