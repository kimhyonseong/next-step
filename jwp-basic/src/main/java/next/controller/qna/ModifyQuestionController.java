package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyQuestionController extends AbstractController {
  QuestionDao questionDao = new QuestionDao();

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long questionId = Long.parseLong(request.getParameter("questionId"));
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());
    Question question = questionDao.findById(questionId);

    if (sessionUser == null) {
      return jspView("/error")
              .addObject("errorMsg","로그인이 필요한 작업입니다.")
              .addObject("location","/user/loginForm");
    }
    if (question == null)
      return jspView("/error").addObject("errorMsg","해당하는 게시물이 없습니다.");
    if (!question.getUserId().equals(sessionUser.getUserId()))
      return jspView("/error").addObject("errorMsg","소유주만 수정할 수 있습니다.");

    question.setTitle(request.getParameter("title"));
    question.setContents(request.getParameter("contents"));
    questionDao.update(question);

    return jspView("redirect:/qna/show?questionId="+questionId);
  }
}
