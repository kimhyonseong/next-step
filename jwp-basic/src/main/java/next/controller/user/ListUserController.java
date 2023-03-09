package next.controller.user;

import core.db.DataBase;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (!UserSessionUtils.isLogined(request.getSession())) {
      return new JspView("redirect:/user/login");
    }

    request.setAttribute("users", DataBase.findAll());
    return new JspView("/user/list.jsp");
  }
}
