package next.controller.qna;

import core.exception.CannotDeleteException;
import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.QuestionDao;
import next.model.Result;
import next.service.QnaService;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiDeleteQuestionClass extends AbstractController {
  private QnaService qnaService = QnaService.getInstance();

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long questionId = Long.parseLong(request.getParameter("questionId"));

    if (!UserSessionUtils.isLogined(request.getSession())) {
      return jsonView().addObject("result", Result.fail("로그인이 필요한 작업입니다."));
    }

    try {
      qnaService.deleteQuestion(questionId, UserSessionUtils.getUserFromSession(request.getSession()));
    } catch (CannotDeleteException e) {
      e.printStackTrace();
      return jsonView().addObject("result",Result.fail(e.getMessage()));
    }

    return jsonView().addObject("result",Result.ok());
  }
}
