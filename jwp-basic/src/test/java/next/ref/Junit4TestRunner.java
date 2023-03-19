package next.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Junit4TestRunner {
    private static final Logger log = LoggerFactory.getLogger(Junit4TestRunner.class);

    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        Constructor<Junit4Test> constructor = clazz.getDeclaredConstructor();
        Method[] methods = clazz.getDeclaredMethods();
        Junit4Test junit4Test = constructor.newInstance();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(junit4Test);
            }
        }
    }
}
