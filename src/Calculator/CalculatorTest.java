package Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;

class CalculatorTest {
  private Calculator cal;

  @BeforeEach
  void before() {
    cal = new Calculator();
  }

  @Test
  public void add() {
    Assertions.assertEquals(9,cal.add(6,3));
  }

  @Test
  public void div() {
    Assertions.assertEquals(2,cal.div(6,3));
  }
}