package next.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long answerId = Long.parseLong(request.getParameter("answerId"));
    AnswerDao answerDao = new AnswerDao();
    long deleteId =  answerDao.delete(answerId);
    log.debug("deleteId : {}",deleteId);

    if (deleteId == 0L) {
      request.setAttribute("result",Result.fail("삭제 실패"));
    }
    request.setAttribute("result",Result.ok());
    return new JsonView();
  }
}
