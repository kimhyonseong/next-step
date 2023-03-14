package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionFormController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String questionId = request.getParameter("questionId");
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());

    if (sessionUser == null)
      return jspView("/error")
              .addObject("errorMsg","로그인 후 이용할 수 있습니다.")
              .addObject("location","/user/loginForm");

    if (questionId != null && !questionId.equals("")) {
      QuestionDao questionDao = new QuestionDao();
      Question question = questionDao.findByIdAndUser(Long.parseLong(questionId),sessionUser);

      return jspView("/qna/form.jsp").addObject("question",question);
    }

    return jspView("/qna/form.jsp");
  }
}
