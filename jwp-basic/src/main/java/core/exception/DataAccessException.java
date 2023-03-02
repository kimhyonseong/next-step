package core.exception;

public class DataAccessException extends RuntimeException{
  DataAccessException() {}
  DataAccessException(String msg) {
    super(msg);
  }
}
