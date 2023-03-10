package core.mvc.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{
  private static final Logger log = LoggerFactory.getLogger(JsonView.class);
  public JsonView() {}

  @Override
  public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/json;charset=UTF-8");

    ObjectMapper mapper = new ObjectMapper();
    PrintWriter out = response.getWriter();

    out.print(mapper.writeValueAsString(model));
    log.debug("Map : {}",request);
  }
}
