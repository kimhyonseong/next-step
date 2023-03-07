package next.dao;

import core.exception.DataAccessException;
import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.model.Question;

import java.sql.ResultSet;
import java.util.List;

public class QuestionDao {
  public void insert(Question question) {
    String sql = "INSERT INTO QUESTIONS(writer,title,contents,createdDate) VALUES(?,?,?,?)";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.update(sql,question.getWriter(),question.getTitle(),question.getContents(),question.getCreatedDate());
  }

  public void update(Question question) {
    String sql = "UPDATE QUESTIONS SET title = ?,contents = ?";
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.update(sql,question.getTitle(),question.getContents());
  }

  public Question findById(long questionId) {
    String sql = "SELECT questionId,writer,title,contents,createdDate,countOfAnswer FROM QUESTIONS WHERE questionId = ?";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.queryForObject(sql,(ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer")
    ),questionId);
  }

  public List<Question> findAll() {
    String sql = "SELECT questionId,writer,title,contents,createdDate,countOfAnswer FROM QUESTIONS";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer")
    ), (Object) null);
  }
}
