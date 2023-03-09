package core.mvc.view;

import core.mvc.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View{
  private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
  private final String viewName;

  public JspView(String viewName) throws Exception {
    if (viewName == null || viewName.trim().equals("")) {
      throw new NullPointerException("viewName is null. \n이동할 URL을 추가해주세요.");
    }
    this.viewName = viewName;
  }

  @Override
  public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
      response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
      return;
    }

    RequestDispatcher rd = request.getRequestDispatcher(viewName);
    rd.forward(request,response);
  }
}
