package core.mvc;

import next.controller.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
  private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
  private Map<String, Controller> mappings = new HashMap<>();

  void initMapping() {
    mappings.put("/",new HomeController());
    mappings.put("/user/createForm", new ForwardController("/user/form.jsp"));
    mappings.put("/user/create",new CreateUserController());
    mappings.put("/user/loginForm", new ForwardController("/user/login.jsp"));
    mappings.put("/user/login", new LoginController());
    mappings.put("/user/logout", new LogoutController());
    mappings.put("/user/updateForm", new ForwardController("/user/update.jsp"));
    mappings.put("/user/update", new UpdateUserController());
    mappings.put("/user/profile",new ProfileController());

    log.info("Initialized Request Mapping!");
  }

  public Controller findController(String url) {
    return mappings.get(url);
  }

  void put(String url, Controller controller) {
    mappings.put(url, controller);
  }
}
