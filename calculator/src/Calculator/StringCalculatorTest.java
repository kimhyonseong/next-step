package Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
  private StringCalculator cal;

  @BeforeEach
  void before() {
    cal = new StringCalculator();
  }

  @Test
  void add() {
    cal.setString("1:1:4");
    cal.splitColon();
    Assertions.assertEquals(6,cal.addAllStr());

    cal.setString(" ");
    cal.splitColon();
    Assertions.assertEquals(0,cal.addAllStr());

    cal.setString(null);
    cal.splitColon();
    Assertions.assertEquals(0,cal.addAllStr());

    cal.setString("-1:11:0");
    cal.splitColon();
    Assertions.assertEquals(0,cal.addAllStr());
  }
}