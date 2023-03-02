package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.exception.DataAccessException;
import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) throws DataAccessException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update(sql, setter);
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };

        return jdbcTemplate.queryForObject(sql, setter, rowMapper);
    }

    public List<User> findAll() throws SQLException{
        String sql = "SELECT userId,password,name,email FROM USERS";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        RowMapper<User> rs = new RowMapper<>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));

            }
        };
        PreparedStatementSetter setter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {}
        };
        return jdbcTemplate.query(sql, setter, rs);
    }

    public void update(User user) throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.update("UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        });
    }
}
