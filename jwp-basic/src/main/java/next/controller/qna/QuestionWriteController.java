package next.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionWriteController implements Controller {
  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 로직
    return new JspView("redirect:/");
  }
}
