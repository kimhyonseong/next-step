package next.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
  private static final Logger log = LoggerFactory.getLogger(Utils.class);
  private static final Utils utils = new Utils();

  private Utils() {}

  public static Utils getInstance() {
    return utils;
  }

  public void goLogin(HttpServletResponse response) {
    try {
      response.setContentType("text/html; charset=UTF-8;");
      PrintWriter writer = response.getWriter();
      writer.println("<script>alert('로그인이 필요합니다.'); location.href='/user/login';</script>");
      writer.close();
    } catch (IOException e) {
      log.error("IOException",e);
    }
  }
}
