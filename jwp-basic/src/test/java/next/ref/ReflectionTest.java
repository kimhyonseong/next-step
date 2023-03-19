package next.ref;

import next.model.Question;
import next.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
  private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);


  @Test
  public void showClass() {
    Class<Question> clazz = Question.class;
    log.debug(clazz.getName());
  }

  @Test
  public void newInstanceWithConstructor() {
    Class<User> user = User.class;
    log.debug(user.getName());
  }

  @Test
  public void privateFieldAccess() {
    Class<Student> student = Student.class;
    log.debug(student.getName());
  }
}
