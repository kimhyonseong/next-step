package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepareStatementCreator {
  PreparedStatement createPrepareStatement(Connection connection) throws SQLException;
}
