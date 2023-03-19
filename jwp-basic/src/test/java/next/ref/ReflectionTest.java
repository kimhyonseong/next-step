package next.ref;

import next.model.Question;
import next.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectionTest {
  private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);


  @Test
  public void showClass() {
    Class<Question> clazz = Question.class;
    log.debug(clazz.getName());
    log.debug(Arrays.toString(clazz.getConstructors()));
    log.debug(Arrays.toString(clazz.getMethods()));
  }

  @Test
  public void newInstanceWithConstructor() throws Exception {
    Class<User> clazz = User.class;
    Constructor<User> constructor = clazz.getConstructor(String.class,String.class,String.class,String.class);
    User user = constructor.newInstance("lss1914","1234","이순신","lss1914@gmail.com");

    log.debug(String.valueOf(user));
  }

  @Test
  public void privateFieldAccess() throws Exception {
    Class<Student> clazz = Student.class;
    Constructor<Student> constructor = clazz.getDeclaredConstructor();
    Student student = constructor.newInstance();

    Field field1 = clazz.getDeclaredField("name");
    field1.setAccessible(true);
    field1.set(student,"이순신");

    Field field2 = clazz.getDeclaredField("age");
    field2.setAccessible(true);
    field2.setInt(student,20);

    log.debug(student.getName());
    log.debug(String.valueOf(student.getAge()));
  }
}
