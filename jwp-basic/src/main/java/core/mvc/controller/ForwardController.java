package core.mvc.controller;

import core.mvc.ModelAndView;
import core.mvc.view.JspView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
  private String forwardUrl;

  public ForwardController(String forwardUrl) {
    this.forwardUrl = forwardUrl;

    if (forwardUrl == null) {
      throw new NullPointerException("forwardUrl is null, 이동할 URL을 입력하세요");
    }
  }
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView(new JspView(forwardUrl));
  }
}
