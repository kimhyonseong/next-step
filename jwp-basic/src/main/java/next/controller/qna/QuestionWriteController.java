package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionWriteController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 로직
    return jspView("redirect:/");
  }
}
