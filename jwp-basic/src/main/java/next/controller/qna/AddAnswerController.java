package next.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Answer answer = new Answer(
            request.getParameter("writer"),
            request.getParameter("contents"),
            Long.parseLong(request.getParameter("questionId")));
    log.debug("answer : {}",answer);

    AnswerDao answerDao = new AnswerDao();
    Answer insertAnswer = answerDao.insert(answer);
    log.debug("insertAnswer : {}",insertAnswer);

    request.setAttribute("answer",insertAnswer);
    return new JsonView();
  }
}