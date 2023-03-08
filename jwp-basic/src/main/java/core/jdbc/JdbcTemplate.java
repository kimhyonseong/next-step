package core.jdbc;

import core.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
  public <T> List<T> query(String sql, RowMapper<T> rowMapper, PreparedStatementSetter setter) {
    List<T> objects = new ArrayList<>();

    try(Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)
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

  public <T> List<T> query(String sql,RowMapper<T> rowMapper,Object... setter) throws DataAccessException {
    return query(sql, rowMapper, createPrepareStatementSetter(setter));
  }

  public <T> T queryForObject(String sql,RowMapper<T> rowMapper, Object... setter) throws DataAccessException {
    List<T> result = query(sql, rowMapper, setter);

    if (result.isEmpty()) {
      return null;
    }
    return result.get(0);
  }

  public void update(PrepareStatementCreator psc, KeyHolder keyHolder) {
    try(Connection con = ConnectionManager.getConnection()) {
      PreparedStatement pstmt = psc.createPrepareStatement(con);
      pstmt.executeUpdate();

      ResultSet rs = pstmt.getGeneratedKeys();
      if (rs.next()) {
        keyHolder.setId(rs.getLong(1));
      }
      rs.close();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public void update(String sql, PreparedStatementSetter setter) {
    try (Connection con = ConnectionManager.getConnection();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
      setter.setValues(pstmt);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public void update(String sql,Object... params) throws DataAccessException {
    update(sql, createPrepareStatementSetter(params));
  }

  public PreparedStatementSetter createPrepareStatementSetter(Object... params) {
    return pstmt -> {
      for(int i=0; i<params.length; i++) {
        pstmt.setObject(i+1,params[i]);
      }
    };
  }
}
