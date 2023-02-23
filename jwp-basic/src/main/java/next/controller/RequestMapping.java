package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
  private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
  private Map<String,Controller> mappings = new HashMap<>();

  void initMapping() {
    mappings.put("/",new HomeController());
    mappings.put("/user/create", new ForwardController("/user/form.jsp"));
  }
}
