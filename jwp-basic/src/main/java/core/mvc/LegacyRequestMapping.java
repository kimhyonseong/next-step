package core.mvc;

import core.mvc.controller.Controller;
import core.mvc.controller.ForwardController;
import next.controller.*;
import next.controller.qna.*;
import next.controller.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LegacyRequestMapping {
  private static final Logger log = LoggerFactory.getLogger(LegacyRequestMapping.class);
  private Map<String, Controller> mappings = new HashMap<>();

  void initMapping() {
    mappings.put("/",new HomeController());
    mappings.put("/error", new ForwardController("/error/error.jsp"));
    mappings.put("/user/createForm", new ForwardController("/user/form.jsp"));
    mappings.put("/user/create",new CreateUserController());
    mappings.put("/user/loginForm", new ForwardController("/user/login.jsp"));
    mappings.put("/user/login", new LoginController());
    mappings.put("/user/logout", new LogoutController());
    mappings.put("/user/updateForm", new UpdateUserFormController());
    mappings.put("/user/update", new UpdateUserController());
    mappings.put("/user/profile",new ProfileController());
    mappings.put("/qna/show",new ShowQuestionController());
    mappings.put("/qna/write",new AddQuestionController());
    mappings.put("/qna/writeForm",new QuestionFormController());
    mappings.put("/qna/modify",new ModifyQuestionController());
    mappings.put("/qna/delete",new DeleterQuestionController());
    mappings.put("/api/qna/addAnswer",new AddAnswerController());
    mappings.put("/api/qna/deleteAnswer",new DeleteAnswerController());
    mappings.put("/api/qna/list",new ListQuestionController());

    log.info("Initialized Request Mapping!");
  }

  public Controller findController(String url) {
    return mappings.get(url);
  }

  void put(String url, Controller controller) {
    mappings.put(url, controller);
  }
}
