package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.exception.DataAccessException;
import core.jdbc.JdbcTemplate;
import next.model.User;

public class UserDao {
    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
        jdbcTemplate.update(sql, user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();

        return jdbcTemplate.queryForObject(sql, (ResultSet rs) -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        ), userId);
    }

    public List<User> findAll() throws SQLException{
        String sql = "SELECT userId,password,name,email FROM USERS";

        JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();

        return jdbcTemplate.query(sql, (ResultSet rs) -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")), (Object) null);
    }

    public void update(User user) throws SQLException{
        JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
        jdbcTemplate.update("UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?",
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getUserId());
    }
}
