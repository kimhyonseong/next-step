package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long answerId = Long.parseLong(request.getParameter("answerId"));
    AnswerDao answerDao = new AnswerDao();
    long deleteId =  answerDao.delete(answerId);
    log.debug("deleteId : {}",deleteId);
    Result result = Result.ok();

    if (deleteId == 0L) {
      result = Result.fail("삭제 실패");
    }
    return jsonView().addObject("result",result);
  }
}
