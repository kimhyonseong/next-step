package next.controller.qna;

import core.mvc.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionShowController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(QuestionShowController.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    log.info("questionId = {}",request.getParameter("questionId"));
    return "/qna/show.jsp";
  }
}
