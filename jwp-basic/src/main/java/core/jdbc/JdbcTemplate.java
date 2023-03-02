package core.jdbc;

import core.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
  public <T> List<T> query(String query,PreparedStatementSetter setter, RowMapper<T> rowMapper) throws DataAccessException {
    List<T> objects = new ArrayList<>();

    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)
    ) {
      setter.setValues(pstmt);

      try(ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          objects.add(rowMapper.mapRow(rs));
        }
        return objects;
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public <T> T queryForObject(String sql,PreparedStatementSetter setter,RowMapper<T> rowMapper) throws DataAccessException {
    List<T> result = query(sql, setter, rowMapper);

    if (result.isEmpty()) {
      return null;
    }
    return result.get(0);
  }

  public void update(String sql,PreparedStatementSetter setter) throws DataAccessException {

    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(sql)) {

      setter.setValues(pstmt);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }
}
