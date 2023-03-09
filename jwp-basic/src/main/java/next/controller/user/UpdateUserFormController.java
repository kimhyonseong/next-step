package next.controller.user;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.model.User;
import next.utils.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserFormController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(UpdateUserFormController.class);
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = UserSessionUtils.getUserFromSession(request.getSession());

    if (user == null) {
      return jspView("redirect:/user/login");
    }

    return jspView("/user/update.jsp").addObject("user",user);
  }
}
