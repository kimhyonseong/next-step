package core.mvc.controller;

import core.mvc.ModelAndView;
import core.mvc.view.JsonView;
import core.mvc.view.JspView;

public abstract class AbstractController implements Controller{
  protected ModelAndView jspView(String forwardUrl) {
    return new ModelAndView(new JspView(forwardUrl));
  }

  protected ModelAndView jsonView() {
    return new ModelAndView(new JsonView());
  }
}
