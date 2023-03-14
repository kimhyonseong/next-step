package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyQuestionController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    long questionId = Long.parseLong(request.getParameter("questionId"));

    QuestionDao questionDao = new QuestionDao();
    Question question = questionDao.findById(questionId);
    question.setTitle(request.getParameter("title"));
    question.setContents(request.getParameter("contents"));
    questionDao.update(question);

    return jspView("redirect:/qna/show?questionId="+questionId);
  }
}
