package next.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import next.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
  private static final Logger log = LoggerFactory.getLogger(HomeController.class);
  @Override
  public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    QuestionDao questionDao = new QuestionDao();

    //log.debug(String.valueOf(questionDao.findAll().get(0)));
    request.setAttribute("questions",questionDao.findAll());
    return new JspView("/home.jsp");
  }
}
