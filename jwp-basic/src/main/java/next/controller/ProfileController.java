package next.controller;

import core.db.DataBase;
import next.model.User;
import next.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileController implements Controller{

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    request.setAttribute("user",user);

    if (user == null || DataBase.findUserById(user.getUserId()) == null) {
      return "redirect:/user/loginForm";
    }
    return "/user/profile.jsp";
  }
}
