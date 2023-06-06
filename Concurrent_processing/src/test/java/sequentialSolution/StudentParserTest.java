package sequentialSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentParserTest {

  private StudentParser testStudentParser;
  private String testFileName;

  @BeforeEach
  void setUp() {
    testFileName = "src/main/resources/studentVle.csv";
    testStudentParser = new StudentParser(testFileName);
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testStudentParser, testStudentParser);

    // test with the null object
    assertFalse(testStudentParser.equals(null));

    // test with an object belonging to a different class
    assertFalse(testStudentParser.equals(testFileName));

    // test with a different object
    assertNotEquals(testStudentParser, new StudentParser("/student.csv"));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testFileName);
    assertEquals(expectedHashcode, testStudentParser.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "StudentParser{" +
        "fileName='" + testFileName + '\'' +
        '}';
    assertEquals(expectedString, testStudentParser.toString());
  }

}
