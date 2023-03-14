package next.dao;

import core.exception.DataAccessException;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import next.model.Answer;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
  private static final Logger log = LoggerFactory.getLogger(AnswerDao.class);

  public Answer insert(Answer answer) {
    String sql = "INSERT INTO ANSWERS(writer,contents,createdDate,questionId) VALUES(?,?,?,?)";

    KeyHolder keyHolder = new KeyHolder();
    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    jdbcTemplate.update((Connection connection) -> {
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, answer.getWriter());
      pstmt.setString(2, answer.getContents());
      pstmt.setTimestamp(3, new Timestamp(answer.getTimeFromCreateDate()));
      pstmt.setLong(4, answer.getQuestionId());
      return pstmt;
    },keyHolder);

    return findById(keyHolder.getId());
  }

  public Answer findById(long answerId) {
    String sql = "SELECT answerId,writer,userId,contents,createdDate,questionId FROM ANSWERS WHERE answerId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.queryForObject(sql,(ResultSet rs)-> new Answer(
              rs.getLong("answerId"),
              rs.getString("writer"),
              rs.getString("userId"),
              rs.getString("contents"),
              rs.getDate("createdDate"),
              rs.getLong("questionId")
    ),answerId);
  }

  public List<Answer> findAllByQuestionId(long questionId) {
    String sql = "SELECT answerId,writer,userId,contents,createdDate,questionId FROM ANSWERS WHERE questionId=?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ), questionId);
  }

  public List<Answer> findAll() {
    String sql = "SELECT answerId,writer,userId,contents,createdDate,questionId FROM ANSWERS";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    return jdbcTemplate.query(sql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ), (Object) null);
  }


  public long delete(long answerId) {
    String selectSql = "SELECT answerId,writer,userId,contents,createdDate,questionId FROM ANSWERS WHERE answerId = ?";
    String deleteSql = "DELETE FROM ANSWERS WHERE answerId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    Answer answer = jdbcTemplate.queryForObject(selectSql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ),answerId);

    if (answer == null) {
      return 0;
    }

    jdbcTemplate.update(deleteSql,answerId);
    return answerId;
  }

  public long deleteByUser(long answerId, User user) {
    String selectSql = "SELECT answerId,writer,userId,contents,createdDate,questionId FROM ANSWERS WHERE answerId = ?";
    String deleteSql = "DELETE FROM ANSWERS WHERE answerId = ? AND userId = ?";

    JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
    Answer answer = jdbcTemplate.queryForObject(selectSql,(ResultSet rs)-> new Answer(
            rs.getLong("answerId"),
            rs.getString("writer"),
            rs.getString("userId"),
            rs.getString("contents"),
            rs.getDate("createdDate"),
            rs.getLong("questionId")
    ),answerId,user.getUserId());

    if (answer == null) {
      return 0;
    }

    jdbcTemplate.update(deleteSql,answerId);
    return answerId;
  }
}
