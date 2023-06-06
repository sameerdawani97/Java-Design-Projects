package sequentialSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseParserTest {

  private CourseParser testCourseParser;
  private String testFileName;

  @BeforeEach
  void setUp() {
    testFileName = "src/main/resources/courses.csv";
    testCourseParser = new CourseParser(testFileName);
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testCourseParser, testCourseParser);

    // test with the null object
    assertFalse(testCourseParser.equals(null));

    // test with an object belonging to a different class
    assertFalse(testCourseParser.equals(testFileName));

    // test with a different object
    assertNotEquals(testCourseParser, new CourseParser("/student.csv"));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testFileName);
    assertEquals(expectedHashcode, testCourseParser.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "CourseParser{" +
        "fileName='" + testFileName + '\'' +
        '}';
    assertEquals(expectedString, testCourseParser.toString());
  }
}
