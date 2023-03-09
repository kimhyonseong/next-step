package core.mvc;

import core.mvc.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "dispatcher", urlPatterns = "/",loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
  private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

  private RequestMapping rm;

  @Override
  public void init() throws ServletException {
    rm = new RequestMapping();
    rm.initMapping();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    log.debug("Method : {},Request URI : {}",request.getMethod(),requestUri);

    Controller controller = rm.findController(requestUri);
    try {
      View view = controller.execute(request,response);
      view.render(request,response);
    } catch (Exception e) {
      log.error("Exception",e);
    }
  }
}
