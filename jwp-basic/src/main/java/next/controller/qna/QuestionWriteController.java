package next.controller.qna;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionWriteController implements Controller {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    return "redirect:/";
  }
}
