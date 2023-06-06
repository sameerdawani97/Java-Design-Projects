package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseCSVProcessorTest {

  private CourseCSVProcessor testCourseCSVProcessor;
  private String testCourseFileName;

  @BeforeEach
  void setUp() {
    testCourseFileName = "src/main/resources/courses.csv";
    testCourseCSVProcessor = new CourseCSVProcessor(testCourseFileName);
  }

  @Test
  void createOutputFiles_getFileNames() throws IOException {
    String destination = "src/main/resources/generated_files/";
    testCourseCSVProcessor.createOutputFiles();
    assertEquals(22, testCourseCSVProcessor.getFileNames().size());
  }

  @Test
  void testEquals() {
    assertTrue(testCourseCSVProcessor.equals(testCourseCSVProcessor));
    assertFalse(testCourseCSVProcessor.equals(null));
    assertTrue(testCourseCSVProcessor.equals(new CourseCSVProcessor(testCourseFileName)));
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hash(testCourseFileName),testCourseCSVProcessor.hashCode());
  }
}