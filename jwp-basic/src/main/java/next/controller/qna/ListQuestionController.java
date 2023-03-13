package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListQuestionController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    QuestionDao questionDao = new QuestionDao();

    return jsonView().addObject("questions",questionDao.findAll());
  }
}
