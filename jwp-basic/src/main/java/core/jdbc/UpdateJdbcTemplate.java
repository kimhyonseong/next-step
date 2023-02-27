package core.jdbc;

import next.dao.UserDao;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateJdbcTemplate {
  public void update(User user, UserDao userDao) throws SQLException {
    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      con = ConnectionManager.getConnection();
      String sql = userDao.createQueryForUpdate();
      pstmt = con.prepareStatement(sql);

      userDao.setValuesForUpdate(user, pstmt);
      pstmt.executeUpdate();
    } finally {
      if (pstmt != null) {
        pstmt.close();
      }

      if (con != null) {
        con.close();
      }
    }
  }
}
