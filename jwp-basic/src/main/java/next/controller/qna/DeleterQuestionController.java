package next.controller.qna;

import core.exception.CannotDeleteException;
import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.service.QnaService;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleterQuestionController extends AbstractController {
  private QnaService qnaService = QnaService.getInstance();

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long questionId = Long.parseLong(request.getParameter("questionId"));

    if (!UserSessionUtils.isLogined(request.getSession())) {
      return jspView("/error")
              .addObject("errorMsg","로그인이 필요한 작업입니다.")
              .addObject("location","/user/loginForm");
    }

    try {
      qnaService.deleteQuestion(questionId, UserSessionUtils.getUserFromSession(request.getSession()));
    } catch (CannotDeleteException e) {
      e.printStackTrace();
      return jspView("/error")
              .addObject("errorMsg",e.getMessage());
    }

    return jspView("redirect:/");
  }
}
