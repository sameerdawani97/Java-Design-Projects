import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialCharacterTest {

  Definition testDefinition;
  String testInputString;

  @BeforeEach
  void setUp() {
    testInputString = ":";
    testDefinition = new SpecialCharacter(testInputString);
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
    assertEquals(testDefinition, testDefinition);
    assertEquals(new SpecialCharacter(testInputString), testDefinition);
    assertNotEquals("sun", testDefinition);
    assertNotEquals("?", testDefinition);
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(testDefinition.getDefinition());
    assertEquals(hashCode, testDefinition.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Definition{definition=':'}", testDefinition.toString());
  }
}