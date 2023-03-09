package next.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionShowController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(QuestionShowController.class);

  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    log.info("questionId = {}",request.getParameter("questionId"));

    long questionId = Long.parseLong(request.getParameter("questionId"));

    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    request.setAttribute("question",questionDao.findById(questionId));
    request.setAttribute("answers",answerDao.findAllByQuestionId(questionId));

    log.info("findAllByQuestionId = {}",answerDao.findAllByQuestionId(questionId));
    return new JspView("/qna/show.jsp");
  }
}
