package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionShowController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(QuestionShowController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    log.info("questionId = {}",request.getParameter("questionId"));

    long questionId = Long.parseLong(request.getParameter("questionId"));

    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    return jspView("/qna/show.jsp")
            .addObject("question",questionDao.findById(questionId))
            .addObject("answers",answerDao.findAllByQuestionId(questionId));
  }
}
