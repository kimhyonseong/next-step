package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import next.model.Question;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
  public long insert(Question question) {
    String sql = "INSERT INTO QUESTIONS(writer,title,contents,createdDate) VALUES(?,?,?,?)";

    KeyHolder keyHolder = new KeyHolder();
    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    jdbcTemplate.update((Connection con) ->{
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, question.getWriter());
      pstmt.setString(2, question.getTitle());
      pstmt.setString(3, question.getContents());
      pstmt.setTimestamp(4, new Timestamp(question.getTimeCreatedDate()));
      return pstmt;
    }, keyHolder);

    return keyHolder.getId();
  }

  public void update(Question question) {
    String sql = "UPDATE QUESTIONS SET title = ?,contents = ? WHERE questionId = ?";
    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    jdbcTemplate.update(sql,question.getTitle(),question.getContents(),question.getQuestionId());
  }

  public Question updateCountOfAnswerByQuestionId(long questionId) {
    String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer+1 WHERE questionId = ?";
    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    jdbcTemplate.update(sql,questionId);

    return findById(questionId);
  }

  public Question findById(long questionId) {
    String sql = "SELECT questionId,writer,userId,title,contents,createdDate,countOfAnswer " +
            "FROM QUESTIONS WHERE questionId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.queryForObject(sql,(ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer")
    ),questionId);
  }

  public Question findByIdAndUser(long questionId, User user) {
    String sql = "SELECT questionId,writer,userId,title,contents,createdDate,countOfAnswer " +
            "FROM QUESTIONS WHERE questionId = ? AND userId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.queryForObject(sql,(ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer")
    ),questionId,user.getUserId());
  }

  public List<Question> findAll() {
    String sql = "SELECT questionId,writer,userId,title,contents,createdDate,countOfAnswer FROM QUESTIONS";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getInt("countOfAnswer")
    ), (Object) null);
  }
  public void delete(long questionId) {
    String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    jdbcTemplate.update(sql,questionId);
  }
}
