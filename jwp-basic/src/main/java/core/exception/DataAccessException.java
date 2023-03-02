package core.exception;

public class DataAccessException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public DataAccessException() {
    super();
  }
  public DataAccessException(String msg) {
    super(msg);
  }

  public DataAccessException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(msg, cause, enableSuppression, writableStackTrace);
  }

  public DataAccessException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public DataAccessException(Throwable cause) {
    super(cause);
  }
}
