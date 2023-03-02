package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
  public List<Object> query(String query,PreparedStatementSetter setter, RowMapper rowMapper) throws SQLException {
    List<Object> objects = new ArrayList<>();

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
    }
  }

  public Object queryForObject(String query,PreparedStatementSetter setter,RowMapper rowMapper) throws SQLException {
    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)) {

      setter.setValues(pstmt);

      try (ResultSet rs = pstmt.executeQuery(query)) {
        if (rs.next()) {
          return rowMapper.mapRow(rs);
        }
      }
    }
    return null;
  }

  public void update(String sql,PreparedStatementSetter setter) throws SQLException {

    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(sql)) {

      setter.setValues(pstmt);
      pstmt.executeUpdate();
    }
  }
}
