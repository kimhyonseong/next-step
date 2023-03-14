package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;
import next.utils.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());

    if (sessionUser == null) {
      return jspView("/error")
              .addObject("errorMsg","로그인이 필요한 작업입니다.")
              .addObject("location","/user/login");
    }

    Answer answer = new Answer(
            request.getParameter("writer"),
            request.getParameter(sessionUser.getUserId()),
            request.getParameter("contents"),
            Long.parseLong(request.getParameter("questionId")));
    log.debug("answer : {}",answer);

    AnswerDao answerDao = new AnswerDao();
    Answer insertAnswer = answerDao.insert(answer);
    log.debug("insertAnswer : {}",insertAnswer);

    QuestionDao questionDao = new QuestionDao();
    Question question = questionDao.updateCountOfAnswerByQuestionId(Long.parseLong(request.getParameter("questionId")));

    return jsonView()
            .addObject("answer",insertAnswer)
            .addObject("answerCont",question.getCountOfAnswer());
  }
}
