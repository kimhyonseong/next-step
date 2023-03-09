package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DeleteAnswerController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long answerId = Long.parseLong(request.getParameter("answerId"));
    AnswerDao answerDao = new AnswerDao();
    long deleteId =  answerDao.delete(answerId);

    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();

    ObjectMapper mapper = new ObjectMapper();

    log.debug("deleteId : {}",deleteId);
    if (deleteId == 0L) {
      out.print(mapper.writeValueAsString(Result.fail("삭제 실패")));
    }
    out.print(mapper.writeValueAsString(Result.ok()));
    return null;
  }
}
