package Calculator;

public class StringCalculator {
  private String string;
  private String[] strArray;

  public StringCalculator() {}

  public StringCalculator(String string) {
    setString(string);
  }

  public void setString(String string) {
    if (string == null) string = "";
    this.string = string.trim();
  }

  public void splitAll() {
    this.strArray = this.string.split("[^0-9]");
  }

  public void splitColon() {
    this.strArray = this.string.split(":");
  }

  public void splitCustom(String separator) {
    this.strArray = this.string.split(separator);
  }

  public int addAllStr() {
    if (this.string.length() < 1) return 0;

    try {
      return add();
    } catch (RuntimeException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int add() {
    int result = 0;

    for(String num : this.strArray) {
      checkString(num);
      result += Integer.parseInt(num);
    }

    return result;
  }

  public void checkString(String str) {
    if (Integer.parseInt(str) < 0) throw new RuntimeException("음수로 인한 에러 발생");
  }
}
