package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import next.model.User;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApiDeleteQuestionClass extends AbstractController {
  QuestionDao questionDao = new QuestionDao();
  AnswerDao answerDao = new AnswerDao();

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long questionId = Long.parseLong(request.getParameter("questionId"));
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());
    Question question = questionDao.findById(questionId);
    List<Answer> answers = answerDao.findAllByQuestionId(questionId);

    if (sessionUser == null) {
      return jsonView().addObject("result", Result.fail("로그인이 필요한 작업입니다."));
    }
    if (question == null)
      return jsonView().addObject("result", Result.fail("해당하는 게시물이 없습니다."));
    if (!question.getUserId().equals(sessionUser.getUserId()))
      return jsonView().addObject("result", Result.fail("소유주만 삭제할 수 있습니다."));

    for (Answer answer : answers) {
      if (!answer.getUserId().equals(question.getUserId()))
        return jsonView().addObject("result", Result.fail("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다."));
    }

    questionDao.delete(questionId);

    return jsonView().addObject("result",Result.ok());
  }
}
