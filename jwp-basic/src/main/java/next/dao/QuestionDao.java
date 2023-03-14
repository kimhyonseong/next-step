package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import next.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
  public long insert(Question question) {
    String sql = "INSERT INTO QUESTIONS(writer,title,contents,createdDate) VALUES(?,?,?,?)";

    KeyHolder keyHolder = new KeyHolder();
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
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
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.update(sql,question.getTitle(),question.getContents(),question.getQuestionId());
  }

  public Question updateCountOfAnswerByQuestionId(long questionId) {
    String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer+1 WHERE questionId = ?";
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.update(sql,questionId);

    return findById(questionId);
  }

  public Question findById(long questionId) {
    String sql = "SELECT questionId,writer,title,contents,createdDate,countOfAnswer " +
            "FROM QUESTIONS WHERE questionId = ?";

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
