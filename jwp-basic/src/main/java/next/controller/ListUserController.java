package next.controller;

import core.db.DataBase;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller{
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (!UserSessionUtils.isLogined(request.getSession())) {
      return "redirect:/user/login";
    }

    request.setAttribute("users", DataBase.findAll());
    return "/user/list";
  }
}
