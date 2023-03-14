package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import next.model.User;
import next.utils.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuestionController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());

    if (sessionUser == null) {
      return jspView("/error")
              .addObject("errorMsg","로그인이 필요한 작업입니다.")
              .addObject("location","/user/login");
    }

    Question question = new Question(
            request.getParameter("writer"),
            request.getParameter(sessionUser.getUserId()),
            request.getParameter("title"),
            request.getParameter("contents"));
    log.debug("question : {}",question);

    QuestionDao questionDao = new QuestionDao();
    long insertId = questionDao.insert(question);
    log.debug("insertId : {}",insertId);

    if (insertId == 0L) {
      return jspView("/error")
              .addObject("errorMsg","에러가 발생하였습니다. 잠시후 다시 시도해 주세요.");
    }
    return jspView("redirect:/qna/show?questionId="+insertId);
  }
}
