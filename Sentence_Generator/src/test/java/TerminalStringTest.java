import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminalStringTest {

  Definition testDefinition;
  String testInputString;

  @BeforeEach
  void setUp() {
    testInputString = "sunrise";
    testDefinition = new TerminalString(testInputString);
  }

  @Test
  void getDefinition() {
    assertEquals(testInputString, testDefinition.getDefinition());
  }

  @Test
  void getProduction() {
    assertEquals(" " + testInputString, testDefinition.getProduction());
  }

  @Test
  void testEquals() {
    assertNotEquals(testDefinition, null);
    assertNotEquals("sunrise", testDefinition);
    assertEquals(new TerminalString(testInputString), testDefinition);
    assertEquals(testDefinition, testDefinition);
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(testDefinition.getDefinition());
    assertEquals(hashCode, testDefinition.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Definition{definition='sunrise'}", testDefinition.toString());
  }
}