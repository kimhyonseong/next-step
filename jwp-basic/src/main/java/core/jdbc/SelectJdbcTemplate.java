package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
  public List<Object> query(String query) throws SQLException {
    List<Object> objects = new ArrayList<>();

    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)
    ) {
      setValues(pstmt);

      try(ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          objects.add(mapRow(rs));
        }
        return objects;
      }
    }
  }

  public Object queryForObject(String query) throws SQLException {
    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)) {

      setValues(pstmt);

      try (ResultSet rs = pstmt.executeQuery(query)) {
        if (rs.next()) {
          return mapRow(rs);
        }
      }
    }
    return null;
  }

  public abstract void setValues(PreparedStatement pstmt);
  public abstract Object mapRow(ResultSet resultSet);
}
