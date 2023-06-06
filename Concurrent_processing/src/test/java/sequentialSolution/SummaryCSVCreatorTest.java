package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SummaryCSVCreatorTest {

  private SummaryCSVCreator testSummaryCSVCreator;
  private String testDirectory;
  private CourseParser tesCourseParser;
  private StudentParser testStudentParser;
  private OULADFilesChecker testOULADFilesChecker;

  @BeforeEach
  void setUp() {
    testDirectory = "src/main/resources/";
    testStudentParser = new StudentParser(testDirectory);
    tesCourseParser = new CourseParser(testDirectory);
    testOULADFilesChecker = new OULADFilesChecker(testDirectory);
    testSummaryCSVCreator = new SummaryCSVCreator(testDirectory,
        tesCourseParser, testStudentParser, testOULADFilesChecker);
  }

  @Test
  void getDirectory() {
    assertEquals(testDirectory, testSummaryCSVCreator.getDirectory());
  }

  @Test
  void setDirectory() {
    testDirectory = "src/main/java/";
    testSummaryCSVCreator.setDirectory(testDirectory);
    assertEquals(testDirectory, testSummaryCSVCreator.getDirectory());
  }

  @Test
  void createSummaryCSV() throws FileNotFoundException, IOException {
   testSummaryCSVCreator.createSummaryCSV(testDirectory);

    // test for FileNotFoundException
    Exception exception = assertThrows(FileNotFoundException.class, () -> {
      testSummaryCSVCreator.createSummaryCSV("src/main/empty/");
    });
    String expectedMessage = "File is not found!";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void testEquals() {

    // test with the same object
    assertEquals(testSummaryCSVCreator, testSummaryCSVCreator);

    // test with the null object
    assertFalse(testSummaryCSVCreator.equals(null));

    // test with an object belonging to a different class
    assertFalse(testSummaryCSVCreator.equals(testDirectory));

    // test with a different object
    assertNotEquals(testSummaryCSVCreator, new SummaryCSVCreator());

  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testSummaryCSVCreator.getDirectory());
    assertEquals(expectedHashcode, testSummaryCSVCreator.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "SummaryCSVCreator{" +
        "directory='" + testDirectory + '\'' +
        '}';
    assertEquals(expectedString, testSummaryCSVCreator.toString());
  }
}