package next.dao;

import core.jdbc.JdbcTemplate;
import next.model.Answer;

import java.sql.ResultSet;
import java.util.List;

public class AnswerDao {
  public void insert(Answer answer) {
    String sql = "INSERT INTO ANSWERS(writer,contents,createdDate,questionId) VALUES(?,?,?,?)";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.update(sql,answer.getWriter(),answer.getContents(),answer.getCreatedDate(),answer.getQuestionId());
  }

  public Answer findById(long answerId) {
    String sql = "SELECT answerId,writer,contents,createdDate,questionId FROM ANSWERS WHERE answerId = ?";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.queryForObject(sql,(ResultSet rs)-> new Answer(
              rs.getLong("answerId"),
              rs.getString("writer"),
              rs.getString("contents"),
              rs.getDate("createdDate"),
              rs.getLong("questionId")
    ),answerId);
  }

  public List<Answer> findAllByQuestionId(long questionId) {
    String sql = "SELECT answerId,writer,contents,createdDate,questionId FROM ANSWERS WHERE questionId=?";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ), questionId);
  }

  public List<Answer> findAll() {
    String sql = "SELECT answerId,writer,contents,createdDate,questionId FROM ANSWERS";

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ), (Object) null);
  }
}
