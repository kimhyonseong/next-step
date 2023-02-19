package core.db;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBase {
    private static final Logger log = LoggerFactory.getLogger(DataBase.class);
    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static void modifyUser(User user) {
        User beforeUser = users.get(user.getUserId());
        log.info("param : {}",user);
        log.info("before : {}",beforeUser);

        if (beforeUser != null) {
            user = new User(user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
            users.put(user.getUserId(), user);
            log.info("put : {}",users.get(user.getUserId()));
        }
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
