package next.controller.user;

import core.db.DataBase;
import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (!UserSessionUtils.isLogined(request.getSession())) {
      return jspView("redirect:/user/login");
    }

    request.setAttribute("users", DataBase.findAll());
    return jspView("/user/list.jsp");
  }
}
