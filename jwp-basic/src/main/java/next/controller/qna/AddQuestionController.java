package next.controller.qna;

import core.mvc.ModelAndView;
import core.mvc.controller.AbstractController;
import core.mvc.controller.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuestionController extends AbstractController {
  @Override
  public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Question question = new Question(
            request.getParameter("writer"),
            request.getParameter("title"),
            request.getParameter("contents"));

    QuestionDao questionDao = new QuestionDao();
    long insertId = questionDao.insert(question);

    if (insertId == 0L) {
      return jspView("/error")
              .addObject("errorMsg","에러가 발생하였습니다. 잠시후 다시 시도해 주세요.")
              .addObject("location","");
    }
    return jspView("redirect:/qna/show?questionId="+insertId);
  }
}
