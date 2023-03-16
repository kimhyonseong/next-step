package core.exception;

public class CannotDeleteException extends Exception{
  private static final long serialVersionUID = 2L;

  public CannotDeleteException() {
    super();
  }
  public CannotDeleteException(String msg) {
    super(msg);
  }

  public CannotDeleteException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(msg, cause, enableSuppression, writableStackTrace);
  }

  public CannotDeleteException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public CannotDeleteException(Throwable cause) {
    super(cause);
  }
}
