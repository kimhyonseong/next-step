package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import next.model.User;
import next.utils.UserSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User sessionUser = UserSessionUtils.getUserFromSession(request.getSession());

    if (sessionUser == null) {
      return jspView("/error")
              .addObject("errorMsg","로그인이 필요한 작업입니다.")
              .addObject("location","/user/login");
    }
    AnswerDao answerDao = new AnswerDao();
    long answerId = Long.parseLong(request.getParameter("answerId"));
    long deleteId =  answerDao.deleteByUser(answerId,sessionUser);
    log.debug("deleteId : {}",deleteId);
    Result result = Result.ok();

    if (deleteId == 0L) {
      result = Result.fail("삭제 실패");
    }
    return jsonView().addObject("result",result);
  }
}
