package core.mvc;

import core.mvc.controller.Controller;
import core.mvc.view.View;
import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/",loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
  private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

  private LegacyRequestMapping lrm;
  private AnnotationHandlerMapping ahm;

  @Override
  public void init() throws ServletException {
    lrm = new LegacyRequestMapping();
    lrm.initMapping();
    ahm = new AnnotationHandlerMapping("next.controller");
    ahm.initialize();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    log.debug("Method : {},Request URI : {}",request.getMethod(),requestUri);

    try {
      Controller controller = lrm.findController(requestUri);

      if (controller != null) {
        render(request, response, controller.execute(request, response));
      } else {
        HandlerExecution he = ahm.getHandler(request);

        if (he == null) {
          throw new ServletException("유효하지 않은 요청입니다.");
        }
        render(request, response, he.handle(request,response));
      }
    } catch (Exception e) {
      log.error("Exception",e);
    }
  }

  private void render(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) throws Exception {
    View view = mav.getView();
    view.render(mav.getModel(), request, response);
  }
}
