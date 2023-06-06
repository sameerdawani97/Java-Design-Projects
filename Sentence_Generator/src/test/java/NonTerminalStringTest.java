import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NonTerminalStringTest {

  Definition testDefinition;
  String testInputString;

  @BeforeEach
  void setUp() {
    testInputString = "<adj> sunny";
    testDefinition = new NonTerminalString(testInputString);
  }

  @Test
  void getDefinition() {
    assertEquals(testInputString, testDefinition.getDefinition());
  }

  @Test
  void getProduction() {
    assertEquals(testInputString, testDefinition.getProduction());
  }

  @Test
  void testEquals() {
    assertNotEquals(testDefinition, null);
    assertNotEquals("<adj> sunny", testDefinition);
    assertNotEquals("<Adj> sunny", testDefinition);
    assertEquals(new NonTerminalString(testInputString), testDefinition);
    assertEquals(testDefinition, testDefinition);
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(testDefinition.getDefinition());
    assertEquals(hashCode, testDefinition.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Definition{definition='<adj> sunny'}", testDefinition.toString());
  }
}