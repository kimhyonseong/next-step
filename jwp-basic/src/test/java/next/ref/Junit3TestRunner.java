package next.ref;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Constructor<Junit3Test> constructor = clazz.getConstructor();
        Method[] methods = clazz.getDeclaredMethods();

        Junit3Test junit3Test = constructor.newInstance();
        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                method.invoke(junit3Test);
            }
        }
    }
}
